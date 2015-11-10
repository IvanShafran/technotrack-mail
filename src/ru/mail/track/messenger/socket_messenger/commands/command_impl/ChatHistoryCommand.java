package ru.mail.track.messenger.socket_messenger.commands.command_impl;

import ru.mail.track.messenger.socket_messenger.chat.ChatStore;
import ru.mail.track.messenger.socket_messenger.commands.Command;
import ru.mail.track.messenger.socket_messenger.commands.CommandType;
import ru.mail.track.messenger.socket_messenger.commands.command_result.ChatHistoryCommandResult;
import ru.mail.track.messenger.socket_messenger.commands.command_result.CommandResult;
import ru.mail.track.messenger.socket_messenger.message.Message;
import ru.mail.track.messenger.socket_messenger.message.MessageStore;
import ru.mail.track.messenger.socket_messenger.message.message_impl.ChatHistoryMessage;
import ru.mail.track.messenger.socket_messenger.session.Session;
import ru.mail.track.messenger.socket_messenger.user.User;

import java.util.ArrayList;
import java.util.List;

public class ChatHistoryCommand implements Command {
    private ChatStore chatStore;
    private MessageStore messageStore;

    public ChatHistoryCommand(ChatStore chatStore, MessageStore messageStore) {
        this.chatStore = chatStore;
        this.messageStore = messageStore;
    }

    @Override
    public CommandResult execute(Session session, Message message) {
        User user = session.getSessionUser();
        if (user == null) {
            return new CommandResult(CommandType.CHAT_HISTORY, CommandResult.Status.NOT_LOGGED, null);
        }

        List<Long> chatIds = chatStore.getChatsByUserId(user.getId());
        ChatHistoryMessage chatHistoryMessage = (ChatHistoryMessage)message;
        Long chatId = chatHistoryMessage.getChatId();
        if (chatId == null) {
            return new CommandResult(CommandType.CHAT_HISTORY, CommandResult.Status.FAILED, "Please, check usage");
        }

        if (!chatIds.contains(chatId)) {
            return new CommandResult(CommandType.CHAT_HISTORY, CommandResult.Status.PERMISSION_DENIED, null);
        }

        List<Long> messageIds = messageStore.getMessagesFromChat(chatId);
        List<Message> messages = new ArrayList<>();
        Integer number = Math.max(chatHistoryMessage.getNumber(), messageIds.size());
        if (chatHistoryMessage.getNumber() != null) {
            for (int i = number - 1; i >= 0; --i) {
                messages.add(messageStore.getMessageById(messageIds.get(messageIds.size() - i)));
            }
        } else {
            messageIds.stream().forEach(id -> messages.add(messageStore.getMessageById(id)));
        }

        return new ChatHistoryCommandResult(CommandResult.Status.OK, null, messages);
    }

    @Override
    public String getUsage() {
        return "/chat_history <chatId> [count]";
    }
}

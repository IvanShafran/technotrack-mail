package main.java.socket_messenger.commands.command_impl;

import main.java.socket_messenger.chat.ChatStore;
import main.java.socket_messenger.commands.CommandType;
import main.java.socket_messenger.commands.command_result.ChatFindCommandResult;
import main.java.socket_messenger.commands.command_result.ChatHistoryCommandResult;
import main.java.socket_messenger.commands.command_result.CommandResult;
import main.java.socket_messenger.message.Message;
import main.java.socket_messenger.message.MessageStore;
import main.java.socket_messenger.message.message_impl.ChatFindMessage;
import main.java.socket_messenger.message.message_impl.ChatHistoryMessage;
import main.java.socket_messenger.message.message_impl.SendMessage;
import main.java.socket_messenger.session.Session;

import java.util.ArrayList;
import java.util.List;

public class ChatFindCommand extends ChatHistoryCommand {
    @Override
    public CommandResult execute(Session session, Message message) {
        ChatFindMessage chatFindMessage = (ChatFindMessage) message;
        ChatHistoryMessage chatHistoryMessage = new ChatHistoryMessage(chatFindMessage.getChatId(), null);
        CommandResult commandResult = super.execute(session, chatHistoryMessage);
        if (commandResult.getStatus() != CommandResult.Status.OK) {
            return new CommandResult(CommandType.CHAT_FIND, commandResult.getStatus(), commandResult.getReport());
        }

        String regex = chatFindMessage.getRegex();
        if (regex == null) {
            return new ChatFindCommandResult(CommandResult.Status.WARNING, "empty regex",
                    ((ChatHistoryCommandResult)commandResult).getMessages());
        }

        List<Message> messages = ((ChatHistoryCommandResult)commandResult).getMessages();
        List<Message> matchedMessages = new ArrayList<>();
        for (Message msg : messages) {
            if (((SendMessage) msg).getMessage().matches(regex)) {
                matchedMessages.add(msg);
            }
        }

        return new ChatFindCommandResult(CommandResult.Status.OK, null, matchedMessages);
    }

    @Override
    public String getUsage() {
        return "/chat_find <chatId> <regex>";
    }

    public ChatFindCommand(ChatStore chatStore, MessageStore messageStore) {
        super(chatStore, messageStore);
    }
}

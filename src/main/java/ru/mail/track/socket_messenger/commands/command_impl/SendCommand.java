package main.java.ru.mail.track.socket_messenger.commands.command_impl;

import main.java.ru.mail.track.socket_messenger.chat.Chat;
import main.java.ru.mail.track.socket_messenger.chat.ChatStore;
import main.java.ru.mail.track.socket_messenger.commands.Command;
import main.java.ru.mail.track.socket_messenger.commands.CommandType;
import main.java.ru.mail.track.socket_messenger.commands.command_result.CommandResult;
import main.java.ru.mail.track.socket_messenger.message.Message;
import main.java.ru.mail.track.socket_messenger.message.MessageStore;
import main.java.ru.mail.track.socket_messenger.message.message_impl.SendMessage;
import main.java.ru.mail.track.socket_messenger.net.SessionManager;
import main.java.ru.mail.track.socket_messenger.session.Session;

import java.io.IOException;
import java.util.List;

/**
 *
 */
public class SendCommand implements Command {
    private ChatStore chatStore;
    private MessageStore messageStore;
    private SessionManager sessionManager;

    public SendCommand(ChatStore chatStore, MessageStore messageStore, SessionManager sessionManager) {
        this.chatStore = chatStore;
        this.messageStore = messageStore;
        this.sessionManager = sessionManager;
    }

    @Override
    public CommandResult execute(Session session, Message message) {
        SendMessage sendMessage = (SendMessage) message;
        Long senderId = session.getSessionUser().getId();
        sendMessage.setSenderId(senderId);

        Long chatId = sendMessage.getChatId();
        Chat chat = chatStore.getChatById(chatId);
        List<Long> parts = chat.getParticipantIds();

        if (senderId == null) {
            return new CommandResult(CommandType.CHAT_SEND, CommandResult.Status.NOT_LOGGED, null);
        }

        if (chatId == null) {
            return new CommandResult(CommandType.CHAT_SEND, CommandResult.Status.FAILED, "Please, check usage by /help");
        }

        if (!parts.contains(senderId)) {
            return new CommandResult(CommandType.CHAT_SEND, CommandResult.Status.FAILED, "Please, check usage by /help");
        }

        messageStore.addMessage(sendMessage);

        try {
            for (Long userId : parts) {
                Session userSession = sessionManager.getSessionByUser(userId);
                if (userSession != null) {
                    userSession.getConnectionHandler().send(message);
                }
            }
        } catch (IOException e) {
            return new CommandResult(CommandType.CHAT_SEND, CommandResult.Status.FAILED,
                    "Message didn't deliver to chat participants");
        }

        return new CommandResult(CommandType.CHAT_SEND, CommandResult.Status.OK, null);
    }

    @Override
    public String getUsage() {
        return "/chat_send <chatId> <message>";
    }
}

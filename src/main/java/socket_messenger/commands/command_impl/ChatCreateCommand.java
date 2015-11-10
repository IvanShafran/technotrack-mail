package main.java.socket_messenger.commands.command_impl;

import main.java.socket_messenger.chat.ChatStore;
import main.java.socket_messenger.commands.Command;
import main.java.socket_messenger.commands.CommandType;
import main.java.socket_messenger.commands.command_result.ChatCreateCommandResult;
import main.java.socket_messenger.commands.command_result.CommandResult;
import main.java.socket_messenger.message.Message;
import main.java.socket_messenger.message.message_impl.ChatCreateMessage;
import main.java.socket_messenger.session.Session;
import main.java.socket_messenger.user.User;
import main.java.socket_messenger.user.UserStore;

import java.util.List;

public class ChatCreateCommand implements Command {
    private ChatStore chatStore;
    private UserStore userStore;

    public ChatCreateCommand(ChatStore chatStore, UserStore userStore) {
        this.chatStore = chatStore;
        this.userStore = userStore;
    }

    @Override
    public CommandResult execute(Session session, Message message) {
        if (session.getSessionUser() == null) {
            return new CommandResult(CommandType.CHAT_CREATE, CommandResult.Status.NOT_LOGGED, null);
        }

        ChatCreateMessage chatCreateMessage = (ChatCreateMessage) message;
        List<Long> participants = chatCreateMessage.getUsers();

        if (participants == null || participants.size() == 0) {
            return new CommandResult(CommandType.CHAT_CREATE, CommandResult.Status.FAILED, "Please, check usage");
        }

        for (Long id : participants) {
            User user = userStore.getUserById(id);
            if (user == null) {
                return new CommandResult(CommandType.CHAT_CREATE, CommandResult.Status.FAILED,
                        "Some user doesn't exist");
            }
        }

        Long chatId;
        if (participants.size() == 1) {
            chatId = chatStore.getTwoChatId(session.getSessionUser().getId(), participants.get(0));
        } else {
            participants.add(session.getSessionUser().getId());
            chatId = chatStore.createChat(participants);
        }

        return new ChatCreateCommandResult(CommandResult.Status.OK, null, chatId);
    }

    @Override
    public String getUsage() {
        return "/chat_create <userId list>";
    }
}

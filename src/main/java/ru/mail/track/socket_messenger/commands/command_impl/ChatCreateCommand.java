package ru.mail.track.socket_messenger.commands.command_impl;

import ru.mail.track.socket_messenger.chat.ChatStore;
import ru.mail.track.socket_messenger.commands.Command;
import ru.mail.track.socket_messenger.commands.CommandType;
import ru.mail.track.socket_messenger.commands.command_result.ChatCreateCommandResult;
import ru.mail.track.socket_messenger.commands.command_result.CommandResult;
import ru.mail.track.socket_messenger.message.Message;
import ru.mail.track.socket_messenger.message.message_impl.ChatCreateMessage;
import ru.mail.track.socket_messenger.session.Session;
import ru.mail.track.socket_messenger.user.User;
import ru.mail.track.socket_messenger.user.UserStore;

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
        User sessionUser = session.getSessionUser();

        if (sessionUser == null) {
            return new CommandResult(CommandType.CHAT_CREATE, CommandResult.Status.NOT_LOGGED, null);
        }

        ChatCreateMessage chatCreateMessage = (ChatCreateMessage) message;
        List<Long> participants = chatCreateMessage.getUsers();

        if (participants == null || participants.size() == 0) {
            return new CommandResult(CommandType.CHAT_CREATE, CommandResult.Status.FAILED, "Please, check usage");
        }

        if (!participants.contains(sessionUser.getId())) {
            participants.add(sessionUser.getId());
        }

        for (Long id : participants) {
            User user = userStore.getUserById(id);
            if (user == null) {
                return new CommandResult(CommandType.CHAT_CREATE, CommandResult.Status.FAILED,
                        "Some user doesn't exist");
            }
        }

        Long chatId;
        if (participants.size() == 2) {
            chatId = chatStore.getTwoChatId(sessionUser.getId(), participants.get(0));
        } else {
            chatId = chatStore.createChat(participants);
        }

        return new ChatCreateCommandResult(CommandResult.Status.OK, null, chatId);
    }

    @Override
    public String getUsage() {
        return "/chat_create <userId list>";
    }
}

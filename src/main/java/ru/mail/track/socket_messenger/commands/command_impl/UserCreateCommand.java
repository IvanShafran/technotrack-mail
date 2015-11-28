package ru.mail.track.socket_messenger.commands.command_impl;

import ru.mail.track.socket_messenger.authorization.AuthorizationService;
import ru.mail.track.socket_messenger.commands.Command;
import ru.mail.track.socket_messenger.commands.CommandType;
import ru.mail.track.socket_messenger.commands.command_result.CommandResult;
import ru.mail.track.socket_messenger.message.Message;
import ru.mail.track.socket_messenger.message.message_impl.UserCreateMessage;
import ru.mail.track.socket_messenger.session.Session;

public class UserCreateCommand implements Command {
    private AuthorizationService authorizationService;

    public UserCreateCommand(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    public CommandResult execute(Session session, Message message) {
        UserCreateMessage userCreateMessage = (UserCreateMessage)message;
        String login = userCreateMessage.getLogin();
        String pass = userCreateMessage.getPass();

        if (login == null || pass == null) {
            return new CommandResult(CommandType.USER_CREATE, CommandResult.Status.FAILED,
                    "empty login or pass. Please, check usage");
        }

        AuthorizationService.Status status = authorizationService.registerUser(login, pass);

        switch (status) {
            case OK:
                return new CommandResult(CommandType.USER_CREATE, CommandResult.Status.OK, null);
            case USER_ALREADY_EXISTS:
                return new CommandResult(CommandType.USER_CREATE, CommandResult.Status.FAILED,
                        "Such user already exists");
            case BAD_LOGIN:
                return new CommandResult(CommandType.USER_CREATE, CommandResult.Status.FAILED,
                        "Bad login. Regex: " + AuthorizationService.getLoginRegex());
            case BAD_PASSWORD:
                return new CommandResult(CommandType.USER_CREATE, CommandResult.Status.FAILED,
                        "Bad pass. Regex: " + AuthorizationService.getPassRegex());

        }

        return new CommandResult(CommandType.USER_CREATE, CommandResult.Status.FAILED,
                "Unexpected error");
    }

    @Override
    public String getUsage() {
        return "/user_create login pass";
    }
}

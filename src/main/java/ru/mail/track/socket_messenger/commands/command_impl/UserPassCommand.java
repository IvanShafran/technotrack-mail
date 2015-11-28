package ru.mail.track.socket_messenger.commands.command_impl;

import ru.mail.track.socket_messenger.authorization.AuthorizationService;
import ru.mail.track.socket_messenger.commands.Command;
import ru.mail.track.socket_messenger.commands.CommandType;
import ru.mail.track.socket_messenger.commands.command_result.CommandResult;
import ru.mail.track.socket_messenger.message.Message;
import ru.mail.track.socket_messenger.message.message_impl.UserPassMessage;
import ru.mail.track.socket_messenger.session.Session;
import ru.mail.track.socket_messenger.user.User;

/**
 * Created by Ivan Shafran on 09.11.2015.
 * Mail: vanobox07@mail.ru
 */
public class UserPassCommand implements Command {
    private AuthorizationService authorizationService;

    public UserPassCommand(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    public CommandResult execute(Session session, Message message) {
        User user = session.getSessionUser();
        if (user == null) {
            return new CommandResult(CommandType.USER_PASS, CommandResult.Status.NOT_LOGGED, null);
        }

        UserPassMessage userPassMessage = (UserPassMessage) message;

        String login = user.getLogin();
        String oldPass = userPassMessage.getOldPass();
        String newPass = userPassMessage.getNewPass();

        if (oldPass == null || newPass == null) {
            return new CommandResult(CommandType.USER_PASS, CommandResult.Status.FAILED, "Please, check usage");
        }

        if (authorizationService.changePassword(login, oldPass, newPass) == AuthorizationService.Status.OK) {
            return new CommandResult(CommandType.USER_PASS, CommandResult.Status.OK, null);
        } else {
            return new CommandResult(CommandType.USER_PASS, CommandResult.Status.FAILED, "Wrong password");
        }
    }

    @Override
    public String getUsage() {
        return "/user_pass oldpass newpass";
    }
}

package main.java.ru.mail.track.socket_messenger.commands.command_impl;

import main.java.ru.mail.track.socket_messenger.authorization.AuthorizationService;
import main.java.ru.mail.track.socket_messenger.commands.Command;
import main.java.ru.mail.track.socket_messenger.commands.CommandType;
import main.java.ru.mail.track.socket_messenger.commands.command_result.CommandResult;
import main.java.ru.mail.track.socket_messenger.message.Message;
import main.java.ru.mail.track.socket_messenger.message.message_impl.UserPassMessage;
import main.java.ru.mail.track.socket_messenger.session.Session;
import main.java.ru.mail.track.socket_messenger.user.User;

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
        String oldPass = userPassMessage.getNewPass();
        String newPass = userPassMessage.getOldPass();

        if (oldPass == null || newPass == null) {
            return new CommandResult(CommandType.USER_PASS, CommandResult.Status.FAILED, "Please, check usage");
        }

        if (authorizationService.changePassword(login, oldPass, newPass) == AuthorizationService.Status.OK) {
            return new CommandResult(CommandType.USER_PASS, CommandResult.Status.OK, null);
        } else {
            return new CommandResult(CommandType.USER_PASS, CommandResult.Status.FAILED, "Wrong password or login");
        }
    }

    @Override
    public String getUsage() {
        return "/user_pass oldpass newpass";
    }
}

package main.java.ru.mail.track.socket_messenger.commands.command_result;

import main.java.ru.mail.track.socket_messenger.commands.CommandType;
import main.java.ru.mail.track.socket_messenger.user.User;

/**
 * Created by Ivan Shafran on 08.11.2015.
 * Mail: vanobox07@mail.ru
 */
public class LoginCommandResult extends UserInfoCommandResult {
    public LoginCommandResult(Status status, String report, User user) {
        super(CommandType.USER_LOGIN, status, report, user);
    }
}

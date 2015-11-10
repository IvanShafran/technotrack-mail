package main.java.socket_messenger.commands.command_result;

import main.java.socket_messenger.commands.CommandType;
import main.java.socket_messenger.message.PrivateUserDecorator;
import main.java.socket_messenger.user.User;

/**
 * Created by Ivan Shafran on 09.11.2015.
 * Mail: vanobox07@mail.ru
 */
public class UserInfoCommandResult extends CommandResult {
    private User user;

    public UserInfoCommandResult(CommandType commandType, Status status, String report, User user) {
        super(commandType, status, report);
        this.user = user;
    }

    public UserInfoCommandResult(Status status, String report, User user) {
        super(CommandType.USER_INFO, status, report);
        //hide pass
        this.user = new PrivateUserDecorator(user);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


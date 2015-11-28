package ru.mail.track.socket_messenger.commands.command_result;

import ru.mail.track.socket_messenger.commands.CommandType;
import ru.mail.track.socket_messenger.user.PrivateUserDecorator;
import ru.mail.track.socket_messenger.user.User;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInfoCommandResult)) return false;
        if (!super.equals(o)) return false;

        UserInfoCommandResult that = (UserInfoCommandResult) o;

        return !(getUser() != null ? !getUser().equals(that.getUser()) : that.getUser() != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        return result;
    }
}


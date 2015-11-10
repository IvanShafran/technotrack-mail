package main.java.socket_messenger.commands.command_impl;

import main.java.socket_messenger.commands.Command;
import main.java.socket_messenger.commands.CommandType;
import main.java.socket_messenger.commands.command_result.CommandResult;
import main.java.socket_messenger.commands.command_result.UserInfoCommandResult;
import main.java.socket_messenger.message.Message;
import main.java.socket_messenger.message.message_impl.UserInfoMessage;
import main.java.socket_messenger.session.Session;
import main.java.socket_messenger.user.User;
import main.java.socket_messenger.user.UserStore;

/**
 * Created by Ivan Shafran on 09.11.2015.
 * Mail: vanobox07@mail.ru
 */
public class UserInfoCommand implements Command {
    private UserStore userStore;

    public UserInfoCommand(UserStore userStore) {
        this.userStore = userStore;
    }

    @Override
    public CommandResult execute(Session session, Message message) {
        UserInfoMessage userInfoMessage = (UserInfoMessage)message;

        Long id = userInfoMessage.getInfoId();

        if (id == null) {
            if (session.getSessionUser() == null) {
                return new CommandResult(CommandType.USER_INFO, CommandResult.Status.NOT_LOGGED, null);
            } else {
                return new UserInfoCommandResult(CommandResult.Status.OK, null, session.getSessionUser());
            }
        } else {
            User user = userStore.getUserById(id);

            if (user == null) {
                return new CommandResult(CommandType.USER_INFO, CommandResult.Status.FAILED, "User doesn't exist");
            } else {
                return new UserInfoCommandResult(CommandResult.Status.OK, null, user);
            }
        }
    }

    @Override
    public String getUsage() {
        return "/user_info [id]";
    }
}

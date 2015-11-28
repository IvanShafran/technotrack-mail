package ru.mail.track.socket_messenger.commands.command_impl;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import ru.mail.track.socket_messenger.authorization.AuthorizationService;
import ru.mail.track.socket_messenger.commands.Command;
import ru.mail.track.socket_messenger.commands.CommandType;
import ru.mail.track.socket_messenger.commands.command_result.CommandResult;
import ru.mail.track.socket_messenger.commands.command_result.LoginCommandResult;
import ru.mail.track.socket_messenger.message.Message;
import ru.mail.track.socket_messenger.message.message_impl.LoginMessage;
import ru.mail.track.socket_messenger.net.SessionManager;
import ru.mail.track.socket_messenger.session.Session;
import ru.mail.track.socket_messenger.user.User;
import ru.mail.track.socket_messenger.user.UserStore;

/**
 * Выполняем авторизацию по этой команде
 */
public class LoginCommand implements Command {

    //static Logger log = LoggerFactory.getLogger(LoginCommand.class);

    private AuthorizationService authorizationService;
    private SessionManager sessionManager;
    private UserStore userStore;

    public LoginCommand(AuthorizationService authorizationService, SessionManager sessionManager, UserStore userStore) {
        this.authorizationService = authorizationService;
        this.sessionManager = sessionManager;
        this.userStore = userStore;
    }

    @Override
    public CommandResult execute(Session session, Message msg) {
        User user = session.getSessionUser();
        boolean wasLogged = false;
        if (user != null) {
            wasLogged = true;
        }

        LoginMessage loginMsg = (LoginMessage) msg;

        if (authorizationService.authorizeUser(loginMsg.getLogin(), loginMsg.getPass())
                != AuthorizationService.Status.OK) {

            return new CommandResult(CommandType.USER_LOGIN, CommandResult.Status.FAILED,
                        "wrong login or password");
        } else {
            user = userStore.getUser(loginMsg.getLogin());
            session.setSessionUser(user);
            sessionManager.registerUser(user.getId(), session.getId());
            //log.info("Success login: {}", user);

            if (wasLogged) {
                return new LoginCommandResult(CommandResult.Status.WARNING, "Relogin", user);
            } else {
                return new LoginCommandResult(CommandResult.Status.OK, null, user);
            }
        }
    }

    @Override
    public String getUsage() {
        return "/login <login> <pass>";
    }
}

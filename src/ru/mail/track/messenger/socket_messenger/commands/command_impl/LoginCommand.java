package ru.mail.track.messenger.socket_messenger.commands.command_impl;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import ru.mail.track.messenger.socket_messenger.authorization.AuthorizationService;
import ru.mail.track.messenger.socket_messenger.commands.Command;
import ru.mail.track.messenger.socket_messenger.commands.CommandType;
import ru.mail.track.messenger.socket_messenger.commands.command_result.CommandResult;
import ru.mail.track.messenger.socket_messenger.commands.command_result.LoginCommandResult;
import ru.mail.track.messenger.socket_messenger.message.Message;
import ru.mail.track.messenger.socket_messenger.message.message_impl.LoginMessage;
import ru.mail.track.messenger.socket_messenger.net.SessionManager;
import ru.mail.track.messenger.socket_messenger.session.Session;
import ru.mail.track.messenger.socket_messenger.user.User;

/**
 * Выполняем авторизацию по этой команде
 */
public class LoginCommand implements Command {

    //static Logger log = LoggerFactory.getLogger(LoginCommand.class);

    private AuthorizationService authorizationService;
    private SessionManager sessionManager;

    public LoginCommand(AuthorizationService authorizationService, SessionManager sessionManager) {
        this.authorizationService = authorizationService;
        this.sessionManager = sessionManager;
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

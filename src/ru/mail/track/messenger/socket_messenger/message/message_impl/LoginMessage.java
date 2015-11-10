package ru.mail.track.messenger.socket_messenger.message.message_impl;

import ru.mail.track.messenger.socket_messenger.commands.CommandType;

/**
 *
 */
public class LoginMessage extends UserMessage {
    private String login;
    private String pass;

    public LoginMessage(String login, String pass) {
        super(CommandType.USER_LOGIN);
        this.login = login;
        this.pass = pass;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}

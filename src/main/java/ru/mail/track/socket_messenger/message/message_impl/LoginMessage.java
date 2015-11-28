package ru.mail.track.socket_messenger.message.message_impl;

import ru.mail.track.socket_messenger.commands.CommandType;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginMessage)) return false;
        if (!super.equals(o)) return false;

        LoginMessage that = (LoginMessage) o;

        if (getLogin() != null ? !getLogin().equals(that.getLogin()) : that.getLogin() != null) return false;
        return !(getPass() != null ? !getPass().equals(that.getPass()) : that.getPass() != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getPass() != null ? getPass().hashCode() : 0);
        return result;
    }
}

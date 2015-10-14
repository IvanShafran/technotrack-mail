package ru.mail.track.messenger.authorization;

/**
 * Created by Ivan Shafran on 28.09.2015.
 * Mail: vanobox07@mail.ru
 */

public class User {
    private String login;
    private int passwordHashCode;

    public User(String login, int passwordHashCode) {
        this.login = login;
        this.passwordHashCode = passwordHashCode;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getPasswordHashCode() {
        return passwordHashCode;
    }

    public void setPasswordHashCode(int passwordHashCode) {
        this.passwordHashCode = passwordHashCode;
    }
}

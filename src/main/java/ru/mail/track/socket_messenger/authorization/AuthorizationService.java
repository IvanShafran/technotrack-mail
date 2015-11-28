package ru.mail.track.socket_messenger.authorization;

import ru.mail.track.socket_messenger.user.User;
import ru.mail.track.socket_messenger.user.UserImpl;
import ru.mail.track.socket_messenger.user.UserStore;

/**
 * Created by Ivan Shafran on 09.11.2015.
 * Mail: vanobox07@mail.ru
 */
public class AuthorizationService {
    final static private String loginRegex = "^[a-z0-9_-]{6,20}$";
    final static private String passRegex = "^.{6,40}$";

    public enum Status {
        OK,
        WRONG_LOGIN_OR_PASSWORD,
        BAD_LOGIN,
        BAD_PASSWORD,
        USER_ALREADY_EXISTS
    }

    private UserStore userStore;

    public AuthorizationService(UserStore userStore) {
        this.userStore = userStore;
    }

    public Status registerUser(String login, String pass) {
        if (!login.matches(loginRegex)) {
            return Status.BAD_LOGIN;
        }

        if (!pass.matches(passRegex)) {
            return Status.BAD_PASSWORD;
        }

        User user = userStore.getUser(login);
        if (user != null) {
            return Status.USER_ALREADY_EXISTS;
        } else {
            user = new UserImpl(login, pass);
            userStore.addUser(user);
            return Status.OK;
        }
    }

    public Status authorizeUser(String login, String pass) {
        if (!login.matches(loginRegex)) {
            return Status.BAD_LOGIN;
        }

        if (!pass.matches(passRegex)) {
            return Status.BAD_PASSWORD;
        }

        User user = userStore.getUser(login);

        if (user == null || !user.getPass().equals(pass)) {
            return Status.WRONG_LOGIN_OR_PASSWORD;
        } else {
            return Status.OK;
        }
    }

    public Status changePassword(String login, String oldPass, String newPass) {
        if (!oldPass.matches(passRegex)) {
            return Status.BAD_PASSWORD;
        }

        if (!newPass.matches(passRegex)) {
            return Status.BAD_PASSWORD;
        }

        User user = userStore.getUser(login);

        if (user == null || !user.getPass().equals(oldPass)) {
            return Status.WRONG_LOGIN_OR_PASSWORD;
        } else {
            user.setPass(newPass);
            return Status.OK;
        }
    }

    public static String getLoginRegex() {
        return loginRegex;
    }

    public static String getPassRegex() {
        return passRegex;
    }
}

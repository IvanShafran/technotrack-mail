package ru.mail.track.messenger.authorization.userstorage.usersteward;

import ru.mail.track.messenger.authorization.User;

import java.util.HashMap;

/**
 * Created by Ivan Shafran on 28.09.2015.
 * Mail: vanobox07@mail.ru
 */

public interface UserSteward {
    HashMap<String, User> readUsers() throws ReadUsersException;
    void saveUsers(HashMap<String, User> users) throws SaveUsersException;

    String LOGIN_REGEX = "^[a-z0-9_-]{6,20}$";
    int LOGIN_MIN_LENGTH = 6;
    int LOGIN_MAX_LENGTH = 20;

    String PASSWORD_REGEX = "^.{6,40}$";
    int PASSWORD_MIN_LENGTH = 6;
    int PASSWORD_MAX_LENGTH = 40;

    static RegexCheckingStatus checkLogin(String login) {
        if (login.length() < LOGIN_MIN_LENGTH) {
            return RegexCheckingStatus.TOO_SHORT;
        }

        if (login.length() > LOGIN_MAX_LENGTH) {
            return RegexCheckingStatus.TOO_LONG;
        }

        if (!login.matches(LOGIN_REGEX)) {
            return RegexCheckingStatus.UNALLOWED_SYMBOLS;
        }

        return RegexCheckingStatus.OK;
    }

    static RegexCheckingStatus checkPassword(String password) {
        if (password.length() < PASSWORD_MIN_LENGTH) {
            return RegexCheckingStatus.TOO_SHORT;
        }

        if (password.length() > PASSWORD_MAX_LENGTH) {
            return RegexCheckingStatus.TOO_LONG;
        }

        if (!password.matches(PASSWORD_REGEX)) {
            return RegexCheckingStatus.UNALLOWED_SYMBOLS;
        }

        return RegexCheckingStatus.OK;
    }
}

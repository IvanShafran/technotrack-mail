package ru.mail.track.messenger.authorization;

import com.sun.istack.internal.NotNull;

/**
 * Created by Ivan Shafran on 08.10.2015.
 * Mail: vanobox07@mail.ru
 */
public class UserFieldsChecking {
    static final String LOGIN_REGEX = "^[a-z0-9_-]{6,20}$";
    static final int LOGIN_MIN_LENGTH = 6;
    static final int LOGIN_MAX_LENGTH = 20;

    static final String PASSWORD_REGEX = "^.{6,40}$";
    static final int PASSWORD_MIN_LENGTH = 6;
    static final int PASSWORD_MAX_LENGTH = 40;

    @NotNull
    public UserFieldsCheckingStatus checkLogin(String login) {
        if (login.length() < LOGIN_MIN_LENGTH) {
            return UserFieldsCheckingStatus.TOO_SHORT;
        }

        if (login.length() > LOGIN_MAX_LENGTH) {
            return UserFieldsCheckingStatus.TOO_LONG;
        }

        if (!login.matches(LOGIN_REGEX)) {
            return UserFieldsCheckingStatus.UNALLOWED_SYMBOLS;
        }

        return UserFieldsCheckingStatus.OK;
    }

    @NotNull
    public UserFieldsCheckingStatus checkPassword(String password) {
        if (password.length() < PASSWORD_MIN_LENGTH) {
            return UserFieldsCheckingStatus.TOO_SHORT;
        }

        if (password.length() > PASSWORD_MAX_LENGTH) {
            return UserFieldsCheckingStatus.TOO_LONG;
        }

        if (!password.matches(PASSWORD_REGEX)) {
            return UserFieldsCheckingStatus.UNALLOWED_SYMBOLS;
        }

        return UserFieldsCheckingStatus.OK;
    }
}

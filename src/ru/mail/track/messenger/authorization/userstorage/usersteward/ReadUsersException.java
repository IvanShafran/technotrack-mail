package ru.mail.track.messenger.authorization.userstorage.usersteward;

/**
 * Created by Ivan Shafran on 29.09.2015.
 * Mail: vanobox07@mail.ru
 */
public class ReadUsersException extends Exception {
    public ReadUsersException(String message) {
        super(message);
    }

    public ReadUsersException() {
    }
}

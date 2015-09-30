package ru.mail.track.messenger.authorization.userstorage;

/**
 * Created by Ivan Shafran on 29.09.2015.
 * Mail: vanobox07@mail.ru
 */
public class UserStorageDidNotRead extends Exception {
    public UserStorageDidNotRead() {
    }

    public UserStorageDidNotRead(String message) {
        super(message);
    }
}

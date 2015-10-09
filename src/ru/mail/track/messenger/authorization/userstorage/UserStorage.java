package ru.mail.track.messenger.authorization.userstorage;

import com.sun.istack.internal.Nullable;
import ru.mail.track.messenger.authorization.User;

import java.util.HashMap;

/**
 * Created by Ivan Shafran on 28.09.2015.
 * Mail: vanobox07@mail.ru
 */
abstract public class UserStorage {
    //login -> user
    protected HashMap<String, User> users;

    abstract public void startFileStorageWork();
    //closing file descriptor for example
    abstract public void finishFileStorageWork();

    public boolean isUserExist(String login) {
        if (users == null) {
            System.err.println("User storage didn't read");
            return false;
        }

        return users.containsKey(login);
    }

    public void addUser(User user) {
        if (isUserExist(user.getLogin())) {
            System.err.println("User has already registered");
            return;
        }

        users.put(user.getLogin(), user);
    }

    public boolean verifyUser(String login, String password) {
        return isUserExist(login) && users.get(login).getPasswordHashCode() == password.hashCode();
    }

    @Nullable
    public User getUser(String login) {
        if (!isUserExist(login)) {
            return null;
        }

        return users.get(login);
    }
}

package ru.mail.track.messenger.authorization.userstorage;

import ru.mail.track.messenger.authorization.User;
import ru.mail.track.messenger.authorization.userstorage.usersteward.UserManager;

import java.util.HashMap;

/**
 * Created by Ivan Shafran on 28.09.2015.
 * Mail: vanobox07@mail.ru
 */
abstract public class UserStorage implements UserManager {
    //login -> user
    protected HashMap<String, User> users;

    public Boolean isUserExist(String login) {
        if (users == null) {
            System.err.println("User storage didn't read");
            return null;
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

    public User getUser(String login) {
        if (!isUserExist(login)) {
            return null;
        }

        return users.get(login);
    }
}

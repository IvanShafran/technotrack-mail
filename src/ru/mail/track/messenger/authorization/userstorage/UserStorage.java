package ru.mail.track.messenger.authorization.userstorage;

import ru.mail.track.messenger.authorization.User;
import ru.mail.track.messenger.authorization.userstorage.usersteward.ReadUsersException;
import ru.mail.track.messenger.authorization.userstorage.usersteward.SaveUsersException;
import ru.mail.track.messenger.authorization.userstorage.usersteward.UserSteward;

import java.util.HashMap;

/**
 * Created by Ivan Shafran on 28.09.2015.
 * Mail: vanobox07@mail.ru
 */
public class UserStorage {
    //login -> user
    private HashMap<String, User> users;
    private UserSteward userSteward;

    public UserStorage(UserSteward userSteward) {
        this.userSteward = userSteward;
    }

    public boolean isUserExist(String login) throws UserStorageDidNotRead {
        if (users == null) {
            System.err.println("User storage didn't read");
            throw new UserStorageDidNotRead();
        }

        return users.containsKey(login);
    }

    public void addUser(User user) throws UserAlreadyExistException, UserStorageDidNotRead {
        if (isUserExist(user.getLogin())) {
            throw new UserAlreadyExistException();
        }

        users.put(user.getLogin(), user);
    }

    public boolean verifyUser(String login, String password) throws UserStorageDidNotRead {
        if (!isUserExist(login)) {
            return false;
        }

        return users.get(login).getPasswordHashCode() == password.hashCode();
    }

    public User getUser(String login) throws UserDoesNotExistException, UserStorageDidNotRead {
        if (!isUserExist(login)) {
            throw new UserDoesNotExistException();
        }

        return users.get(login);
    }

    public void readUsers() throws ReadUsersException {
        users = userSteward.readUsers();
    }

    public void saveUsers() throws SaveUsersException {
        userSteward.saveUsers(users);
    }
}

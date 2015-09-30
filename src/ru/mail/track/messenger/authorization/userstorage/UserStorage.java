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

    public boolean isUser(User user) throws UserStorageDidNotRead {
        if (users == null) {
            throw new UserStorageDidNotRead();
        }

        return users.containsKey(user.getLogin());
    }

    public void addUser(User user) throws UserAlreadyExistException, UserStorageDidNotRead{
        if (isUser(user)) {
            throw new UserAlreadyExistException();
        }

        users.put(user.getLogin(), user);
    }

    public boolean verifyUser(User user) throws UserDoesNotExistException, UserStorageDidNotRead {
        if (!isUser(user)) {
            throw new UserDoesNotExistException();
        }

        return users.get(user.getLogin()).getPasswordHashCode() == user.getPasswordHashCode();
    }

    public void readUsers() throws ReadUsersException {
        users = userSteward.readUsers();
    }

    public void saveUsers() throws SaveUsersException {
        userSteward.saveUsers(users);
    }
}

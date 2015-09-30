package ru.mail.track.messenger.authorization.userauthorization;

import ru.mail.track.messenger.authorization.User;
import ru.mail.track.messenger.authorization.userstorage.UserAlreadyExistException;
import ru.mail.track.messenger.authorization.userstorage.UserDoesNotExistException;
import ru.mail.track.messenger.authorization.userstorage.UserStorage;
import ru.mail.track.messenger.authorization.userstorage.UserStorageDidNotRead;
import ru.mail.track.messenger.authorization.userstorage.usersteward.RegexCheckingStatus;
import ru.mail.track.messenger.authorization.userstorage.usersteward.UserSteward;

import java.util.Scanner;

/**
 * Created by Ivan Shafran on 28.09.2015.
 * Mail: vanobox07@mail.ru
 */
public class AuthorizationService {
    private UserStorage userStorage;
    private Scanner in;
    private boolean isLogin = false;
    private User authorizedUser;

    public AuthorizationService(Scanner in, UserStorage userStorage) {
        this.in = in;
        this.userStorage = userStorage;
    }

    private void printUsage() {
        System.out.println("Usage: \nAuthorization: login password\nRegistration: login password --reg");
    }

    private boolean handleStringRegexResults(RegexCheckingStatus status, String string_name) {
        switch (status) {
            case OK:
                return true;
            case TOO_SHORT:
                System.out.println(string_name + " is too short");
                return false;
            case TOO_LONG:
                System.out.println(string_name + " is too long");
                return false;
            case UNALLOWED_SYMBOLS:
                System.out.println(string_name + " contains unallowed symbols");

                if (string_name.equals("login")) {
                    System.out.println("login regex==" + UserSteward.LOGIN_REGEX);
                } else if (string_name.equals("password")) {
                    System.out.println("password regex==" + UserSteward.PASSWORD_REGEX);
                }
                return false;
            default:
                //????
                return false;
        }
    }

    private void tryToRegister(String login, String password) {
        if (!handleStringRegexResults(UserSteward.checkLogin(login), "login")) {
            //printUsage();
            return;
        }

        if (!handleStringRegexResults(UserSteward.checkPassword(password), "password")) {
            //printUsage();
            return;
        }

        try {
            userStorage.addUser(new User(login, password.hashCode()));
        } catch (UserAlreadyExistException e) {
            System.out.println("User has already existed");
            return;
        } catch (UserStorageDidNotRead e) {
            System.out.println("User storage did not read");
            return;
        }

        System.out.println("User has successfully registered");
    }

    private void tryToLogin(User user) {
        try {
            if (userStorage.verifyUser(user)) {
                isLogin = true;
                authorizedUser = user;
                System.out.println("You are logged!");
            } else {
                System.out.println("Wrong password");
            }
        } catch (UserDoesNotExistException e) {
            System.out.println("Wrong login");
        } catch (UserStorageDidNotRead e) {
            System.out.println("User storage didn't read");
        }
    }

    public void startAuthorization() {
        printUsage();

        String line = in.nextLine();
        if (line == null) {
            return;
        }

        String[] words = line.trim().split("\\s+");

        if (words.length == 2) {
            tryToLogin(new User(words[0], words[1].hashCode()));
        } else if (words.length == 3 && words[2].equals("--reg")) {
            tryToRegister(words[0], words[1]);
        } else {
            printUsage();
        }
    }

    public boolean isLogin() {
        return isLogin;
    }

    public User getAuthorizedUser() {
        return authorizedUser;
    }
}

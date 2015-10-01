package ru.mail.track.messenger.authorization.userauthorization;

import ru.mail.track.messenger.authorization.User;
import ru.mail.track.messenger.authorization.userstorage.UserAlreadyExistException;
import ru.mail.track.messenger.authorization.userstorage.UserDoesNotExistException;
import ru.mail.track.messenger.authorization.userstorage.UserStorage;
import ru.mail.track.messenger.authorization.userstorage.UserStorageDidNotRead;
import ru.mail.track.messenger.authorization.userstorage.usersteward.RegexCheckingStatus;
import ru.mail.track.messenger.authorization.userstorage.usersteward.UserSteward;

import java.io.Console;
import java.util.Scanner;

/**
 * Created by Ivan Shafran on 28.09.2015.
 * Mail: vanobox07@mail.ru
 */
public class AuthorizationService {
    private UserStorage userStorage;
    private Scanner in;
    private boolean isLogged = false;
    private User authorizedUser;

    public AuthorizationService(Scanner in, UserStorage userStorage) {
        this.in = in;
        this.userStorage = userStorage;
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

    private String readPassword() {
        Console console = System.console();
        if (console == null) {
            return in.nextLine();
        } else {
            return new String(console.readPassword());
        }
    }

    public void tryToRegister(String login) throws UserStorageDidNotRead {
        System.out.print("Would you like to register? (y/n): ");

        String answer = in.nextLine();
        if (answer != null) {
            if (answer.equals("y")) {
                if (!handleStringRegexResults(UserSteward.checkLogin(login), "login")) {
                    return;
                }

                System.out.print("Password:");
                String password = readPassword();
                if (password != null) {
                    if (!handleStringRegexResults(UserSteward.checkPassword(password), "password")) {
                        return;
                    }

                    try {
                        userStorage.addUser(new User(login, password.hashCode()));
                        System.out.println("You has successfully registered!");
                    } catch (UserAlreadyExistException e) {
                        //checked before
                    }
                }
            }
        }
    }

    private void tryToLogin(String login) throws UserStorageDidNotRead {
        System.out.print("Password:");
        String password = readPassword();
        if (password != null) {
            try {
                if (userStorage.verifyUser(login, password)) {
                    authorizedUser = userStorage.getUser(login);
                    isLogged = true;

                    System.out.println("You are logged!");
                } else {
                    System.out.println("Wrong password or login");
                }
            } catch (UserDoesNotExistException e) {
                //checked before
            }
        }
    }

    public void startAuthorization() throws UserStorageDidNotRead{
        System.out.print("Login:");

        String login = in.nextLine();
        if (login == null) {
            return;
        }

        if (userStorage.isUserExist(login)) {
            tryToLogin(login);
        } else {
            tryToRegister(login);
        }
    }

    public boolean isLogged() {
        return isLogged;
    }

    public User getAuthorizedUser() {
        return authorizedUser;
    }
}

package ru.mail.track.messenger.authorization.userauthorization;

import ru.mail.track.messenger.authorization.User;
import ru.mail.track.messenger.authorization.userstorage.UserStorage;
import ru.mail.track.messenger.authorization.userstorage.usersteward.RegexCheckingStatus;
import ru.mail.track.messenger.authorization.userstorage.usersteward.UserManager;

import java.io.Console;
import java.util.Scanner;

/**
 * Created by Ivan Shafran on 28.09.2015.
 * Mail: vanobox07@mail.ru
 */
public class AuthorizationService {
    private UserStorage userStorage;
    private Scanner scanner;

    public AuthorizationService(UserStorage userStorage) {
        this.scanner = new Scanner(System.in);
        this.userStorage = userStorage;
    }

    private String readPassword() {
        Console console = System.console();
        if (console == null) {
            return scanner.nextLine();
        } else {
            return new String(console.readPassword());
        }
    }

    public User tryToRegister(String login) {
        System.out.print("Would you like to register? (y/n): ");

        String answer = scanner.nextLine();
        if (answer != null) {
            if (answer.equals("y")) {
                RegexCheckingStatus loginStatus = UserManager.checkLogin(login);
                if (loginStatus != RegexCheckingStatus.OK) {
                    System.out.println("Bad login: " + loginStatus);
                    return null;
                }

                System.out.print("Password: ");
                String password = readPassword();
                if (password != null) {
                    RegexCheckingStatus passwordStatus = UserManager.checkPassword(password);
                    if (passwordStatus != RegexCheckingStatus.OK) {
                        System.out.println("Bad password " + passwordStatus);
                        return null;
                    }

                    userStorage.addUser(new User(login, password.hashCode()));
                    System.out.println("You has successfully registered!");

                    return userStorage.getUser(login);
                }
            }
        }

        return null;
    }

    private User tryToLogin(String login) {
        System.out.print("Password:");
        String password = readPassword();
        if (password != null) {
            if (userStorage.verifyUser(login, password)) {
                System.out.println("You are logged!");
                return userStorage.getUser(login);
            } else {
                System.out.println("Wrong password or login");
                return null;
            }
        }

        return null;
    }

    public User startAuthorization() {
        System.out.print("Login:");

        String login = scanner.nextLine();
        if (login == null) {
            return null;
        }

        if (userStorage.isUserExist(login)) {
            return tryToLogin(login);
        } else {
            return tryToRegister(login);
        }
    }
}

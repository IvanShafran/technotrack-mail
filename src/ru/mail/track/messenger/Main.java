package ru.mail.track.messenger;

import ru.mail.track.messenger.authorization.userauthorization.AuthorizationService;
import ru.mail.track.messenger.authorization.userstorage.UserStorage;
import ru.mail.track.messenger.authorization.userstorage.UserStorageDidNotRead;
import ru.mail.track.messenger.authorization.userstorage.usersteward.ReadUsersException;
import ru.mail.track.messenger.authorization.userstorage.usersteward.SaveUsersException;
import ru.mail.track.messenger.authorization.userstorage.usersteward.UserFileSteward;

import java.util.Scanner;

/**
 * Created by Ivan Shafran on 28.09.2015.
 * Mail: vanobox07@mail.ru
 */
public class Main {
    public static void main(String[] args) {
        UserStorage userStorage = new UserStorage(new UserFileSteward("C:\\1\\base.txt"));

        try {
            userStorage.readUsers();
        } catch (ReadUsersException e) {
            //
        }

        Scanner scanner = new Scanner(System.in);
        AuthorizationService authorization = new AuthorizationService(scanner, userStorage);
        try {
            while (true) {
                String line = scanner.nextLine();
                if (line == null) {
                    continue;
                } else if (line.equals("exit")) {
                    try {
                        userStorage.saveUsers();
                    } catch (SaveUsersException e) {
                        System.out.println("error with saving");
                    }
                    break;
                } else {
                    authorization.startAuthorization();
                }
            }
        } catch (UserStorageDidNotRead e) {
            //!!!
        }
    }
}

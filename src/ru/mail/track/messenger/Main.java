package ru.mail.track.messenger;

import ru.mail.track.messenger.authorization.userauthorization.AuthorizationService;
import ru.mail.track.messenger.authorization.userstorage.FileUserStorage;
import ru.mail.track.messenger.authorization.userstorage.UserStorage;
import ru.mail.track.messenger.authorization.userstorage.UserStorageDidNotRead;

import java.util.Scanner;

/**
 * Created by Ivan Shafran on 28.09.2015.
 * Mail: vanobox07@mail.ru
 */
public class Main {
    public static void main(String[] args) {
        UserStorage userStorage = new FileUserStorage("C:\\1\\base.txt");
        userStorage.readUsers();

        AuthorizationService authorization = new AuthorizationService(userStorage);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            if (line != null) {
                if (line.equals("exit")) {
                    userStorage.saveUsers();
                    break;
                } else {
                    authorization.startAuthorization();
                }
            }
        }
    }
}

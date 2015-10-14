package ru.mail.track.messenger;

import ru.mail.track.messenger.authorization.AuthorizationService;
import ru.mail.track.messenger.authorization.userstorage.FileUserStorage;
import ru.mail.track.messenger.authorization.userstorage.UserStorage;

import java.util.Scanner;

/**
 * Created by Ivan Shafran on 28.09.2015.
 * Mail: vanobox07@mail.ru
 */
public class Main {
    static final String pathToFile = "C:\\1\\base.txt";

    public static void main(String[] args) {
        UserStorage userStorage = new FileUserStorage(pathToFile);
        userStorage.startFileStorageWork();

        AuthorizationService authorization = new AuthorizationService(userStorage);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();

            if (line != null) {
                if (line.equals("exit")) {
                    userStorage.finishFileStorageWork();
                    break;
                } else {
                    authorization.startAuthorization();
                }
            }
        }
    }
}

package ru.mail.track.messenger;

import ru.mail.track.messenger.authorization.AuthorizationService;
import ru.mail.track.messenger.authorization.userstorage.FileUserStorage;
import ru.mail.track.messenger.authorization.userstorage.UserStorage;
import ru.mail.track.messenger.chat.ChatClient;
import ru.mail.track.messenger.chat.FileMessageManager;
import ru.mail.track.messenger.chat.FileMessageManagerCreatingException;

import java.util.Scanner;

/**
 * Created by Ivan Shafran on 28.09.2015.
 * Mail: vanobox07@mail.ru
 */
public class Main {
    static final String pathToFile = "C:\\1\\base.txt";
    static final String pathToMessages = "D:\\messages";

    public static void main(String[] args) {
        UserStorage userStorage = new FileUserStorage(pathToFile);
        userStorage.startFileStorageWork();

        AuthorizationService authorization = new AuthorizationService(userStorage);

        FileMessageManager messageManager;
        try {
            messageManager = new FileMessageManager(pathToMessages);
        } catch (FileMessageManagerCreatingException e) {
            System.err.println(e.getMessage());
            return;
        }

        ChatClient chatClient = new ChatClient(authorization, messageManager);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (chatClient.getUser() != null) {
                System.out.print(chatClient.getNickname() + " :");
            } else {
                System.out.print("Please, sign in by --login: ");
            }
            String line = scanner.nextLine();

            if (line != null) {
                if (line.equals("--exit")) {
                    chatClient.close();
                    break;
                } else {
                    chatClient.addMessage(line);
                }
            }
        }
    }
}

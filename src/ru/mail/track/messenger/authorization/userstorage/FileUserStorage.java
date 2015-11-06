package ru.mail.track.messenger.authorization.userstorage;

import ru.mail.track.messenger.authorization.User;
import ru.mail.track.messenger.authorization.UserFieldsChecking;
import ru.mail.track.messenger.authorization.UserFieldsCheckingStatus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Ivan Shafran on 04.10.2015.
 * Mail: vanobox07@mail.ru
 */
public class FileUserStorage extends UserStorage {
    String pathToFile;
    BufferedWriter bufferedWriter;

    public FileUserStorage(String pathToFile) {
        this.pathToFile = pathToFile;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(pathToFile, true));
        } catch (IOException e) {
            System.err.println("Error with opening: " + pathToFile);
        }
    }

    @Override
    public void startFileStorageWork() {
        UserFieldsChecking userFieldsChecking = new UserFieldsChecking();
        HashMap<String, User> users = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            //[0] - login, [1] - passwordHashCode
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.trim().split("\\s+");

                if (words.length != 2) {
                    throw new IOException("broken login/password database on path: " + pathToFile);
                }

                if (userFieldsChecking.checkLogin(words[0]) != UserFieldsCheckingStatus.OK) {
                    throw new IOException("broken login/password database on path: " + pathToFile);
                }

                User user;
                try {
                    user = new User(words[0], Integer.parseInt(words[1]));
                } catch (NumberFormatException e) {
                    throw new IOException("broken login/password database on path: " + pathToFile);
                }

                users.put(user.getLogin(), user);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        super.users = users;
    }

    private void saveUser(User user) {
        try {
            bufferedWriter.write(user.getLogin() + " " + user.getPasswordHashCode() + "\n");
        } catch (IOException e) {
            System.err.println("Error during data writing");
        }
    }

    @Override
    public void addUser(User user) {
        super.addUser(user);
        saveUser(user);
    }

    @Override
    public void close() {
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            //just relax
        }
    }
}

package ru.mail.track.messenger.authorization.userstorage;

import ru.mail.track.messenger.authorization.User;
import ru.mail.track.messenger.authorization.userstorage.usersteward.RegexCheckingStatus;
import ru.mail.track.messenger.authorization.userstorage.usersteward.UserManager;

import java.io.*;
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
    public void readUsers() {
        HashMap<String, User> users = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            //[0] - login, [1] - passwordHashCode
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.trim().split("\\s+");

                if (words.length != 2) {
                    throw new IOException("broken login/password database on path: " + pathToFile);
                }

                if (UserManager.checkLogin(words[0]) != RegexCheckingStatus.OK) {
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

    @Override
    public void saveUsers() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathToFile))) {
            for (User user : users.values()) {
                bufferedWriter.write(user.getLogin() + " " + user.getPasswordHashCode() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error during data writing");
        }
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
}

package ru.mail.track.messenger.authorization.userstorage.usersteward;

import ru.mail.track.messenger.authorization.User;

import java.io.*;
import java.util.HashMap;

/**
 * Created by Ivan Shafran on 28.09.2015.
 * Mail: vanobox07@mail.ru
 */
public class UserFileSteward implements UserSteward {
    private String path;

    public UserFileSteward(String path) {
        this.path = path;
    }

    private HashMap<String, User> readFromFile() throws IOException {
        HashMap<String, User> users = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            //[0] - login, [1] - passwordHashCode
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.trim().split("\\s+");

                if (words.length != 2) {
                    throw new IOException("broken login/password database on path: " + path);
                }

                if (UserSteward.checkLogin(words[0]) != RegexCheckingStatus.OK) {
                    throw new IOException("broken login/password database on path: " + path);
                }

                User user;
                try {
                    user = new User(words[0], Integer.parseInt(words[1]));
                } catch (NumberFormatException e) {
                    throw new IOException("broken login/password database on path: " + path);
                }

                users.put(user.getLogin(), user);
            }
        }

        return users;
    }

    @Override
    public HashMap<String, User> readUsers() throws ReadUsersException {
        try {
            return readFromFile();
        } catch (IOException e) {
            throw new ReadUsersException(e.getMessage());
        }
    }

    private void saveToFile(HashMap<String, User> users) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (User user : users.values()) {
                bw.write(user.getLogin() + " " + user.getPasswordHashCode() + "\n");
            }
        }
    }

    @Override
    public void saveUsers(HashMap<String, User> users) throws SaveUsersException {
        try {
            saveToFile(users);
        } catch (IOException e) {
            throw new SaveUsersException(e.getMessage());
        }
    }
}

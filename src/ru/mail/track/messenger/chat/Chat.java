package ru.mail.track.messenger.chat;

import com.sun.istack.internal.Nullable;
import ru.mail.track.messenger.authorization.AuthorizationService;
import ru.mail.track.messenger.authorization.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Ivan Shafran on 13.10.2015.
 * Mail: vanobox07@mail.ru
 */
public class Chat {
    private User user;
    private User friend;
    //friend login - > messages
    private Map<String, List<String>> messages;
    private Map<String, Command> commands;
    private String nickname;
    private AuthorizationService authorizationService;

    private void initializeCommands() {
        commands = new HashMap<String, Command>();

        Command command = new CommandHelp(this);
        commands.put(command.getName(), command);

        command = new CommandNickname(this);
        commands.put(command.getName(), command);

        command = new CommandHistory(this);
        commands.put(command.getName(), command);

        command = new CommandFind(this);
        commands.put(command.getName(), command);
    }

    public Chat(AuthorizationService service) {
        initializeCommands();
        this.authorizationService = service;
    }

    public void addMessage(String message) {
        if (user == null) {
            return;
        }

        String[] args = message.trim().split("\\s+");
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    @Nullable
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Nullable
    public Map<String, List<String>> getMessages() {
        return messages;
    }

    @Nullable
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        messages = new HashMap<String, List<String>>();
        nickname = user.getLogin();
    }

    @Nullable
    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public AuthorizationService getAuthorizationService() {
        return authorizationService;
    }
}

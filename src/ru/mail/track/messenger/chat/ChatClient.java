package ru.mail.track.messenger.chat;

import com.sun.istack.internal.Nullable;
import ru.mail.track.messenger.authorization.AuthorizationService;
import ru.mail.track.messenger.authorization.User;
import ru.mail.track.messenger.chat.commands.*;

import java.util.*;


/**
 * Created by Ivan Shafran on 13.10.2015.
 * Mail: vanobox07@mail.ru
 */
public class ChatClient {
    private User user;
    private String friendLogin;
    //friend login - > messages
    private MessageManager messageManager;
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

        command = new CommandLogin(this);
        commands.put(command.getName(), command);

        command = new CommandLogout(this);
        commands.put(command.getName(), command);

        command = new CommandFriend(this);
        commands.put(command.getName(), command);

        command = new CommandExit(this);
        commands.put(command.getName(), command);
    }

    public ChatClient(AuthorizationService service, MessageManager messageManager) {
        initializeCommands();
        this.authorizationService = service;
        this.messageManager = messageManager;
    }

    public void addMessage(String text) {
        String[] args = text.trim().split("\\s+");

        if (commands.containsKey(args[0])) {
            commands.get(args[0]).execute(args);
        } else if (user != null) {
            try {
                messageManager.addMessage(user.getLogin(), friendLogin, new MessageImpl(new Date(), text));
            } catch (AddMessageException e) {
                System.err.println("Message didn't send");
            }
        }
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

    public List<Message> getMessages() {
        return messageManager.getMessages(user.getLogin(), friendLogin);
    }

    @Nullable
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;

        if (user != null) {
            nickname = user.getLogin();
            setFriendLogin(user.getLogin());
        }
    }

    @Nullable
    public String getFriendLogin() {
        return friendLogin;
    }

    public void setFriendLogin(String friendLogin) {
        this.friendLogin = friendLogin;
    }

    public AuthorizationService getAuthorizationService() {
        return authorizationService;
    }

    public void close() {
        messageManager.close();
        authorizationService.close();
    }
}

package ru.mail.track.messenger.chat.commands;

import ru.mail.track.messenger.authorization.User;
import ru.mail.track.messenger.chat.ChatClient;

/**
 * Created by Ivan Shafran on 14.10.2015.
 * Mail: vanobox07@mail.ru
 */
public class CommandLogin implements Command {
    ChatClient chatClient;

    public CommandLogin(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public String getName() {
        return "--login";
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            return;
        }

        User user = chatClient.getAuthorizationService().startAuthorization();
        if (user != null) {
            chatClient.setUser(user);
        }
    }

    @Override
    public String getUsage() {
        return "Usage Login: --login";
    }
}

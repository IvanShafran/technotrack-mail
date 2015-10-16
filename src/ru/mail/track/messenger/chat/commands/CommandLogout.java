package ru.mail.track.messenger.chat.commands;

import ru.mail.track.messenger.chat.ChatClient;

/**
 * Created by Ivan Shafran on 14.10.2015.
 * Mail: vanobox07@mail.ru
 */
public class CommandLogout implements Command {
    ChatClient chatClient;

    public CommandLogout(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public String getName() {
        return "--logout";
    }

    @Override
    public void execute(String[] args) {
        chatClient.setUser(null);
    }

    @Override
    public String getUsage() {
        return "Usage LogOut: --logout";
    }
}

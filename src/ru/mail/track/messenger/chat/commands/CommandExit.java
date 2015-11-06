package ru.mail.track.messenger.chat.commands;

import ru.mail.track.messenger.chat.ChatClient;

/**
 * Created by Ivan Shafran on 15.10.2015.
 * Mail: vanobox07@mail.ru
 */
public class CommandExit implements Command {
    ChatClient chatClient;

    public CommandExit(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public String getName() {
        return "--exit";
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            return;
        }

        chatClient.close();
        System.exit(0);
    }

    @Override
    public String getUsage() {
        return "Usage --exit: --exit";
    }
}

package ru.mail.track.messenger.chat.commands;

import ru.mail.track.messenger.chat.ChatClient;

/**
 * Created by Ivan Shafran on 13.10.2015.
 * Mail: vanobox07@mail.ru
 */
public class CommandHelp implements Command {
    private ChatClient chatClient;

    public CommandHelp(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public String getName() {
        return "--help";
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            return;
        }

        for (Command command : chatClient.getCommands().values()) {
            System.out.println(command.getUsage());
        }
    }

    @Override
    public String getUsage() {
        return "Usage help: --help";
    }

}

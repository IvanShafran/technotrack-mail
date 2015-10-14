package ru.mail.track.messenger.chat;

import java.util.ArrayList;

/**
 * Created by Ivan Shafran on 13.10.2015.
 * Mail: vanobox07@mail.ru
 */
public class CommandHelp implements Command {
    private Chat chat;

    public CommandHelp(Chat chat) {
        this.chat = chat;
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

        for (Command command : chat.getCommands()) {
            System.out.println(command.getUsage());
        }
    }

    @Override
    public String getUsage() {
        return "Usage help: --help";
    }

}

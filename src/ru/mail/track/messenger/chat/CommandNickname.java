package ru.mail.track.messenger.chat;

import java.util.ArrayList;

/**
 * Created by Ivan Shafran on 13.10.2015.
 * Mail: vanobox07@mail.ru
 */
public class CommandNickname implements Command {
    private Chat chat;

    public CommandNickname(Chat chat) {
        this.chat = chat;
    }

    @Override
    public String getName() {
        return "--nickname";
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            return;
        }

        chat.setNickname(args[1]);
    }

    @Override
    public String getUsage() {
        return "Usage user: --nickname your_nickname";
    }

}

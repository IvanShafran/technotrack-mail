package ru.mail.track.messenger.chat;

import java.util.ArrayList;

/**
 * Created by Ivan Shafran on 13.10.2015.
 * Mail: vanobox07@mail.ru
 */
public class CommandFind implements Command {
    private Chat chat;

    @Override
    public String getName() {
        return "--find";
    }

    public CommandFind(Chat chat) {
        this.chat = chat;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            return;
        }

        String regex = args[1];

        if (chat.getMessages() == null || chat.getFriend() == null) {
            return;
        }

        System.out.println("_____Find_____");
        for (String message : chat.getMessages().get(chat.getFriend().getLogin())) {
            if (message.matches(regex)) {
                System.out.println(message);
            }
        }
    }

    @Override
    public String getUsage() {
        return "Usage find: --find regex";
    }
}

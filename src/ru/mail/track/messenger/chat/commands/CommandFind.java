package ru.mail.track.messenger.chat.commands;

import ru.mail.track.messenger.chat.ChatClient;
import ru.mail.track.messenger.chat.Message;

/**
 * Created by Ivan Shafran on 13.10.2015.
 * Mail: vanobox07@mail.ru
 */
public class CommandFind implements Command {
    private ChatClient chatClient;

    @Override
    public String getName() {
        return "--find";
    }

    public CommandFind(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            return;
        }

        String regex = args[1];

        if (chatClient.getMessages() == null || chatClient.getFriendLogin() == null) {
            return;
        }

        System.out.println("_____Find_____");
        for (Message message : chatClient.getMessages()) {
            if (message.getText().matches(regex)) {
                System.out.println(message);
            }
        }
    }

    @Override
    public String getUsage() {
        return "Usage find: --find regex";
    }
}

package ru.mail.track.messenger.chat.commands;

import ru.mail.track.messenger.chat.ChatClient;

/**
 * Created by Ivan Shafran on 13.10.2015.
 * Mail: vanobox07@mail.ru
 */
public class CommandNickname implements Command {
    private ChatClient chatClient;

    public CommandNickname(ChatClient chatClient) {
        this.chatClient = chatClient;
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

        chatClient.setNickname(args[1]);
    }

    @Override
    public String getUsage() {
        return "Usage nickname: --nickname your_nickname";
    }

}

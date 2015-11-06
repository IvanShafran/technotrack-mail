package ru.mail.track.messenger.chat.commands;

import ru.mail.track.messenger.chat.ChatClient;
import ru.mail.track.messenger.chat.Message;

import java.util.List;

/**
 * Created by Ivan Shafran on 13.10.2015.
 * Mail: vanobox07@mail.ru
 */
public class CommandHistory implements Command {
    private ChatClient chatClient;

    public CommandHistory(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public String getName() {
        return "--history";
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 2 || args.length < 1 || chatClient.getUser() == null) {
            return;
        }

        List<Message> messages = chatClient.getMessages();



        int count;
        if (args.length == 2) {
            count = Integer.parseInt(args[1]);
        } else {
            if (messages == null) {
                count = 0;
            } else {
                count = messages.size();
            }
        }

        StringBuilder stringBuilder = new StringBuilder("____History____\n");
        for (int i = 1; i <= count; ++i) {
            stringBuilder.append(messages.get(messages.size() - i)).append("\n");
        }

        System.out.println(stringBuilder.toString());
    }

    @Override
    public String getUsage() {
        return "Usage history: --history [count]";
    }

}

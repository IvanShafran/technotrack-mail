package ru.mail.track.messenger.chat.commands;

import ru.mail.track.messenger.chat.ChatClient;

/**
 * Created by Ivan Shafran on 16.10.2015.
 * Mail: vanobox07@mail.ru
 */
public class CommandFriend implements Command {
    ChatClient chatClient;

    public CommandFriend(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public String getName() {
        return "--friend";
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 2 || args.length < 1) {
            return;
        }

        if (args.length == 1) {
            if (chatClient.getFriendLogin() != null) {
                System.out.println(chatClient.getFriendLogin());
            } else {
                System.out.println("Please, sign in or set your friend login");
            }

            return;
        }

        String friendLogin = args[1];

        if (chatClient.getAuthorizationService().isUserExist(friendLogin)) {
            chatClient.setFriendLogin(friendLogin);
        }
    }

    @Override
    public String getUsage() {
        return "Usage --friend: --friend [friendLogin]";
    }
}

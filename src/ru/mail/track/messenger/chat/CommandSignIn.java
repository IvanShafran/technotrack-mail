package ru.mail.track.messenger.chat;

import ru.mail.track.messenger.authorization.User;

/**
 * Created by Ivan Shafran on 14.10.2015.
 * Mail: vanobox07@mail.ru
 */
public class CommandSignIn implements Command {
    Chat chat;

    public CommandSignIn(Chat chat) {
        this.chat = chat;
    }

    @Override
    public String getName() {
        return "--signin";
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            return;
        }

        User user = chat.getAuthorizationService().startAuthorization();
    }

    @Override
    public String getUsage() {
        return "Usage SignIn: --signin";
    }
}

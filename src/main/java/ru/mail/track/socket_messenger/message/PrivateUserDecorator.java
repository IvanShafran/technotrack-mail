package main.java.ru.mail.track.socket_messenger.message;

import main.java.ru.mail.track.socket_messenger.user.User;
import main.java.ru.mail.track.socket_messenger.user.UserImpl;

/**
 * Created by Ivan Shafran on 08.11.2015.
 * Mail: vanobox07@mail.ru
 */
public class PrivateUserDecorator extends UserImpl {
    public PrivateUserDecorator(User user) {
        super(user.getLogin(), null);
        this.setNickname(user.getNickname());
    }

    @Override
    public String getPass() {
        return null;
    }

    @Override
    public void setPass(String pass) {
        //keep calm and do nothing
    }
}

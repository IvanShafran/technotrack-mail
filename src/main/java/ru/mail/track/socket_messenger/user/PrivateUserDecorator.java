package ru.mail.track.socket_messenger.user;

/**
 * Created by Ivan Shafran on 08.11.2015.
 * Mail: vanobox07@mail.ru
 */
public class PrivateUserDecorator extends UserImpl {
    public PrivateUserDecorator(User user) {
        super(user.getLogin(), null);
        this.setNickname(user.getNickname());
        this.setId(user.getId());
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

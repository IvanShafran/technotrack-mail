package main.java.ru.mail.track.socket_messenger.message.message_impl;

import main.java.ru.mail.track.socket_messenger.commands.CommandType;

/**
 * Created by Ivan Shafran on 08.11.2015.
 * Mail: vanobox07@mail.ru
 */
public class UserPassMessage extends UserMessage {
    private String oldPass;
    private String newPass;

    public UserPassMessage(String oldPass, String newPass) {
        super(CommandType.USER_PASS);
        this.oldPass = oldPass;
        this.newPass = newPass;
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }
}

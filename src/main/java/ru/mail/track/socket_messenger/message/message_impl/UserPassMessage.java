package ru.mail.track.socket_messenger.message.message_impl;

import ru.mail.track.socket_messenger.commands.CommandType;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserPassMessage)) return false;
        if (!super.equals(o)) return false;

        UserPassMessage that = (UserPassMessage) o;

        if (getOldPass() != null ? !getOldPass().equals(that.getOldPass()) : that.getOldPass() != null) return false;
        return !(getNewPass() != null ? !getNewPass().equals(that.getNewPass()) : that.getNewPass() != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getOldPass() != null ? getOldPass().hashCode() : 0);
        result = 31 * result + (getNewPass() != null ? getNewPass().hashCode() : 0);
        return result;
    }
}

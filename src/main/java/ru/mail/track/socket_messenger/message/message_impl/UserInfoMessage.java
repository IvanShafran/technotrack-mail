package ru.mail.track.socket_messenger.message.message_impl;

import ru.mail.track.socket_messenger.commands.CommandType;

/**
 * Created by Ivan Shafran on 08.11.2015.
 * Mail: vanobox07@mail.ru
 */
public class UserInfoMessage extends UserMessage {
    private Long infoId;

    public UserInfoMessage(Long infoId) {
        super(CommandType.USER_INFO);
        this.infoId = infoId;
    }

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInfoMessage)) return false;
        if (!super.equals(o)) return false;

        UserInfoMessage that = (UserInfoMessage) o;

        return !(getInfoId() != null ? !getInfoId().equals(that.getInfoId()) : that.getInfoId() != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getInfoId() != null ? getInfoId().hashCode() : 0);
        return result;
    }
}

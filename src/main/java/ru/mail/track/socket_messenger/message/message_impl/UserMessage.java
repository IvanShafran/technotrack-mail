package ru.mail.track.socket_messenger.message.message_impl;

import ru.mail.track.socket_messenger.commands.CommandType;
import ru.mail.track.socket_messenger.message.Message;

/**
 *
 */
public abstract class UserMessage extends Message {

    private Long senderId;

    public UserMessage(CommandType type) {
        super(type);
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    @Override
    public String toString() {
        return "Message{" +
                ", senderId=" + getSenderId() +
                ", type=" + getType() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserMessage)) return false;
        if (!super.equals(o)) return false;

        UserMessage that = (UserMessage) o;

        return !(getSenderId() != null ? !getSenderId().equals(that.getSenderId()) : that.getSenderId() != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getSenderId() != null ? getSenderId().hashCode() : 0);
        return result;
    }
}

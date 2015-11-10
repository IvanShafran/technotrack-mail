package main.java.socket_messenger.message.message_impl;

import main.java.socket_messenger.commands.CommandType;
import main.java.socket_messenger.message.Message;

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
}

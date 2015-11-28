package ru.mail.track.socket_messenger.message;

import ru.mail.track.socket_messenger.commands.CommandType;

import java.io.Serializable;

/**
 * Created by Ivan Shafran on 01.11.2015.
 * Mail: vanobox07@mail.ru
 */
public abstract class Message implements Serializable {
    private CommandType type;
    private Long messageId;

    public Message(CommandType type) {
        this.type = type;
    }

    public CommandType getType() {
        return type;
    }

    public void setType(CommandType type) {
        this.type = type;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;

        Message message = (Message) o;

        if (getType() != message.getType()) return false;
        return !(getMessageId() != null ? !getMessageId().equals(message.getMessageId()) : message.getMessageId() != null);

    }

    @Override
    public int hashCode() {
        int result = getType() != null ? getType().hashCode() : 0;
        result = 31 * result + (getMessageId() != null ? getMessageId().hashCode() : 0);
        return result;
    }
}

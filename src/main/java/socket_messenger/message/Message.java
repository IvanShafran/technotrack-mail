package main.java.socket_messenger.message;

import main.java.socket_messenger.commands.CommandType;

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
}

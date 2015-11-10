package main.java.ru.mail.track.socket_messenger.message.message_impl;

import main.java.ru.mail.track.socket_messenger.commands.CommandType;

/**
 *
 */
public class SendMessage extends UserMessage {
    private Long chatId;
    private String message;

    public SendMessage(Long chatId, String message) {
        super(CommandType.CHAT_SEND);
        this.chatId = chatId;
        this.message = message;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

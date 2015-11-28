package ru.mail.track.socket_messenger.message.message_impl;

import ru.mail.track.socket_messenger.commands.CommandType;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SendMessage)) return false;
        if (!super.equals(o)) return false;

        SendMessage that = (SendMessage) o;

        if (getChatId() != null ? !getChatId().equals(that.getChatId()) : that.getChatId() != null) return false;
        return !(getMessage() != null ? !getMessage().equals(that.getMessage()) : that.getMessage() != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getChatId() != null ? getChatId().hashCode() : 0);
        result = 31 * result + (getMessage() != null ? getMessage().hashCode() : 0);
        return result;
    }
}

package ru.mail.track.socket_messenger.message.message_impl;


import ru.mail.track.socket_messenger.commands.CommandType;

/**
 * Created by Ivan Shafran on 08.11.2015.
 * Mail: vanobox07@mail.ru
 */
public class ChatHistoryMessage extends UserMessage {
    private Long chatId;
    private Integer number;

    public ChatHistoryMessage(Long chatId, Integer number) {
        super(CommandType.CHAT_HISTORY);
        this.chatId = chatId;
        this.number = number;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatHistoryMessage)) return false;
        if (!super.equals(o)) return false;

        ChatHistoryMessage that = (ChatHistoryMessage) o;

        if (getChatId() != null ? !getChatId().equals(that.getChatId()) : that.getChatId() != null) return false;
        return !(getNumber() != null ? !getNumber().equals(that.getNumber()) : that.getNumber() != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getChatId() != null ? getChatId().hashCode() : 0);
        result = 31 * result + (getNumber() != null ? getNumber().hashCode() : 0);
        return result;
    }
}

package ru.mail.track.socket_messenger.message.message_impl;

import ru.mail.track.socket_messenger.commands.CommandType;

/**
 * Created by Ivan Shafran on 08.11.2015.
 * Mail: vanobox07@mail.ru
 */
public class ChatFindMessage extends UserMessage {
    private Long chatId;
    private String regex;

    public ChatFindMessage(Long chatId, String regex) {
        super(CommandType.CHAT_FIND);
        this.chatId = chatId;
        this.regex = regex;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatFindMessage)) return false;
        if (!super.equals(o)) return false;

        ChatFindMessage that = (ChatFindMessage) o;

        if (getChatId() != null ? !getChatId().equals(that.getChatId()) : that.getChatId() != null) return false;
        return !(getRegex() != null ? !getRegex().equals(that.getRegex()) : that.getRegex() != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getChatId() != null ? getChatId().hashCode() : 0);
        result = 31 * result + (getRegex() != null ? getRegex().hashCode() : 0);
        return result;
    }
}

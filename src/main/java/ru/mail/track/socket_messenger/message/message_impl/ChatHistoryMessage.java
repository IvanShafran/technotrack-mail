package main.java.ru.mail.track.socket_messenger.message.message_impl;


import main.java.ru.mail.track.socket_messenger.commands.CommandType;

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
}

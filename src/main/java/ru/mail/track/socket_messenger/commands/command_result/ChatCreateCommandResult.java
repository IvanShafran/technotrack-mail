package ru.mail.track.socket_messenger.commands.command_result;

import ru.mail.track.socket_messenger.commands.CommandType;

/**
 * Created by Ivan Shafran on 09.11.2015.
 * Mail: vanobox07@mail.ru
 */
public class ChatCreateCommandResult extends CommandResult {
    private Long chatId;

    public ChatCreateCommandResult(Status status, String report, Long chatId) {
        super(CommandType.CHAT_CREATE, status, report);
        this.chatId = chatId;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatCreateCommandResult)) return false;
        if (!super.equals(o)) return false;

        ChatCreateCommandResult that = (ChatCreateCommandResult) o;

        return !(getChatId() != null ? !getChatId().equals(that.getChatId()) : that.getChatId() != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getChatId() != null ? getChatId().hashCode() : 0);
        return result;
    }
}

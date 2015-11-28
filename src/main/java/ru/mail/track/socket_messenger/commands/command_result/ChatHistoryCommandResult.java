package ru.mail.track.socket_messenger.commands.command_result;

import ru.mail.track.socket_messenger.commands.CommandType;
import ru.mail.track.socket_messenger.message.Message;

import java.util.List;

/**
 * Created by Ivan Shafran on 09.11.2015.
 * Mail: vanobox07@mail.ru
 */
public class ChatHistoryCommandResult extends CommandResult {
    private List<Message> messages;

    public ChatHistoryCommandResult(Status status, String report, List<Message> messages) {
        super(CommandType.CHAT_HISTORY, status, report);
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatHistoryCommandResult)) return false;
        if (!super.equals(o)) return false;

        ChatHistoryCommandResult that = (ChatHistoryCommandResult) o;

        return !(getMessages() != null ? !getMessages().equals(that.getMessages()) : that.getMessages() != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getMessages() != null ? getMessages().hashCode() : 0);
        return result;
    }
}

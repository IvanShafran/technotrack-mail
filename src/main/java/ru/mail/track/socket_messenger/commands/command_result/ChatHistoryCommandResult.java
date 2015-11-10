package main.java.ru.mail.track.socket_messenger.commands.command_result;

import main.java.ru.mail.track.socket_messenger.commands.CommandType;
import main.java.ru.mail.track.socket_messenger.message.Message;

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
}

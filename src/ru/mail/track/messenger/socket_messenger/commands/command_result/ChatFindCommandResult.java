package ru.mail.track.messenger.socket_messenger.commands.command_result;

import ru.mail.track.messenger.socket_messenger.commands.CommandType;
import ru.mail.track.messenger.socket_messenger.message.Message;

import java.util.List;

/**
 * Created by Ivan Shafran on 09.11.2015.
 * Mail: vanobox07@mail.ru
 */
public class ChatFindCommandResult extends CommandResult {
    private List<Message> messages;

    public ChatFindCommandResult(Status status, String report, List<Message> messages) {
        super(CommandType.CHAT_FIND, status, report);
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}

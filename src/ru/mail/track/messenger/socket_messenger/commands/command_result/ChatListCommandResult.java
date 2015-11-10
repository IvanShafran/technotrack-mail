package ru.mail.track.messenger.socket_messenger.commands.command_result;

import ru.mail.track.messenger.socket_messenger.commands.CommandType;

import java.util.List;

/**
 * Created by Ivan Shafran on 09.11.2015.
 * Mail: vanobox07@mail.ru
 */
public class ChatListCommandResult extends CommandResult {
    private List<Long> participants;

    public ChatListCommandResult(Status status, String report, List<Long> participants) {
        super(CommandType.CHAT_LIST, status, report);
        this.participants = participants;
    }

    public List<Long> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Long> participants) {
        this.participants = participants;
    }
}

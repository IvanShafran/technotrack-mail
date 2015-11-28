package ru.mail.track.socket_messenger.commands.command_result;

import ru.mail.track.socket_messenger.commands.CommandType;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatListCommandResult)) return false;
        if (!super.equals(o)) return false;

        ChatListCommandResult that = (ChatListCommandResult) o;

        return !(getParticipants() != null ? !getParticipants().equals(that.getParticipants()) : that.getParticipants() != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getParticipants() != null ? getParticipants().hashCode() : 0);
        return result;
    }
}

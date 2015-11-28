package ru.mail.track.socket_messenger.message.message_impl;

import ru.mail.track.socket_messenger.commands.command_result.CommandResult;
import ru.mail.track.socket_messenger.commands.CommandType;
import ru.mail.track.socket_messenger.message.Message;

/**
 * Created by Ivan Shafran on 01.11.2015.
 * Mail: vanobox07@mail.ru
 */
public class CommandResultMessage extends Message {
    CommandResult commandResult;

    public CommandResultMessage(CommandResult commandResult) {
        super(CommandType.COMMAND_RESULT);
        this.commandResult = commandResult;
    }

    public CommandResult getCommandResult() {
        return commandResult;
    }

    public void setCommandResult(CommandResult commandResult) {
        this.commandResult = commandResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommandResultMessage)) return false;
        if (!super.equals(o)) return false;

        CommandResultMessage that = (CommandResultMessage) o;

        return !(getCommandResult() != null ? !getCommandResult().equals(that.getCommandResult()) : that.getCommandResult() != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getCommandResult() != null ? getCommandResult().hashCode() : 0);
        return result;
    }
}

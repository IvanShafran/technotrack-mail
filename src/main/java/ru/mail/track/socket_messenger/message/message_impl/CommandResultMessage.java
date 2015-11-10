package main.java.ru.mail.track.socket_messenger.message.message_impl;

import main.java.ru.mail.track.socket_messenger.commands.command_result.CommandResult;
import main.java.ru.mail.track.socket_messenger.commands.CommandType;
import main.java.ru.mail.track.socket_messenger.message.Message;

/**
 * Created by Ivan Shafran on 01.11.2015.
 * Mail: vanobox07@mail.ru
 */
public class CommandResultMessage extends Message {
    CommandResult commandResult;

    public CommandResultMessage(CommandType type, CommandResult commandResult) {
        super(type);
        this.commandResult = commandResult;
    }

    public CommandResult getCommandResult() {
        return commandResult;
    }

    public void setCommandResult(CommandResult commandResult) {
        this.commandResult = commandResult;
    }
}

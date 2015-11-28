package ru.mail.track.socket_messenger.commands.command_impl;

import ru.mail.track.socket_messenger.commands.Command;
import ru.mail.track.socket_messenger.commands.CommandType;
import ru.mail.track.socket_messenger.commands.command_result.CommandResult;
import ru.mail.track.socket_messenger.commands.command_result.HelpCommandResult;
import ru.mail.track.socket_messenger.message.Message;
import ru.mail.track.socket_messenger.session.Session;

import java.util.Map;

/**
 * Вывести помощь
 */
public class HelpCommand implements Command {

    private Map<CommandType, Command> commands;

    public HelpCommand(Map<CommandType, Command> commands) {
        this.commands = commands;
    }

    @Override
    public CommandResult execute(Session session, Message msg) {
        StringBuilder stringBuilder = new StringBuilder("Usage:\n");
        for (Command command : commands.values()) {
            stringBuilder.append(command.getUsage());
            stringBuilder.append("\n");
        }

        return new HelpCommandResult(CommandResult.Status.OK, null,
                stringBuilder.toString());
    }

    @Override
    public String getUsage() {
        return "/help";
    }
}

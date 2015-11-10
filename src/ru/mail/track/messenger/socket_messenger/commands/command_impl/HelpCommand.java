package ru.mail.track.messenger.socket_messenger.commands.command_impl;

import java.util.Map;

import ru.mail.track.messenger.socket_messenger.commands.Command;
import ru.mail.track.messenger.socket_messenger.commands.CommandType;
import ru.mail.track.messenger.socket_messenger.commands.command_result.CommandResult;
import ru.mail.track.messenger.socket_messenger.message.Message;
import ru.mail.track.messenger.socket_messenger.session.Session;

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

        return new CommandResult(CommandType.USER_HELP, CommandResult.Status.OK, stringBuilder.toString());
    }

    @Override
    public String getUsage() {
        return "/help";
    }
}

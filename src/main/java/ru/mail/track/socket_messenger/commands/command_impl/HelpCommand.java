package main.java.ru.mail.track.socket_messenger.commands.command_impl;

import java.util.Map;

import main.java.ru.mail.track.socket_messenger.commands.Command;
import main.java.ru.mail.track.socket_messenger.commands.CommandType;
import main.java.ru.mail.track.socket_messenger.commands.command_result.CommandResult;
import main.java.ru.mail.track.socket_messenger.message.Message;
import main.java.ru.mail.track.socket_messenger.session.Session;

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

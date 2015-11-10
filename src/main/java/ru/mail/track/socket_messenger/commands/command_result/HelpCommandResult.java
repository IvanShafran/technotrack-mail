package main.java.ru.mail.track.socket_messenger.commands.command_result;

import main.java.ru.mail.track.socket_messenger.commands.CommandType;

/**
 * Created by Ivan Shafran on 09.11.2015.
 * Mail: vanobox07@mail.ru
 */
public class HelpCommandResult extends CommandResult {
    private String usage;

    public HelpCommandResult(Status status, String report, String usage) {
        super(CommandType.USER_HELP, status, report);
        this.usage = usage;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }
}

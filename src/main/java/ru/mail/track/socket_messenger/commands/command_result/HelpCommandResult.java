package ru.mail.track.socket_messenger.commands.command_result;

import ru.mail.track.socket_messenger.commands.CommandType;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HelpCommandResult)) return false;
        if (!super.equals(o)) return false;

        HelpCommandResult that = (HelpCommandResult) o;

        return !(getUsage() != null ? !getUsage().equals(that.getUsage()) : that.getUsage() != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getUsage() != null ? getUsage().hashCode() : 0);
        return result;
    }
}

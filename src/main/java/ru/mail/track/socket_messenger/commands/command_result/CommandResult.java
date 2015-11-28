package ru.mail.track.socket_messenger.commands.command_result;

import ru.mail.track.socket_messenger.commands.CommandType;

import java.io.Serializable;

public class CommandResult implements Serializable {
    public enum Status {
        OK,
        FAILED,
        NOT_LOGGED,
        WARNING,
        PERMISSION_DENIED
    }

    private CommandType commandType;
    private Status status;
    private String report;

    public CommandResult(CommandType commandType, Status status, String report) {
        this.commandType = commandType;
        this.status = status;
        this.report = report;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommandResult)) return false;

        CommandResult that = (CommandResult) o;

        if (getCommandType() != that.getCommandType()) return false;
        if (getStatus() != that.getStatus()) return false;
        return !(getReport() != null ? !getReport().equals(that.getReport()) : that.getReport() != null);

    }

    @Override
    public int hashCode() {
        int result = getCommandType() != null ? getCommandType().hashCode() : 0;
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getReport() != null ? getReport().hashCode() : 0);
        return result;
    }
}

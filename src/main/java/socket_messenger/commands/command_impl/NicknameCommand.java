package main.java.socket_messenger.commands.command_impl;

import main.java.socket_messenger.commands.Command;
import main.java.socket_messenger.commands.CommandType;
import main.java.socket_messenger.commands.command_result.CommandResult;
import main.java.socket_messenger.message.Message;
import main.java.socket_messenger.message.message_impl.NicknameMessage;
import main.java.socket_messenger.session.Session;

/**
 * Created by Ivan Shafran on 08.11.2015.
 * Mail: vanobox07@mail.ru
 */
public class NicknameCommand implements Command {
    @Override
    public CommandResult execute(Session session, Message message) {
        if (session.getSessionUser() == null) {
            return new CommandResult(CommandType.NICKNAME_COMMAND, CommandResult.Status.NOT_LOGGED, null);
        } else {
            NicknameMessage nicknameMessage = (NicknameMessage)message;

            if (nicknameMessage.getNickname() == null) {
                return new CommandResult(CommandType.NICKNAME_COMMAND, CommandResult.Status.FAILED,
                        "Please, check usage by /help");
            }

            session.getSessionUser().setNickname(nicknameMessage.getNickname());

            return new CommandResult(CommandType.NICKNAME_COMMAND, CommandResult.Status.OK, null);
        }
    }

    @Override
    public String getUsage() {
        return "/user <nickname>";
    }
}

package ru.mail.track.socket_messenger.commands.command_impl;

import ru.mail.track.socket_messenger.commands.Command;
import ru.mail.track.socket_messenger.commands.CommandType;
import ru.mail.track.socket_messenger.commands.command_result.CommandResult;
import ru.mail.track.socket_messenger.message.Message;
import ru.mail.track.socket_messenger.message.message_impl.NicknameMessage;
import ru.mail.track.socket_messenger.session.Session;

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

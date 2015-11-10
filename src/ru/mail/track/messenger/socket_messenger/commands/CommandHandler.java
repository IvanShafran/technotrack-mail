package ru.mail.track.messenger.socket_messenger.commands;

import java.io.IOException;
import java.util.Map;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import ru.mail.track.messenger.socket_messenger.commands.command_result.CommandResult;
import ru.mail.track.messenger.socket_messenger.message.message_impl.CommandResultMessage;
import ru.mail.track.messenger.socket_messenger.message.Message;
import ru.mail.track.messenger.socket_messenger.net.MessageListener;
import ru.mail.track.messenger.socket_messenger.session.Session;

/**
 *
 */
public class CommandHandler implements MessageListener {

    //static Logger log = LoggerFactory.getLogger(CommandHandler.class);

    Map<CommandType, Command> commands;

    public CommandHandler(Map<CommandType, Command> commands) {
        this.commands = commands;
    }

    @Override
    public void onMessage(Session session, Message message) {
        Command cmd = commands.get(message.getType());
        //log.info("onMessage: {} type {}", message, message.getType());
        CommandResult commandResult = cmd.execute(session, message);

        try {
            session.getConnectionHandler().send(new CommandResultMessage(CommandType.COMMAND_RESULT, commandResult));
        } catch (IOException e) {
            //nice try
        }
    }
}

package main.java.socket_messenger.message.message_impl;

import main.java.socket_messenger.commands.CommandType;
import main.java.socket_messenger.message.Message;

/**
 * Created by Ivan Shafran on 02.11.2015.
 * Mail: vanobox07@mail.ru
 */
public class HelpMessage extends Message {
    public HelpMessage() {
        super(CommandType.USER_HELP);
    }
}

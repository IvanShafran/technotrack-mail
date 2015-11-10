package main.java.socket_messenger.net;

import main.java.socket_messenger.message.Message;

/**
 *
 */
public interface Protocol {

    Message decode(byte[] bytes);

    byte[] encode(Message msg);

}

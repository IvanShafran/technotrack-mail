package main.java.ru.mail.track.socket_messenger.net;

import main.java.ru.mail.track.socket_messenger.message.Message;

/**
 *
 */
public interface Protocol {

    Message decode(byte[] bytes);

    byte[] encode(Message msg);

}

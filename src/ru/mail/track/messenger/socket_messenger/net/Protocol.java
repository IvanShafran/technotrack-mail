package ru.mail.track.messenger.socket_messenger.net;

import ru.mail.track.messenger.socket_messenger.message.Message;

/**
 *
 */
public interface Protocol {

    Message decode(byte[] bytes);

    byte[] encode(Message msg);

}

package ru.mail.track.socket_messenger.serialization;

import ru.mail.track.socket_messenger.message.Message;

/**
 *
 */
public interface Protocol {

    Message decode(byte[] bytes);

    byte[] encode(Message msg);

}

package ru.mail.track.messenger.socket_messenger.net;

import ru.mail.track.messenger.socket_messenger.message.Message;
import ru.mail.track.messenger.socket_messenger.session.Session;

/**
 * Слушает сообщения
 */
public interface MessageListener {

    void onMessage(Session session, Message message);
}

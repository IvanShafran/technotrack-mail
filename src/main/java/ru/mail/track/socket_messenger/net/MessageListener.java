package main.java.ru.mail.track.socket_messenger.net;

import main.java.ru.mail.track.socket_messenger.message.Message;
import main.java.ru.mail.track.socket_messenger.session.Session;

/**
 * Слушает сообщения
 */
public interface MessageListener {

    void onMessage(Session session, Message message);
}

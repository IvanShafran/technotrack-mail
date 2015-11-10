package main.java.socket_messenger.net;

import main.java.socket_messenger.message.Message;
import main.java.socket_messenger.session.Session;

/**
 * Слушает сообщения
 */
public interface MessageListener {

    void onMessage(Session session, Message message);
}

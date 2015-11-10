package main.java.ru.mail.track.socket_messenger.net;

import java.io.IOException;

import main.java.ru.mail.track.socket_messenger.message.Message;

/**
 * Обработчик сокета
 */
public interface ConnectionHandler extends Runnable {

    void send(Message msg) throws IOException;

    void addListener(MessageListener listener);

    void stop();
}

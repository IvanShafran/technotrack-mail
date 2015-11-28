package ru.mail.track.socket_messenger.net;

import ru.mail.track.socket_messenger.message.Message;

import java.io.IOException;

/**
 * Обработчик сокета
 */
public interface ConnectionHandler extends Runnable {

    void send(Message msg) throws IOException;

    void addListener(MessageListener listener);

    void stop();
}

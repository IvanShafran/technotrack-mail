package main.java.socket_messenger.net;

import java.io.IOException;

import main.java.socket_messenger.message.Message;

/**
 * Обработчик сокета
 */
public interface ConnectionHandler extends Runnable {

    void send(Message msg) throws IOException;

    void addListener(MessageListener listener);

    void stop();
}

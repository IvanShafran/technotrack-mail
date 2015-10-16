package ru.mail.track.messenger.chat;

import java.util.List;

/**
 * Created by Ivan Shafran on 16.10.2015.
 * Mail: vanobox07@mail.ru
 */
public interface MessageManager {
    void addMessage(String from, String to, Message message) throws AddMessageException;
    List<Message> getMessages(String from, String to);
    void close();
}

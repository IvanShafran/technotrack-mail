package ru.mail.track.messenger.chat;

import ru.mail.track.messenger.authorization.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Ivan Shafran on 15.10.2015.
 * Mail: vanobox07@mail.ru
 */
public interface MessageStorage {
    Map<String, List<String>> getMessages();
    void addMessage(String message);
    void setUser(User user);
    void finishWorking();
}

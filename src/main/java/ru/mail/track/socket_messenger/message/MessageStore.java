package main.java.ru.mail.track.socket_messenger.message;

import main.java.ru.mail.track.socket_messenger.message.message_impl.SendMessage;
import main.java.ru.mail.track.socket_messenger.message.message_impl.UserMessage;

import java.util.List;

/**
 * Хранилище информации о сообщениях
 */
public interface MessageStore {
    /**
     * Список сообщений из чата
     *
     */
    List<Long> getMessagesFromChat(Long chatId);

    /**
     * Получить информацию о сообщении
     */
    UserMessage getMessageById(Long messageId);

    /**
     * Добавить сообщение в чат
     */
    void addMessage(SendMessage message);
}

package main.java.ru.mail.track.socket_messenger.chat;

import java.util.List;

public interface ChatStore {
    /**
     * создать чат
     */
    Long createChat(List<Long> participants);

    /**
     получаем список чатов указанного пользователя
     */
    List<Long> getChatsByUserId(Long userId);

    /**
     получить информацию о чате
     */
    Chat getChatById(Long chatId);

    /**
     * получить чат между двумя пользователями, если его нет, то создать
     */
    Long getTwoChatId(Long first_id, Long second_id);

    /**
     * Добавить пользователя к чату
     */
    void addUserToChat(Long userId, Long chatId);
}

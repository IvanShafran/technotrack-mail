package ru.mail.track.socket_messenger.chat;

import java.util.List;

public interface ChatStore {

    Long createChat(List<Long> participants);

    List<Long> getChatsByUserId(Long userId);

    Chat getChatById(Long chatId);

    Long getTwoChatId(Long first_id, Long second_id);

    void addUserToChat(Long userId, Long chatId);
}

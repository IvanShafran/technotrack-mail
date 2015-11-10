package main.java.ru.mail.track.socket_messenger.chat;

import java.util.HashMap;
import java.util.List;

public class ChatStoreStub implements ChatStore {
    static HashMap<Long, Chat> chats = new HashMap<>();

    static {
        Chat chat1 = new Chat();
        chat1.addParticipant(0L);
        chat1.addParticipant(2L);

        Chat chat2 = new Chat();
        chat2.addParticipant(1L);
        chat2.addParticipant(2L);
        chat2.addParticipant(3L);

        chats.put(1L, chat1);
        chats.put(2L, chat2);
    }

    @Override
    public Long createChat(List<Long> participants) {
        return null;
    }

    @Override
    public List<Long> getChatsByUserId(Long userId) {
        return null;
    }

    @Override
    public Chat getChatById(Long chatId) {
        return chats.get(chatId);
    }

    @Override
    public Long getTwoChatId(Long first_id, Long second_id) {
        return null;
    }

    @Override
    public void addUserToChat(Long userId, Long chatId) {

    }
}

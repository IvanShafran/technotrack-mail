package main.java.ru.mail.track.socket_messenger.message;

import main.java.ru.mail.track.socket_messenger.chat.ChatStore;
import main.java.ru.mail.track.socket_messenger.message.message_impl.SendMessage;
import main.java.ru.mail.track.socket_messenger.message.message_impl.UserMessage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 */
public class MessageStoreStub implements MessageStore {

    public static final AtomicLong counter = new AtomicLong(0);

    List<SendMessage> messages1 = Arrays.asList(
            new SendMessage(1L, "msg1_1"),
            new SendMessage(1L, "msg1_2"),
            new SendMessage(1L, "msg1_3"),
            new SendMessage(1L, "msg1_4"),
            new SendMessage(1L, "msg1_5")
    );
    List<SendMessage> messages2 = Arrays.asList(
            new SendMessage(2L, "msg2_1"),
            new SendMessage(2L, "msg2_2"),
            new SendMessage(2L, "msg2_3"),
            new SendMessage(2L, "msg2_4"),
            new SendMessage(2L, "msg2_5")
    );

    Map<Long, UserMessage> messages = new HashMap<>();
    ChatStore chatStore;

    public MessageStoreStub(ChatStore chatStore) {
        this.chatStore = chatStore;
    }

    @Override
    public List<Long> getMessagesFromChat(Long chatId) {
        return chatStore.getChatById(chatId).getMessageIds();
    }

    @Override
    public UserMessage getMessageById(Long messageId) {
        return messages.get(messageId);
    }

    @Override
    public void addMessage(SendMessage message) {
        message.setMessageId(counter.getAndIncrement());
        chatStore.getChatById(message.getChatId()).addMessage(message.getMessageId());
        messages.put(message.getMessageId(), message);
    }
}

package ru.mail.track.messenger.socket_messenger.message.message_impl;

import ru.mail.track.messenger.socket_messenger.commands.CommandType;

/**
 * Created by Ivan Shafran on 08.11.2015.
 * Mail: vanobox07@mail.ru
 */
public class ChatListMessage extends UserMessage {
    public ChatListMessage() {
        super(CommandType.CHAT_LIST);
    }
}

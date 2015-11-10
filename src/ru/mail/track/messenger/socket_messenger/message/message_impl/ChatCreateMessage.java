package ru.mail.track.messenger.socket_messenger.message.message_impl;


import ru.mail.track.messenger.socket_messenger.commands.CommandType;

import java.util.List;

/**
 * Created by Ivan Shafran on 08.11.2015.
 * Mail: vanobox07@mail.ru
 */
public class ChatCreateMessage extends UserMessage {
    private List<Long> users;

    public ChatCreateMessage(List<Long> users) {
        super(CommandType.CHAT_CREATE);
        this.users = users;
    }

    public List<Long> getUsers() {
        return users;
    }

    public void setUsers(List<Long> users) {
        this.users = users;
    }
}

package ru.mail.track.socket_messenger.message.message_impl;


import ru.mail.track.socket_messenger.commands.CommandType;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatCreateMessage)) return false;
        if (!super.equals(o)) return false;

        ChatCreateMessage that = (ChatCreateMessage) o;

        return !(getUsers() != null ? !getUsers().equals(that.getUsers()) : that.getUsers() != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getUsers() != null ? getUsers().hashCode() : 0);
        return result;
    }
}

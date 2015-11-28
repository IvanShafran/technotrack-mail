package ru.mail.track.socket_messenger.message.message_impl;

import ru.mail.track.socket_messenger.commands.CommandType;

/**
 * Created by Ivan Shafran on 08.11.2015.
 * Mail: vanobox07@mail.ru
 */
public class NicknameMessage extends UserMessage {
    private String nickname;

    public NicknameMessage(String nickname) {
        super(CommandType.NICKNAME_COMMAND);
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NicknameMessage)) return false;
        if (!super.equals(o)) return false;

        NicknameMessage that = (NicknameMessage) o;

        return !(getNickname() != null ? !getNickname().equals(that.getNickname()) : that.getNickname() != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getNickname() != null ? getNickname().hashCode() : 0);
        return result;
    }
}

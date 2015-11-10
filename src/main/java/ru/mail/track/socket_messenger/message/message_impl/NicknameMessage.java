package main.java.ru.mail.track.socket_messenger.message.message_impl;

import main.java.ru.mail.track.socket_messenger.commands.CommandType;

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
}

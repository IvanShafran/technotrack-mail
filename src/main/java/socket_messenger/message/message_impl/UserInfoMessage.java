package main.java.socket_messenger.message.message_impl;

import main.java.socket_messenger.commands.CommandType;

/**
 * Created by Ivan Shafran on 08.11.2015.
 * Mail: vanobox07@mail.ru
 */
public class UserInfoMessage extends UserMessage {
    private Long infoId;

    public UserInfoMessage(Long infoId) {
        super(CommandType.USER_INFO);
        this.infoId = infoId;
    }

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }
}

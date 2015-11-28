package ru.mail.track.socket_messenger.user;


import ru.mail.track.socket_messenger.jdbc.Dao.Identified;

import java.io.Serializable;

public interface User extends Serializable, Identified<Long> {
    @Override
    Long getId();
    void setId(Long id);
    String getPass();
    void setPass(String pass);
    String getNickname();
    void setNickname(String nickname);
    String getLogin();
    void setLogin(String login);
}

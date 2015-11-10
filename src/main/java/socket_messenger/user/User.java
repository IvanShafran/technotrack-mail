package main.java.socket_messenger.user;


import java.io.Serializable;

public interface User extends Serializable {
    Long getId();
    void setId(Long id);
    String getPass();
    void setPass(String pass);
    String getNickname();
    void setNickname(String nickname);
    String getLogin();
    void setLogin(String login);
}

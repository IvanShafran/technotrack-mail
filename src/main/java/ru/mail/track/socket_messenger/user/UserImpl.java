package main.java.ru.mail.track.socket_messenger.user;

/**
 * Created by Ivan Shafran on 04.11.2015.
 * Mail: vanobox07@mail.ru
 */
public class UserImpl implements User {
    private Long id;
    private String login;
    private String nickname;
    private String pass;

    public UserImpl(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getPass() {
        return pass;
    }

    @Override
    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    @Override
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}

package ru.mail.track.socket_messenger.jdbc.SqlDao;

import ru.mail.track.socket_messenger.jdbc.Dao.AbstractJDBCDao;
import ru.mail.track.socket_messenger.jdbc.Dao.PersistException;
import ru.mail.track.socket_messenger.user.User;
import ru.mail.track.socket_messenger.user.UserImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class MySqlGroupDao extends AbstractJDBCDao<User, Long> {

    @Override
    public String getIdName() {
        return "userId";
    }

    @Override
    public String getSelectQuery() {
        return "SELECT userId, login, pass, nickname  FROM users";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO users (login, pass, nickname) \n" +
                "VALUES (?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE users SET login=?, pass=?, nickname=?  WHERE userId= ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM users WHERE userId= ?;";
    }

    @Override
    public User create() throws PersistException {
        throw new PersistException("Not implemented");
    }

    public MySqlGroupDao(Connection connection) {
        super(connection);
    }

    @Override
    protected List<User> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<User> result = new LinkedList<User>();
        try {
            while (rs.next()) {
                User user = new UserImpl();
                user.setId(rs.getLong("userId"));
                user.setLogin(rs.getString("login"));
                user.setPass(rs.getString("pass"));
                user.setNickname(rs.getString("nickname"));
                result.add(user);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User user) throws PersistException {
        try {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPass());
            statement.setString(3, user.getNickname());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User user) throws PersistException {
        try {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPass());
            statement.setString(3, user.getNickname());
            statement.setLong(4, user.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}

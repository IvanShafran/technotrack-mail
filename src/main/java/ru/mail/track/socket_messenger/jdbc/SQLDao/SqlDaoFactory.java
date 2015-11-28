package ru.mail.track.socket_messenger.jdbc.SqlDao;

import ru.mail.track.socket_messenger.jdbc.Dao.DaoFactory;
import ru.mail.track.socket_messenger.jdbc.Dao.GenericDao;
import ru.mail.track.socket_messenger.jdbc.Dao.PersistException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SqlDaoFactory implements DaoFactory<Connection> {

    private String user = "senthil";//Логин пользователя
    private String password = "ubuntu";//Пароль пользователя
    private String url = "jdbc:postgresql://178.62.140.149:5432/mydb";//URL адрес
    private String driver = "org.postgresql.Driver";//Имя драйвера
    private Map<Class, DaoCreator> creators;

    public Connection getContext() throws PersistException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return  connection;
    }

    @Override
    public GenericDao getDao(Connection connection, Class dtoClass) throws PersistException {
        DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new PersistException("Dao object for " + dtoClass + " not found.");
        }
        return creator.create(connection);
    }

    public SqlDaoFactory() {
        try {
            Class.forName(driver);//Регистрируем драйвер
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        creators = new HashMap<Class, DaoCreator>();
//        creators.put(Group.class, new DaoCreator<Connection>() {
//            @Override
//            public GenericDao create(Connection connection) {
//                return new MySqlGroupDao(connection);
//            }
//        });
//        creators.put(Student.class, new DaoCreator<Connection>() {
//            @Override
//            public GenericDao create(Connection connection) {
//                return new MySqlStudentDao(connection);
//            }
//        });
    }
}

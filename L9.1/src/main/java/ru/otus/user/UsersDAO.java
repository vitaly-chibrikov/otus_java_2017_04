package ru.otus.user;

import java.sql.Connection;
import java.sql.SQLException;

import ru.otus.executor.TExecutor;

public class UsersDAO {

    private Connection connection;

    public UsersDAO(Connection connection) {
        this.connection = connection;
    }

    public UsersDataSet get(long id) throws SQLException {
        TExecutor exec = new TExecutor(connection);
        return exec.execQuery("select * from users where id=" + id, result -> {
            result.next();
            return new UsersDataSet(result.getLong(1), result.getString(2));
        });
    }
}

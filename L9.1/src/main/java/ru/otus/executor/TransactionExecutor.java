package ru.otus.executor;

import ru.otus.handlers.ResultHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionExecutor {
    private final Connection connection;

    public TransactionExecutor(Connection connection) {
        this.connection = connection;
    }

    public void execQuery(String query, ResultHandler handler) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute(query);
        ResultSet result = stmt.getResultSet();
        handler.handle(result);
        result.close();
        stmt.close();
    }

    public void execUpdate(String[] updates) {
        try {
            connection.setAutoCommit(false);
            for (String update : updates) {
                Statement stmt = connection.createStatement();
                stmt.executeUpdate(update);
                stmt.close();
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
        }
    }

}

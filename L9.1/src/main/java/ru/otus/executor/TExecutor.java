package ru.otus.executor;

import ru.otus.handlers.TResultHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TExecutor {
    private final Connection connection;

    public TExecutor(Connection connection) {
        this.connection = connection;
    }

    public <T> T execQuery(String query, TResultHandler<T> handler) throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.execute(query);
		ResultSet result = stmt.getResultSet();
		T value = handler.handle(result);
		result.close();
		stmt.close();

		return value;
	}

}

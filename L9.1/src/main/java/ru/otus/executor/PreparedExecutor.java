package ru.otus.executor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class PreparedExecutor extends SimpleExecutor {

    public PreparedExecutor(Connection connection) {
        super(connection);
    }

    public void execUpdate(Map<Integer, String> idToName) {
        try {
            getConnection().setAutoCommit(false);
            String update = "insert into users(id, user_name) values(?,?)";
            PreparedStatement stmt = getConnection().prepareStatement(update);

            for (Integer id : idToName.keySet()) {
                stmt.setInt(1, id);
                stmt.setString(2, idToName.get(id));
                stmt.executeUpdate();
            }
            getConnection().commit();
            stmt.close();
        } catch (SQLException e) {
            try {
                getConnection().rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}

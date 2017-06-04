package ru.otus.examples;

import ru.otus.handlers.ResultHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import ru.otus.executor.PreparedExecutor;

public class PreparedExample {
    public static void example() {
        try {
            Connection connection = ConnectionHelper.getConnection();

            Map<Integer, String> idToName = new HashMap<Integer, String>();
            idToName.put(1, "Test1");
            idToName.put(2, "Test2");
            idToName.put(3, "Test3");
            idToName.put(4, "Test4");

            PreparedExecutor exec = new PreparedExecutor(connection);
            exec.execUpdate("create table users (id bigint, user_name varchar(256), primary key (id))");
            exec.execUpdate(idToName);
            System.out.println("All users added\n");

            exec.execQuery("select * from users", new ResultHandler() {

                public void handle(ResultSet result) throws SQLException {
                    while (true) {
                        result.next();
                        System.out.println("User: " + result.getInt(1) + " name: " + result.getString("user_name"));
                        if (result.isLast())
                            break;
                    }
                }
            });

            exec.execUpdate("drop table users");
            System.out.println("Done!\n");

            connection.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}

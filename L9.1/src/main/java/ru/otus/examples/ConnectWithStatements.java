package ru.otus.examples;

import ru.otus.handlers.ResultHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import ru.otus.executor.SimpleExecutor;

public class ConnectWithStatements {

    static class ResultHandlerGetName implements ResultHandler {
        public void handle(ResultSet result) throws SQLException {
            result.next();
            System.out.println("Read user: " + result.getString("user_name"));
        }
    }

    public static void example() {
        Connection connection = ConnectionHelper.getConnection();
        SimpleExecutor exec = new SimpleExecutor(connection);
        try {
            exec.execUpdate("create table users (id bigint auto_increment, user_name varchar(256), primary key (id))");
            System.out.println("Table created");
            exec.execUpdate("insert into users (user_name) values ('tully25')");
            System.out.println("User added");

            //with inner class
            exec.execQuery("select user_name from users where id=1", new ResultHandlerGetName());

            //with anonymous class
            exec.execQuery("select user_name from users where id=1",
                    new ResultHandler() {
                        @Override
                        public void handle(ResultSet result) throws SQLException {
                            result.next();
                            System.out.println("Read user: " + result.getString("user_name"));
                        }
                    }
            );

            //with lambda
            exec.execQuery("select user_name from users where id=1",
                    result -> {
                        result.next();
                        System.out.println("Read user: " + result.getString("user_name"));
                    });

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                exec.execUpdate("drop table users");
                System.out.println("Done!");

                connection.close();
            } catch (Exception err) {
                System.err.println(err.getMessage());
            }
        }
    }


}

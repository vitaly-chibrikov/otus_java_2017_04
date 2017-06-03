package ru.otus.examples;

import java.sql.Connection;
import java.sql.SQLException;

import ru.otus.user.UsersDAO;
import ru.otus.user.UsersDataSet;
import ru.otus.executor.SimpleExecutor;

public class DataSetExample {
    public static void example() {
        try {
            Connection connection = ConnectionHelper.getConnection();
            SimpleExecutor exec = new SimpleExecutor(connection);

            exec.execUpdate("create table users (id bigint auto_increment, user_name varchar(256), primary key (id))");
            System.out.println("DB created");
            exec.execUpdate("insert into users (user_name) values ('tully')");
            System.out.println("User added");

            UsersDAO userDAO = new UsersDAO(connection);
            UsersDataSet result = userDAO.get(1);

            System.out.println("User id: " + result.getId() + ", name: " + result.getName());

            exec.execUpdate("drop table users");
            System.out.println("Done!");

            connection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}

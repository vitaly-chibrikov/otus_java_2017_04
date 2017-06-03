package ru.otus.examples;

import java.sql.Connection;
import java.sql.SQLException;

import ru.otus.user.UsersDataSet;
import ru.otus.executor.SimpleExecutor;
import ru.otus.executor.TExecutor;

public class TExample {
    public static void example() {
        Connection connection = ConnectionHelper.getConnection();
        SimpleExecutor exec = new SimpleExecutor(connection);
        try {
            exec.execUpdate("create table users (id bigint auto_increment, user_name varchar(256), primary key (id))");
            System.out.println("Table created");
            exec.execUpdate("insert into users (user_name) values ('tully')");
            System.out.println("User added");

            TExecutor execT = new TExecutor(connection);

            String name = execT.execQuery("select user_name from users where id=1", result -> {
                result.next();
                return result.getString(1);
            });

            UsersDataSet dataSet = execT.execQuery("select id, user_name from users where id=1", result -> {
                result.next();
                return new UsersDataSet(result.getInt(1), result.getString(2));
            });

            System.out.println("Read user: " + name);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                exec.execUpdate("drop table users");
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
            System.out.println("Done!");
        }
    }
}

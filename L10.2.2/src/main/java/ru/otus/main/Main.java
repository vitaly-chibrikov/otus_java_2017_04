package ru.otus.main;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        //ConnectionFactory connectionFactory = new JDBCConnectionFactory();
        ConnectionFactory connectionFactory = new MyPoolConnectionFactory(new JDBCConnectionFactory());

        try (Connection connection = connectionFactory.get()) {
            System.out.println("Connected to: " + connection.getMetaData().getURL());
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionFactory.dispose();
    }
}

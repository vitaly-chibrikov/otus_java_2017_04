package ru.otus.h2;

import org.h2.tools.Server;

import java.sql.SQLException;

/**
 * http://localhost:8082
 */

public class Main {
    public static void main(String[] args) throws SQLException {
        Server.createWebServer("-web","-webAllowOthers","-webPort","8082").start();
        ConnectionHelper.example();
    }
}

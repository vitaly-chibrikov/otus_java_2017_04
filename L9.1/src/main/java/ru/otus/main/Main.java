package ru.otus.main;

import ru.otus.examples.ConnectionHelper;
import ru.otus.examples.TExample;

/**
 * mysql> CREATE USER 'tully'@'localhost' IDENTIFIED BY 'tully';
 * mysql> GRANT ALL PRIVILEGES ON * . * TO 'tully'@'localhost';
 * mysql> select user, host from mysql.user;
 * mysql> create database db_example;
 * mysql> SET GLOBAL time_zone = '+1:00';
 */

public class Main {
    public static void main(String[] args) {
        ConnectionHelper.example();

        //ConnectWithStatements.example();
        //PreparedExample.example();
        //TExample.example();
        //DataSetExample.example();
    }
}

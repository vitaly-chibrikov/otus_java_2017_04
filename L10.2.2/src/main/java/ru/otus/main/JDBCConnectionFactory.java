package ru.otus.main;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectionFactory implements ConnectionFactory {

    public Connection get() {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            String url = "jdbc:mysql://" +       //db type
                    "localhost:" +               //host name
                    "3306/" +                    //port
                    "db_example?" +              //db name
                    "useSSL=false&" +            //do not use ssl
                    "user=tully&" +              //login
                    "password=tully";            //password

            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dispose() {
        //do nothing
    }

}

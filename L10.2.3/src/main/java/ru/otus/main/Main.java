package ru.otus.main;

import java.beans.PropertyVetoException;
import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws PropertyVetoException, SQLException, InterruptedException {
        //DataSourceFactory factory = new C3P0DataSourceFactory();
        //DataSourceFactory factory = new DBCPDataSourceFactory();
        DataSourceFactory factory = new HikariCPDataSourceFactory();

        Connection connection = factory.get().getConnection();
        System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());

        hold();
    }


    @SuppressWarnings("InfiniteLoopStatement")
    private static void hold() throws InterruptedException {
        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());
        while(true){
            Thread.sleep(1000);
        }
    }

}

package ru.otus.main;

import com.mchange.v2.c3p0.ComboPooledDataSource;


import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Created by tully.
 */
public class C3P0DataSourceFactory implements DataSourceFactory {
    @Override
    public DataSource get() throws PropertyVetoException {
        ComboPooledDataSource cpds = new ComboPooledDataSource();

        cpds.setDriverClass( "com.mysql.cj.jdbc.Driver" );
        cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/db_example" );
        cpds.setUser("tully");
        cpds.setPassword("tully");

        cpds.setInitialPoolSize(10);
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(1);
        cpds.setMaxPoolSize(20);
        return cpds;
    }
}

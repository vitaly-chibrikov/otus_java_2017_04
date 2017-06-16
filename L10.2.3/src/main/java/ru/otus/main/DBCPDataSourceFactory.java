package ru.otus.main;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Created by tully.
 */
public class DBCPDataSourceFactory implements DataSourceFactory {
    @Override
    public DataSource get() throws PropertyVetoException {
        BasicDataSource ds = new org.apache.commons.dbcp2.BasicDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUsername("tully");
        ds.setPassword("tully");
        ds.setUrl("jdbc:mysql://localhost:3306/db_example");

        ds.setInitialSize(10);
        ds.setMaxTotal(20);

        return ds;
    }
}

package ru.otus.main;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

/**
 * Created by tully.
 */
public class HikariCPDataSourceFactory implements DataSourceFactory {
    @Override
    public DataSource get() {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://localhost:3306/db_example");
        config.setUsername("tully");
        config.setPassword("tully");

        config.setMaximumPoolSize(10);
        config.setAutoCommit(false);
        config.setRegisterMbeans(true);

        return new HikariDataSource(config);
    }

}

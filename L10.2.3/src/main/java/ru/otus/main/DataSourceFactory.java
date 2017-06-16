package ru.otus.main;


import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Created by tully.
 */
public interface DataSourceFactory {
    DataSource get() throws PropertyVetoException;
}

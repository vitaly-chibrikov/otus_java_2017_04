package ru.otus.main;

import java.sql.Connection;

/**
 * Created by tully.
 */
public interface ConnectionFactory {
    Connection get();

    void dispose();
}

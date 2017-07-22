package ru.otus.l151.app;

/**
 * Created by tully.
 */
public interface DBService {
    void init();

    int getUserId(String name);
}

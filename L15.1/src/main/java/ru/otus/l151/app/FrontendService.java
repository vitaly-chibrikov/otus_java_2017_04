package ru.otus.l151.app;

/**
 * Created by tully.
 */
public interface FrontendService {
    void init();

    void handleRequest(String login);

    void addUser(int id, String name);
}


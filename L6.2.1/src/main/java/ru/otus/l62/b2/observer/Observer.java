package ru.otus.l62.b2.observer;

/**
 * Created by tully.
 *
 * Abstract observer in the Observer interface.
 */
@FunctionalInterface
public interface Observer {
    void notify(Event event);
}

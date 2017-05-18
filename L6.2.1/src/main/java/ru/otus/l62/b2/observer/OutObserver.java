package ru.otus.l62.b2.observer;

/**
 * Created by tully.
 */
public class OutObserver implements Observer {
    @Override
    public void notify(Event event) {
        System.out.println(event.getClass());
    }
}

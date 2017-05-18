package ru.otus.l62.b2.observer;

/**
 * Created by tully.
 */
public class Main {
    public static void main(String[] args) {
        Subject subject = new Subject();
        subject.register(new OutObserver());
        subject.register(new OutObserver());
        subject.register(new ErrObserver());

        subject.notify(new Event());
    }
}

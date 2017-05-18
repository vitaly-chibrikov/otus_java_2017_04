package ru.otus.l62.b1.command;

/**
 * Created by tully.
 * <p>
 * Receiver of the Command pattern.
 */
public class Receiver {
    /**
     * action() method in Command pattern
     *
     * @param msg message to print
     */
    public void action(String msg) {
        System.out.println(msg);
    }
}

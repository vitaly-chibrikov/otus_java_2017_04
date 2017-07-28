package ru.otus.l152.counter;

/**
 * Created by tully.
 */
public class CallCounterSimple implements CallCounter {
    private int count = 0;

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }

}

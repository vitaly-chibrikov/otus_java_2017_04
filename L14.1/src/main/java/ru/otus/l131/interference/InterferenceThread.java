package ru.otus.l131.interference;

public final class InterferenceThread extends Thread {
    private static int i;

    public void run() {
        while (CounterSemaphore.instance().isIncrementAndCheck()) {
            increment();
        }
    }

    private void increment() {
        i++;
    }

    int getI() {
        return i;
    }
}

package ru.otus.l131;

public class RandomRun extends Thread {

    private static final int MAX_THREADS_COUNT = 20;

    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    static void example() {
        for (int i = 0; i < MAX_THREADS_COUNT; ++i) {
            Thread thread = new RandomRun();
            thread.start();
        }
    }
}

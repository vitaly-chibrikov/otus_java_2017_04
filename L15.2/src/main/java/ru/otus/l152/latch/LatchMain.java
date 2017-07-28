package ru.otus.l152.latch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by tully.
 */
public class LatchMain {
    private static final int THREADS_COUNT = 10;

    private final CountDownLatch latch = new CountDownLatch(THREADS_COUNT);

    public static void main(String[] args) throws InterruptedException {
        new LatchMain().start();
    }


    private void start() throws InterruptedException {
        Runnable countDown = () -> {
            System.out.println(Thread.currentThread().getName());
            latch.countDown();
        };

        for (int i = 0; i < THREADS_COUNT; i++) {
            new Thread(countDown).start();
        }

        latch.await();

        System.out.println("All done");
    }
}

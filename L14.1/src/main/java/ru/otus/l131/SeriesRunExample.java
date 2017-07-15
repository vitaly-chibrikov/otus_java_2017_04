package ru.otus.l131;

public class SeriesRunExample extends Thread {
    private static final int MAX_THREADS_COUNT = 10;

    private final Object waitObject = SeriesRunExample.class;

    private static int currentMax = 1;
    private int threadId;

    static void example() {
        for (int i = currentMax; i <= MAX_THREADS_COUNT; ++i) {
            new SeriesRunExample(i).start();
        }
    }

    private SeriesRunExample(int id) {
        this.threadId = id;
    }

    @Override
    public void run() {
        try {
            synchronized (waitObject) {
                while (threadId > currentMax) {
                    System.out.println("Waiting id: " + threadId);
                    waitObject.wait();
                }

                currentMax++;
                System.out.println("Thread id: " + threadId);
                waitObject.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

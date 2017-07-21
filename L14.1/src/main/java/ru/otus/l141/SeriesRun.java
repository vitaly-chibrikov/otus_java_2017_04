package ru.otus.l141;

/**
 * Example of threads' organization.
 * All threads try to print self id. But we need them to print it in serial order.
 */
class SeriesRun {
    private static final int MAX_THREADS_COUNT = 10;

    private int currentMax = 1;

    void start() {
        for (int i = currentMax; i <= MAX_THREADS_COUNT; ++i) {
            int threadId = i; //effective final
            new Thread(
                    () -> {
                        try {
                            synchronized (SeriesRun.this) {
                                while (threadId > currentMax) {
                                    System.out.println("Waiting id: " + threadId);
                                    SeriesRun.this.wait();
                                }

                                currentMax++;
                                System.out.println("Thread id: " + threadId);
                                SeriesRun.this.notifyAll();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            ).start();
        }
    }

}

package ru.otus.l152.counter;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author v.chibrikov
 */
public class CounterMain {
    private static final int HUNDRED_MILLION = 100_000_000;
    private static final int THREADS_NUMBER = 2;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_NUMBER);

        CallCounter counter = new CallCounterSimple();
        AtomicInteger realCountNumber = new AtomicInteger();

        Callable<Integer> callable = () -> {
            while (realCountNumber.incrementAndGet() < HUNDRED_MILLION) {
                counter.increment();
            }
            return counter.getCount();
        };

        long startTime = System.currentTimeMillis();


        Future<Integer> future01 = executorService.submit(callable);
        Future<Integer> future02 = executorService.submit(callable);

        System.out.println("Future01: " + future01.get());
        System.out.println("Future02: " + future02.get());
        System.out.println("RealCountNumber: " + realCountNumber);

        long finishTime = System.currentTimeMillis();
        System.out.println("Time spent: " + (finishTime - startTime));
        executorService.shutdown();
    }
}

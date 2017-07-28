package ru.otus.l152.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by tully.
 */
public class ExecutorServiceMain {
    private static final long SLEEP_TIME_MS = TimeUnit.SECONDS.toMillis(2);
    private static final long SCHEDULE_TIME_SEC = 5;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new ExecutorServiceMain().start();
    }

    private void start() throws ExecutionException, InterruptedException {
        futureForRunnableExample();
        futureForRunnableMessagesExample();
        futureForCallableExample();

        futureForArrayExample();
        schedulingExample();
    }

    private void futureForRunnableExample() throws InterruptedException, ExecutionException {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        Future<?> futureNull = singleThreadExecutor.submit(() -> {
            try {
                Thread.sleep(SLEEP_TIME_MS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Object objectNull = futureNull.get();
        System.out.println("Result is: " + objectNull);
    }


    private void futureForRunnableMessagesExample() throws InterruptedException, ExecutionException {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        List<String> messages = new ArrayList<>();

        Future<?> futureNull = singleThreadExecutor.submit(() -> {
            try {
                messages.add("Hello");
                Thread.sleep(SLEEP_TIME_MS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        futureNull.get();
        System.out.println("Messages: " + messages.get(0));
    }

    private void futureForCallableExample() throws InterruptedException, ExecutionException {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        Future<List<String>> futureNull = singleThreadExecutor.submit(() -> {
            List<String> messages = new ArrayList<>();
            try {

                messages.add("Hello");
                Thread.sleep(SLEEP_TIME_MS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return messages;
        });

        List<String> messages = futureNull.get();
        System.out.println("Messages: " + messages.get(0));
    }

    private void futureForArrayExample() throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(5);

        List<Future<Integer>> resultsFuture = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final int taskIndex = i;
            resultsFuture.add(pool.submit(() -> {
                Thread.sleep(SLEEP_TIME_MS);
                return taskIndex;
            }));
        }

        Iterator<Future<Integer>> iterator = resultsFuture.iterator();
        while (iterator.hasNext()) {
            System.out.println("Result from the thread: " + iterator.next().get());
        }
        pool.shutdown();
    }


    private void schedulingExample() {
        ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();
        Runnable job = () -> System.out.println("Message from a thread");
        pool.schedule(job, SCHEDULE_TIME_SEC, TimeUnit.SECONDS);
        pool.shutdown();
    }
}

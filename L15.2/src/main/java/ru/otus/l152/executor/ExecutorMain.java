package ru.otus.l152.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by tully.
 */
public class ExecutorMain {
    public static void main(String[] args) {
        new ExecutorMain().start();
    }

    private void start() {
        Runnable runnable = () -> System.out.println("Thread name: " + Thread.currentThread().getName());

        Executor executor;

        executor = getSameThreadExecutor();
        executor.execute(runnable);

        executor = getNewThreadExecutor();
        executor.execute(runnable);

        executor = getPoolExecutor();
        executor.execute(runnable);
    }

    private Executor getSameThreadExecutor() {
        return Runnable::run;
    }

    private Executor getNewThreadExecutor() {
        return command -> new Thread(command).start();
    }

    private Executor getPoolExecutor() {
        return Executors.newSingleThreadExecutor();
    }

}

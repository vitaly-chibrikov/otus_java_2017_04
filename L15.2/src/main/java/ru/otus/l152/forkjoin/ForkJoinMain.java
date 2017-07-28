package ru.otus.l152.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * Created by tully.
 */
public class ForkJoinMain {
    private static final int SLEEP_TIME_MILLIS = 1000;
    private static final int PARALLELISM = 1;
    private static final int NUMBER_TO_SUM = 10;
    private static final int SUBTASKS_COUNT = 1;

    private final Queue<Integer> sequentialQueue = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new ForkJoinMain().start();
    }

    private void start() throws ExecutionException, InterruptedException {
        for (int i = 1; i <= NUMBER_TO_SUM; i++) {
            sequentialQueue.add(i);
        }

        ForkJoinPool pool = new ForkJoinPool(PARALLELISM);
        ForkJoinTask<Integer> task = new LoadRecursive();

        long start = System.currentTimeMillis();
        Integer result = pool.invoke(task);

        System.out.println("Done in " + (System.currentTimeMillis() - start) + "ms : " + result);
    }

    private void sleep() {
        try {
            Thread.sleep(SLEEP_TIME_MILLIS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class LoadRecursive extends RecursiveTask<Integer> {

        @Override
        protected Integer compute() {
            Integer item = sequentialQueue.poll();
            if (item != null) {
                System.out.println(Thread.currentThread().getName() + ": add " + item);
                sleep();

                List<LoadRecursive> tasks = new ArrayList<>();

                //fork
                for (int i = 0; i < SUBTASKS_COUNT; i++) {
                    LoadRecursive subTask = new LoadRecursive();
                    subTask.fork();
                    tasks.add(subTask);
                }

                //join
                Integer result = item + tasks.stream().mapToInt(ForkJoinTask::join).sum();

                System.out.println(Thread.currentThread().getName() + ": result: " + result);
                return result;
            }
            return 0;
        }
    }
}

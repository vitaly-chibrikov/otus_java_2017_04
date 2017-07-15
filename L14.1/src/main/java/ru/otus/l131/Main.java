package ru.otus.l131;

import ru.otus.l131.interference.Interference;

import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws InterruptedException {
        RandomRun.example();
        printThreadInfo();

        //calculate ms between start and run

        SeriesRunExample.example();
        new Interference().example();
    }

    private static void printThreadInfo() throws InterruptedException {
        //get an object ot the main thread
        Thread mainThread = Thread.currentThread();
        logger.info("Main thread name: " + mainThread.getName());
        //set name to the thread
        mainThread.setName("MyMain");
        //get priority of the main thread
        logger.info("Main thread priority: " + mainThread.getPriority());

        //create new thread with runnable lambda
        Thread thread2 = new Thread(() -> {
            logger.info("Hello from thread: " + Thread.currentThread().getName());
        });
        //set daemon
        thread2.setDaemon(true);

        thread2.start();

        //join to a the main thread
        thread2.join();

        //get thread state
        logger.info("Main thread state: " + mainThread.getState());
        logger.info("thread2 state: " + thread2.getState());
    }
}

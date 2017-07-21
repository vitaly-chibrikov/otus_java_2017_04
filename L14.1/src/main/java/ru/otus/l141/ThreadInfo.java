package ru.otus.l141;


import java.util.logging.Logger;

class ThreadInfo {
    private static final Logger logger = Logger.getLogger(ThreadInfo.class.getName());

    static void print() throws InterruptedException {
        //get an object ot the main thread
        Thread mainThread = Thread.currentThread();
        logger.info("Thread name: " + mainThread.getName());
        //set name to the thread
        mainThread.setName("MyMain");
        //get priority of the main thread
        logger.info("Thread priority: " + mainThread.getPriority());

        //create new thread with runnable lambda
        Thread thread2 = new Thread(() -> logger.info("Hello from the thread: " + Thread.currentThread().getName()));
        //set daemon
        thread2.setDaemon(true);

        thread2.start();

        thread2.setPriority(10);

        //join to a the main thread
        //thread2.join();

        //get thread state
        logger.info("Main thread state: " + mainThread.getState());
        logger.info("thread2 state: " + thread2.getState());
    }
}

package ru.otus.l131.interference;

/**
 * @author v.chibrikov
 */
public class Interference {

    public void example() throws InterruptedException {
        InterferenceThread thread1 = new InterferenceThread();
        InterferenceThread thread2 = new InterferenceThread();
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println("Expected: " + CounterSemaphore.HUNDRED_MILLION);
        System.out.println("Result: " + thread1.getI());
    }
}

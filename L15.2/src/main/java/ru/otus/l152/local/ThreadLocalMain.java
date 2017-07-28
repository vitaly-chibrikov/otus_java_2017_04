package ru.otus.l152.local;

/**
 * Created by tully.
 */
public class ThreadLocalMain {

    public static void main(String[] args) throws InterruptedException {
        //ThreadLocal<Integer> value = ThreadLocal.withInitial(() -> 0);
        Value<Integer> value = new Value<>(0);

        Runnable sharedRunnable = () -> {
            int count = 0;
            while (count < 5_000) {
                int current = value.get();
                value.set(++current);

                count++;
            }
            System.out.println(Thread.currentThread().getName() + ": " + value.get());
        };

        Thread thread1 = new Thread(sharedRunnable);
        Thread thread2 = new Thread(sharedRunnable);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(Thread.currentThread().getName() + ": " + value.get());
    }

    private static class Value<T> {
        private T value;

        Value(T value) {
            this.value = value;
        }

        T get() {
            return value;
        }

        void set(T value) {
            this.value = value;
        }
    }
}

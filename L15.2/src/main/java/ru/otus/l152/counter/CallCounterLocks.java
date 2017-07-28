package ru.otus.l152.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tully.
 */
public class CallCounterLocks implements CallCounter {
    private final Lock lock = new ReentrantLock();

    private int count = 0;

    public void increment() {
        try {
            lock.lock();
            innerLogic();
        } finally {
            lock.unlock();
        }
    }

    private void innerLogic() {
        try {
            lock.lock();
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        try {
            lock.lock();
            return count;
        } finally {
            lock.unlock();
        }
    }
}

package ru.otus.l152.counter;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by tully.
 */
public class CallCounterReadWriteLock implements CallCounter {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private int count = 0;

    public void increment() {
        try {
            lock.writeLock().lock();
            count++;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int getCount() {
        try {
            lock.readLock().lock();
            return count;
        } finally {
            lock.readLock().unlock();
        }
    }
}

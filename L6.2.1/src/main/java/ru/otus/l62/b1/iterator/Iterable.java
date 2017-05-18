package ru.otus.l62.b1.iterator;

/**
 * Created by tully.
 *
 * Abstract Collection in the Iterator pattern.
 */
public interface Iterable<T> {
    Iterator<T> getIterator();
}

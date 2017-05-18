package ru.otus.l62.b1.iterator;

/**
 * Created by tully.
 *
 * Traversal abstraction in the Iterator pattern.
 *
 */
public interface Iterator<T> {
    T next();
    boolean hasNext();
}

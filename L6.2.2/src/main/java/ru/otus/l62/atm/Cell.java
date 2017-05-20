package ru.otus.l62.atm;

import java.util.Iterator;

/**
 * Created by tully.
 */
public class Cell implements Comparable<Cell>, Iterable<Cell> {
    private final int nominal;
    private int count;

    private Cell next;

    public Cell(int nominal, int count) {
        this.nominal = nominal;
        this.count = count;
    }

    public boolean withdraw(int requested) {
        int expectedCount = Math.min(requested / nominal, count);
        int expectedCash = expectedCount * nominal;
        boolean nextCellResult = true;
        if (requested != expectedCash) {
            nextCellResult = next != null && next.withdraw(requested - expectedCash);
        }
        if(nextCellResult) {
            count = count - expectedCount;
            return true;
        }
        return false;
    }

    public int getNominal() {
        return nominal;
    }

    public int getCount() {
        return count;
    }

    public void setNext(Cell next) {
        this.next = next;
    }

    public int getBalance() {
        return count * nominal;
    }

    @Override
    public int compareTo(Cell o) {
        if (nominal > o.getNominal())
            return -1;
        if (nominal < o.getNominal())
            return 1;
        return 0;
    }

    @Override
    public Iterator<Cell> iterator() {
        return new Iterator<Cell>() {
            Cell current = Cell.this;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Cell next() {
                Cell before = current;
                current = current.next;
                return before;
            }
        };
    }
}

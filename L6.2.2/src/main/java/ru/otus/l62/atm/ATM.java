package ru.otus.l62.atm;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tully.
 */
public class ATM {
    private final Cell first;

    public ATM(List<Cell> cells) {
        Collections.sort(cells);
        first = cells.get(0);
        linkCells(cells);
    }

    public boolean withdraw(int requested) {
        return first.withdraw(requested);
    }

    public int getBalance() {
        Iterator<Cell> iterator = first.iterator();
        int balance = 0;
        while (iterator.hasNext()) {
            balance += iterator.next().getBalance();
        }
        return balance;
    }

    private void linkCells(List<Cell> cells) {
        Iterator<Cell> iterator = cells.iterator();
        Cell cellA = iterator.next();
        while (iterator.hasNext()) {
            Cell cellB = iterator.next();
            cellA.setNext(cellB);
            cellA = cellB;
        }
    }
}

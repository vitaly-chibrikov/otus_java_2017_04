package ru.otus.l62.atm;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tully.
 */
public class ATMTest {

    @Test
    public void balanceSimple() {
        List<Cell> cells = new ArrayList<>();

        cells.add(new Cell(5, 10));
        ATM atm = new ATM(cells);
        Assert.assertEquals(50, atm.getBalance());
    }

    @Test
    public void withdrawSimple() {
        List<Cell> cells = new ArrayList<>();

        cells.add(new Cell(1, 10));
        cells.add(new Cell(5, 10));
        cells.add(new Cell(10, 10));

        ATM atm = new ATM(cells);
        int balance = atm.getBalance();

        Assert.assertTrue(atm.withdraw(27));
        Assert.assertEquals(balance - 27, atm.getBalance());
    }

    @Test
    public void withdrawFail() {
        List<Cell> cells = new ArrayList<>();

        cells.add(new Cell(5, 10));
        cells.add(new Cell(10, 10));

        ATM atm = new ATM(cells);
        int balance = atm.getBalance();

        Assert.assertFalse(atm.withdraw(27));
        Assert.assertEquals(balance, atm.getBalance());
    }
    
    @Test
    public void withdrawEdgeCase() {
        List<Cell> cells = new ArrayList<>();

        cells.add(new Cell(20, 3));
        cells.add(new Cell(50, 1));

        ATM atm = new ATM(cells);

        Assert.assertTrue(atm.withdraw(60));
        Assert.assertEquals(50, atm.getBalance());
    }
}

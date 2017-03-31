package ru.otus.lottery;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by tully.
 */
public class LotteryMachineTest {
    @Test
    public void oneEmail() {
        Assert.assertEquals(1, new LotteryMachine(Collections.singletonList("test"), 5).draw().size());
    }

    @Test
    public void fiveEmails() {
        List<String> result = new LotteryMachine(Arrays.asList("0", "1", "2", "3", "4"), 5).draw();
        Assert.assertEquals(5, result.size());
        Assert.assertTrue(result.contains("0"));
        Assert.assertTrue(result.contains("1"));
        Assert.assertTrue(result.contains("2"));
        Assert.assertTrue(result.contains("3"));
        Assert.assertTrue(result.contains("4"));
    }

    @Test
    public void tenEmailsSeed0() {
        List<String> result = new LotteryMachine(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"), 5).setSeed(0).draw();
        Assert.assertEquals(5, result.size());
        Assert.assertTrue(result.contains("0"));
        Assert.assertTrue(result.contains("5"));
        Assert.assertTrue(result.contains("7"));
        Assert.assertTrue(result.contains("8"));
        Assert.assertTrue(result.contains("9"));
    }

    @Test
    public void tenEmailsSeed100500() {
        List<String> result = new LotteryMachine(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"), 5).setSeed(100500).draw();
        Assert.assertEquals(5, result.size());
        Assert.assertTrue(result.contains("1"));
        Assert.assertTrue(result.contains("3"));
        Assert.assertTrue(result.contains("7"));
        Assert.assertTrue(result.contains("0"));
        Assert.assertTrue(result.contains("9"));
    }

}

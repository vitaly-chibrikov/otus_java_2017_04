package ru.otus.l42;

import org.junit.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tully.
 */
public class LotteryMachineChildTest {

    private LotteryMachine lotteryMachine;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before all LotteryMachineChildTest tests");
    }

    @Before
    public void before() {
        System.out.println("Before LotteryMachineChildTest");
        lotteryMachine = new LotteryMachine(5);
    }

    @After
    public void afterLotteryMachineTest() {
        System.out.println("After LotteryMachineChildTest");
        lotteryMachine.dispose();
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("After all LotteryMachineChildTest tests");
    }


    @Test
    public void tenEmailsSeed0() {
        List<String> result = lotteryMachine.setSeed(0)
                .draw(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
        Assert.assertEquals(5, result.size());
        Assert.assertTrue(result.contains("0"));
        Assert.assertTrue(result.contains("5"));
        Assert.assertTrue(result.contains("7"));
        Assert.assertTrue(result.contains("8"));
        Assert.assertTrue(result.contains("9"));
    }

    @Test
    public void tenEmailsSeed100500() {
        List<String> result = lotteryMachine.setSeed(100500)
                .draw(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
        Assert.assertEquals(5, result.size());
        Assert.assertTrue(result.contains("1"));
        Assert.assertTrue(result.contains("3"));
        Assert.assertTrue(result.contains("7"));
        Assert.assertTrue(result.contains("0"));
        Assert.assertTrue(result.contains("9"));
    }
}

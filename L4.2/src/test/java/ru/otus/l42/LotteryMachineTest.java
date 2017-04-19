package ru.otus.l42;

import org.junit.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**1
 * Created by tully.
 */
public class LotteryMachineTest {

    private LotteryMachine lotteryMachine;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before all LotteryMachineTest tests");
    }

    @Before
    public void before() {
        System.out.println("Before LotteryMachineTest");
        lotteryMachine = new LotteryMachine(5);
    }

    @After
    public void afterLotteryMachineTest() {
        System.out.println("After LotteryMachineTest");
        lotteryMachine.dispose();
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("After all LotteryMachineTest tests");
    }


    @Test
    public void oneEmail() {
        Assert.assertEquals(1, lotteryMachine.draw(Collections.singletonList("test")).size());
    }

    @Test
    public void fiveEmails() {
        List<String> result = lotteryMachine
                .draw(Arrays.asList("0", "1", "2", "3", "4"));
        assertEquals(5, result.size());
        Assert.assertTrue(result.contains("0"));
        Assert.assertTrue(result.contains("1"));
        Assert.assertTrue(result.contains("2"));
        Assert.assertTrue(result.contains("3"));
        Assert.assertTrue(result.contains("4"));
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

    @Ignore
    @Test(timeout = 100)
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

    @Test(expected = NullPointerException.class)
    public void NPETest() {
        List<String> result = lotteryMachine.setSeed(100500)
                .draw(null);
        Assert.assertEquals(0, result.size());
    }

}

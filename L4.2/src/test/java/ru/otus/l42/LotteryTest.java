package ru.otus.l42;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by tully.
 */
public class LotteryTest {

    @Test
    public void mockedLottery() {
        EmailsReader emailsReader = mock(EmailsReader.class);
        LotteryMachine lotteryMachine = mock(LotteryMachine.class);

        String seedString = "May the Force be with you";
        when(emailsReader.get()).thenReturn(Arrays.asList("0", "1", "2", "3", "4"));
        when(lotteryMachine.setSeed(seedString)).thenReturn(lotteryMachine);

        Lottery lottery = new Lottery(emailsReader, lotteryMachine, seedString);
        lottery.run();

        verify(emailsReader, times(1)).get();
        verify(lotteryMachine, times(1)).setSeed(seedString);
        verify(lotteryMachine, never()).dispose();
    }

    @Test
    public void mockedWithSpy() {
        EmailsReader emailsReader = mock(EmailsReader.class);

        List<String> list = new ArrayList<>();
        List<String> spy = spy(list);
        doReturn(1).when(spy).size();
        doReturn("0").when(spy).get(0);

        when(emailsReader.get()).thenReturn(spy);

        String seedString = "Test";

        LotteryMachine lotteryMachine = new LotteryMachine(5);
        LotteryMachine spyMachine = spy(lotteryMachine);

        //set seed to 0
        when(spyMachine.setSeed(seedString)).thenReturn(spyMachine.setSeed(0));

        Lottery lottery = new Lottery(emailsReader, spyMachine, seedString);
        lottery.run();
        Assert.assertEquals(0, lotteryMachine.getSeed());
    }
}

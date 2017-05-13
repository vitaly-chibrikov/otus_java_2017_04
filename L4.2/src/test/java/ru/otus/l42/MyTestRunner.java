package ru.otus.l42;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Created by tully.
 */
public class MyTestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(LotteryMachineTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }
}

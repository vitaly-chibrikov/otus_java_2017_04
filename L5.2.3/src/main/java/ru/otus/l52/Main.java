package ru.otus.l52;

import java.lang.management.ManagementFactory;

/**
 * Created by tully.
 */

public class Main {
    public static void main(String... args) throws InterruptedException {
        new Main().pid();
    }

    @AddLog(message = "Test 42")
    private void pid() {
        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());
    }
}

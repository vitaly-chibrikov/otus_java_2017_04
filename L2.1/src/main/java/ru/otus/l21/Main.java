package ru.otus.l21;

import java.lang.management.ManagementFactory;

/**
 * Created by tully.
 */
//VM options -Xmx512m -Xms512m
public class Main {
    public static void main(String... args) throws InterruptedException {
        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());
        //Runtime runtime = Runtime.getRuntime();
        //RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

        int size = 50 * 1024 * 1024;
        Object[] array = new Object[size];
        System.out.println("Array of size: " + array.length + " created");
        Thread.sleep(10 * 1000);

        int n = 0;
        System.out.println("Starting the loop");
        while (n < Integer.MAX_VALUE) {
            int i = n % size;
            array[i] = new String(""); //no String pool
            n++;
            if (n % 1024 == 0) {
                Thread.sleep(1);
            }
            if (n % size == 0) {
                System.out.println("Created " + n + " objects");
                System.out.println("Creating new array");
                array = new Object[size];
            }
        }
    }
}

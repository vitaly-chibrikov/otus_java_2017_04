package ru.otus.l21;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * Created by tully.
 */
//VM options -Xmx512m -Xms512m
//VM options -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=4000, suspend=n
//VM options  -javaagent:target/L2.1.jar
//VM options  -XX:+UseSerialGC

public class Main {
    public static void main(String... args) throws InterruptedException {
        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());

        //installGCMonitoring();

        int size = 10 * 1024 * 1024;
        Object[] array = new Object[size];
        System.out.println("Array of size: " + array.length + " created");
        Thread.sleep(10 * 1000);

        reFillArray(array);
    }

    private static void reFillArray(Object[] array) throws InterruptedException {
        int size = array.length;
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

    private static void installGCMonitoring() {
        List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcbean : gcbeans) {
            NotificationEmitter emitter = (NotificationEmitter) gcbean;
            System.out.println(gcbean.getName());

            NotificationListener listener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());

                    long duration = info.getGcInfo().getDuration();
                    String gctype = info.getGcAction();

                    System.out.println(gctype + ": - "
                            + info.getGcInfo().getId() + ", "
                            + info.getGcName()
                            + " (from " + info.getGcCause() + ") " + duration + " milliseconds");

                }
            };

            emitter.addNotificationListener(listener, null, null);
        }
    }
}

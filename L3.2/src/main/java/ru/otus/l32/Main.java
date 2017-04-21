package ru.otus.l32;

import com.google.common.collect.Collections2;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

/**
 * Created by tully.
 */
public class Main {
    private static final int MEASURE_COUNT = 100;

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public static void main(String... args) {
        Collection<Integer> example = new HashSet<>();
        int min = 0;
        int max = 9_999_999;
        for (int i = min; i < max + 1; i++) {
            example.add(i);
        }

        calcTime(() -> example.contains(min));
    }

    private static void calcTime(Runnable runnable) {
        long startTime = System.nanoTime();
        for (int i = 0; i < MEASURE_COUNT; i++)
            runnable.run();
        long finishTime = System.nanoTime();
        long timeNs = (finishTime - startTime) / MEASURE_COUNT;
        System.out.println("Time spent: " + timeNs + "ns (" + timeNs / 1_000_000 + "ms)");
    }
}

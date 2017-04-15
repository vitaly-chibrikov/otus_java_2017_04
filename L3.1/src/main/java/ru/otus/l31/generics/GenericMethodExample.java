package ru.otus.l31.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tully.
 */
public class GenericMethodExample {
    public static <T> T getTheFirst(List<T> list) {
        return list.get(0);
    }

    public static void main(String[] args) {
        List<Integer> listOfInts = new ArrayList<>();
        listOfInts.add(0);
        Integer intValue = getTheFirst(listOfInts);
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("Java is the best!");
        String stringValue = getTheFirst(listOfStrings);
    }
}

package ru.otus.l31;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tully.
 */
public class GenericClassExample<T> {
    public T value;

    public GenericClassExample() {
        this.value = null;
    }

    public GenericClassExample(T value) {
        this.value = value;
    }

    public T getT() {
        return value;
    }

    public static void main(String[] args) {
        GenericClassExample<Integer> intObject = new GenericClassExample<>(1);
        Integer valueInteger = intObject.getT();

        GenericClassExample<String> stringObject = new GenericClassExample<>("word");
        String valueString = stringObject.getT();
    }
}

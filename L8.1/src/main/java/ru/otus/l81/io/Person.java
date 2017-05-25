package ru.otus.l81.io;

import java.io.Serializable;

/**
 * Created by tully.
 */
public class Person implements Serializable{
    private final int age;
    private final String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + " age: " + getAge();
    }
}

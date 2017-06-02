package ru.otus.l821.main;

public class Person {
    private String name;
    private int age;

    public Person() {
        this.name = "Nobody";
        this.age = 0;
    }

    public Person(String name, int age) {
        this.setAge(age);
        this.setName(name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Name: " + name + " age: " + age;
    }
}

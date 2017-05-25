package ru.otus.l81.io;

/**
 * Created by tully.
 */
public class Student extends Person{


    private final String course;

    public Student(int age, String name, String course) {
        super(age, name);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    public String toString() {
        return super.toString() + " course: " + getCourse();
    }
}

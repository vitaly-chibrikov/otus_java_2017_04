package ru.otus.l71.structural.proxy;

/**
 * Created by tully.
 *
 * Real subject in the Procy pattern.
 */
public class SubjectImpl implements Subject {
    @Override
    public void doIt() {
        System.out.println("Done");
    }
}

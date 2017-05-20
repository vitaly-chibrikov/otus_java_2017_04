package ru.otus.l71.behavioral.visitor;

/**
 * Created by tully.
 */
public class CarWashService implements Service {
    @Override
    public void visit(Wheel element) {
        System.out.println("Washing: " + element.getName());
    }

    @Override
    public void visit(Engine element) {
        System.out.println("Washing: " + element.getName());
    }

    @Override
    public void visit(Body element) {
        System.out.println("Washing: " + element.getName());
    }
}

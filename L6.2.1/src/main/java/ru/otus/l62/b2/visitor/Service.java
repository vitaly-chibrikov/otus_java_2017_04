package ru.otus.l62.b2.visitor;

/**
 * Created by tully.
 *
 * Visitor in the Visitor pattern.
 * An abstract service for elements of a car.
 */
public interface Service {

    void visit(Wheel element);

    void visit(Engine element);

    void visit(Body element);
}

package ru.otus.l71.behavioral.visitor;

/**
 * Created by tully.
 */
public class Engine implements CarElement {
    @Override
    public String getName() {
        return "engine";
    }

    public void accept(Service visitor) {
        visitor.visit(this);
    }
}

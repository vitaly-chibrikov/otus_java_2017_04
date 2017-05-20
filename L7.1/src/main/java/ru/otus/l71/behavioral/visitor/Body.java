package ru.otus.l71.behavioral.visitor;

/**
 * Created by tully.
 */
public class Body implements CarElement {
    @Override
    public String getName() {
        return "body";
    }

    public void accept(Service visitor) {
        visitor.visit(this);
    }
}

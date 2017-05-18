package ru.otus.l62.b2.visitor;

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

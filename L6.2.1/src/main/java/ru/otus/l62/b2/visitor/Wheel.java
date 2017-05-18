package ru.otus.l62.b2.visitor;

/**
 * Created by tully.
 */
public class Wheel implements CarElement {
    @Override
    public String getName() {
        return "wheel";
    }

    public void accept(Service visitor) {
        visitor.visit(this);
    }
}

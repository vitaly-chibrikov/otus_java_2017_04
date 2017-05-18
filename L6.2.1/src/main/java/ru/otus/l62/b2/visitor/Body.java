package ru.otus.l62.b2.visitor;

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

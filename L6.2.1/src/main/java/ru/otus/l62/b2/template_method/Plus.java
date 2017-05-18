package ru.otus.l62.b2.template_method;

/**
 * Created by tully.
 */
public class Plus extends BaseClass {

    public Plus(int a, int b) {
        super(a, b);
    }

    @Override
    protected int apply(int a, int b) {
        return a + b;
    }
}

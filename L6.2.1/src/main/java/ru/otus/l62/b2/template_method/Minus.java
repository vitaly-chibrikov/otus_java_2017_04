package ru.otus.l62.b2.template_method;

/**
 * Created by tully.
 */
public class Minus extends BaseClass {
    public Minus(int a, int b) {
        super(a, b);
    }

    @Override
    protected int apply(int a, int b) {
        return a - b;
    }
}

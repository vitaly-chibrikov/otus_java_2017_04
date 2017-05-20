package ru.otus.l71.behavioral.visitor;

import java.util.Arrays;

/**
 * Client class in the Visitor pattern.
 * Contains elements for service.
 * <p>
 * Created by tully.
 */
public class Car {
    private final Body body;
    private final Engine engine;
    private final Wheel[] wheels;

    public Car() {
        engine = new Engine();
        body = new Body();
        wheels = new Wheel[4];
        Arrays.fill(wheels, new Wheel());
    }

    public void doService(Service service) {
        body.accept(service);
        engine.accept(service);
        for (Wheel wheel : wheels) {
            wheel.accept(service);
        }
    }
}

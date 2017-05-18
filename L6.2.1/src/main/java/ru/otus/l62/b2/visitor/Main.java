package ru.otus.l62.b2.visitor;

/**
 * Created by tully.
 */
public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        car.doService(new CarRepairService());
        car.doService(new CarWashService());
    }
}

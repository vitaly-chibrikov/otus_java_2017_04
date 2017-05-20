package ru.otus.l71.structural.decorator;

/**
 * Created by tully.
 */
public class Main {
    public static void main(String[] args) {
        Printer normalPrinter = new PrinterImpl();
        normalPrinter.print("Test");

        Printer decorator = new PrinterDecorator(normalPrinter);
        decorator.print("Decorator");


        Printer reversedPrinter = new ReversedPrinter(normalPrinter);


        Printer doubleReversedPrinter = new ReversedPrinter(reversedPrinter);

        normalPrinter.print("Hello");
        reversedPrinter.print("Hello");
        doubleReversedPrinter.print("Hello");
    }
}

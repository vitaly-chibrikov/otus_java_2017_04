package ru.otus.l141;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ThreadInfo.print();
        new RandomRun().start();
        new SeriesRun().start();
    }
}

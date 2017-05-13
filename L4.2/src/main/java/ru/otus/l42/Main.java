package ru.otus.l42;

import java.io.IOException;

/**
 * Created by tully.
 */
public class Main {
    private static final int MAX_WINNERS_COUNT = 5;

    public static void main(String[] args) throws IOException {
        String pathToFile;
        if (args.length >= 1) {
            pathToFile = args[0];
        } else {
            pathToFile = "emails.csv";
        }

        String seedString = "May the Force be with you";

        new Lottery(
                new EmailsReader(pathToFile),
                new LotteryMachine(MAX_WINNERS_COUNT),
                seedString).run();
    }
}

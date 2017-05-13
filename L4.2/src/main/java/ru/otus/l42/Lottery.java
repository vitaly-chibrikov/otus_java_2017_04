package ru.otus.l42;

import java.util.List;
import java.util.function.Supplier;

/**
 * Created by tully.
 */
class Lottery {
    private final Supplier<List<String>> emailsSource;
    private final LotteryMachine machine;
    private final  String seedString;

    Lottery(Supplier<List<String>> emailsSource, LotteryMachine machine, String seedString) {
        this.emailsSource = emailsSource;
        this.machine = machine;
        this.seedString = seedString;
    }

    void run() {
        List<String> emails = emailsSource.get();

        System.out.println("Emails count: " + emails.size());

        List<String> winners = machine.setSeed(seedString).draw(emails);

        System.out.println("Winners:");
        winners.forEach(System.out::println);
    }
}

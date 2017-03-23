package ru.otus.lottery;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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

        System.out.println("Reading emails from: " + pathToFile);

        CSVReader reader = new CSVReader(new FileReader(pathToFile));
        List<String> emails = reader.readAll().stream().map(line -> line[0].trim()).collect(Collectors.toList());

        System.out.println("Emails count: " + emails.size());

        String seedString = "ph'nglui mglw'nafh Cthulhu R'lyeh wgah'nagl fhtagn";

        List<String> winners = new LotteryMachine(emails, MAX_WINNERS_COUNT).setSeed(seedString).draw();

        System.out.println("Winners:");
        winners.forEach(System.out::println);
    }
}

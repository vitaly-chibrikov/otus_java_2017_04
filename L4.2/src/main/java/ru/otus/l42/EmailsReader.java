package ru.otus.l42;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by tully.
 */
public class EmailsReader implements Supplier<List<String>> {
    private final String pathToFile;

    EmailsReader(String pathToFile) {
        this.pathToFile = pathToFile;
    }


    @Override
    public List<String> get() {
        try {
        System.out.println("Reading emails from: " + pathToFile);

        CSVReader reader = new CSVReader(new FileReader(pathToFile));
        return reader.readAll().stream().map(line -> line[0].trim()).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

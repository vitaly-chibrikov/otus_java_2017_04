package ru.otus.l81.io;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by tully.
 */
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student student = new Student(32, "tully", "java");
        System.out.println(student);
        String file = "student.out";

        ioExample(student, file);
        apacheExample(student, file);

        String textFile = "hello.txt";
        String text = "Hello Java";
        writeTextFile(textFile, text);

        List<String> lines = Files.readAllLines(Paths.get(textFile));
        System.out.println(lines);
    }

    private static void writeTextFile(String textFile, String text) throws IOException {
        byte[] textBytes = text.getBytes(Charset.forName("UTF-8"));
        try (FileOutputStream stream = new FileOutputStream(textFile)) {
            stream.write(textBytes);
        }
    }

    private static void apacheExample(Student student, String file) throws IOException, ClassNotFoundException {
        FileUtils.writeByteArrayToFile(new File(file), JavaIO.serialize(student));
        Student readStudent = (Student) JavaIO.deserialize(FileUtils.readFileToByteArray(new File(file)));
        System.out.println(readStudent);
    }

    private static void ioExample(Student student, String file) {
        JavaIO.writeObject(file, student);
        Student readStudent = (Student) JavaIO.readObject(file);
        System.out.println(readStudent);
    }


}

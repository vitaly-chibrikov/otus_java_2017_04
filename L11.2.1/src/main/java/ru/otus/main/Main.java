package ru.otus.main;

import ru.otus.base.DBService;
import ru.otus.base.dataSets.PhoneDataSet;
import ru.otus.base.dataSets.UserDataSet;
import ru.otus.dbService.DBServiceImpl;

import java.lang.management.ManagementFactory;
import java.util.List;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        DBService dbService = new DBServiceImpl();

        String status = dbService.getLocalStatus();
        System.out.println("Status: " + status);

        dbService.save(new UserDataSet("tully", new PhoneDataSet("12345")));
        dbService.save(new UserDataSet("sully", new PhoneDataSet("67890")));

        System.out.println(dbService.read(1));
        System.out.println(dbService.read(1));

        System.out.println(dbService.readByName("sully"));

        List<UserDataSet> dataSets = dbService.readAll();
        for (UserDataSet userDataSet : dataSets) {
            System.out.println(userDataSet);
        }

        hold();

        dbService.shutdown();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private static void hold() throws InterruptedException {
        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());
        while(true){
            Thread.sleep(1000);
        }
    }
}

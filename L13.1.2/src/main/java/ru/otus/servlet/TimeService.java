package ru.otus.servlet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tully.
 */
public class TimeService {
    private static final String DEFAULT_PATTERN = "HH.mm.ss";
    private static TimeService timeService;

    private final String pattern;

    private TimeService(String pattern) {
        this.pattern = pattern;
    }

    public static TimeService instance() {
        if(timeService==null){
            timeService = new TimeService(DEFAULT_PATTERN);
        }
        return timeService;
    }

    public String getTime() {
        Date date = new Date();
        date.getTime();
        DateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }
}

package ru.otus.l62.b1.chain;

/**
 * Created by tully.
 */
public class DBLogger extends Logger {
    public DBLogger() {
        super(30);
    }

    @Override
    protected void writeMsg(String msg) {
        //write to the DB
        System.err.println(this.getClass().getSimpleName() + ": " + msg);
    }
}

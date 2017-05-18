package ru.otus.l62.b1.chain;

/**
 * Created by tully.
 * <p>
 * This class is a Handler class in the Chain-of-responsibility pattern.
 */
public abstract class Logger {
    private Logger next;
    private int logLevel;

    public Logger(int logLevel) {
        this.logLevel = logLevel;
    }

    /**
     * Each logger tries to handle a message and calls next if it is not possible.
     *
     * @param msg   message to log
     * @param level log level
     */

    public void handle(String msg, int level) {
        if (level > this.getLogLevel()) {
            writeMsg(msg);
        }
        if (getNext() != null) {
            getNext().handle(msg, level);
        }
    }

    public void setNext(Logger next) {
        this.next = next;
    }

    protected abstract void writeMsg(String msg);

    private Logger getNext() {
        return next;
    }

    private int getLogLevel() {
        return logLevel;
    }

}

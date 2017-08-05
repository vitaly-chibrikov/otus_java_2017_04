package ru.otus.l161.server;

/**
 * Created by tully.
 */
public interface NonBlockMirrorServerMBean {
    boolean getRunning();

    void setRunning(boolean running);
}

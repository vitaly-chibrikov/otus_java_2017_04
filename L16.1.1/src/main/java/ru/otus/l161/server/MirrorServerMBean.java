package ru.otus.l161.server;

/**
 * Created by tully.
 */
public interface MirrorServerMBean {
    boolean getRunning();

    void setRunning(boolean running);
}

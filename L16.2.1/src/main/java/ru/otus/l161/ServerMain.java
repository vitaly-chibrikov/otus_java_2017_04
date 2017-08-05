package ru.otus.l161;

import ru.otus.l161.runner.ProcessRunnerImpl;
import ru.otus.l161.server.BlockingServer;
import ru.otus.l161.server.LogServer;
import ru.otus.l161.server.NonBlockMirrorServer;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by tully.
 */
public class ServerMain {
    private static final Logger logger = Logger.getLogger(ServerMain.class.getName());

    private static final String CLIENT_START_COMMAND = "java -jar ../L16.2.2/target/client.jar";
    private static final int CLIENT_START_DELAY_SEC = 1;
    private static final int CLIENTS_COUNT = 15;

    public static void main(String[] args) throws Exception {
        new ServerMain().start();
    }

    private void start() throws Exception {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        startClients(CLIENTS_COUNT, executorService);

        //startLogServer();
        startMirrorServer();
        //startBlockingServer();

        executorService.shutdown();
    }

    private void startLogServer() throws Exception {
        new LogServer().start();
    }

    private void startBlockingServer() throws Exception {
        new BlockingServer().start();
    }

    private void startMirrorServer() throws Exception {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("ru.otus:type=Server");
        NonBlockMirrorServer server = new NonBlockMirrorServer();
        mbs.registerMBean(server, name);

        server.start();
    }

    private void startClients(int count, ScheduledExecutorService executorService) {
        for (int i = 0; i < count; i++) {
            executorService.schedule(() -> {
                try {
                    new ProcessRunnerImpl().start(CLIENT_START_COMMAND);
                } catch (IOException e) {
                    logger.log(Level.SEVERE, e.getMessage());
                }
            }, CLIENT_START_DELAY_SEC + i, TimeUnit.SECONDS);
        }
    }

}

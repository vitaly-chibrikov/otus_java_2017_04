package ru.otus.l161.server;

import ru.otus.l161.app.Msg;
import ru.otus.l161.app.MsgChannel;
import ru.otus.l161.channel.SocketClientChannel;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by tully.
 */
public class BlockingServer {
    private static final Logger logger = Logger.getLogger(NonBlockMirrorServer.class.getName());

    private static final int THREADS_NUMBER = 1;
    private static final int PORT = 5050;
    private static final int MIRROR_DELAY = 100;

    private final ExecutorService executor;
    private final List<MsgChannel> channels;

    public BlockingServer() {
        executor = Executors.newFixedThreadPool(THREADS_NUMBER);
        channels = new CopyOnWriteArrayList<>();
    }

    public void start() throws Exception {
        executor.submit(this::mirror);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            logger.info("Server started on port: " + serverSocket.getLocalPort());
            while (!executor.isShutdown()) {
                Socket client = serverSocket.accept(); //blocks
                SocketClientChannel channel = new SocketClientChannel(client);
                channel.init();
                channel.addShutdownRegistration(() -> channels.remove(channel));
                channels.add(channel);
            }
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void mirror() {
        while (true) {
            for (MsgChannel channel : channels) {
                Msg msg = channel.pool(); //get
                if (msg != null) {
                    System.out.println("Mirroring the message: " + msg.toString());
                    channel.send(msg);
                }
            }
            try {
                Thread.sleep(MIRROR_DELAY);
            } catch (InterruptedException e) {
                logger.log(Level.SEVERE, e.toString());
            }
        }
    }
}

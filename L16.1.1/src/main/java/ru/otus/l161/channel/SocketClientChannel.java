package ru.otus.l161.channel;

import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.otus.l161.app.Msg;
import ru.otus.l161.app.MsgChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by tully.
 */
public class SocketClientChannel implements MsgChannel {
    private static final Logger logger = Logger.getLogger(SocketClientChannel.class.getName());
    private static final int WORKERS_COUNT = 2;

    private final BlockingQueue<Msg> output = new LinkedBlockingQueue<>();
    private final BlockingQueue<Msg> input = new LinkedBlockingQueue<>();

    private final ExecutorService executor;

    private final Socket client;

    public SocketClientChannel(Socket client) {
        this.client = client;
        this.executor = Executors.newFixedThreadPool(WORKERS_COUNT);
    }

    public void init() {
        executor.execute(this::sendMessage);
        executor.execute(this::receiveMessage);
    }

    private void receiveMessage() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()))) {
            String inputLine;
            StringBuilder stringBuilder = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                //System.out.println("Message received: " + inputLine);
                stringBuilder.append(inputLine);
                if (inputLine.isEmpty() && !stringBuilder.toString().isEmpty()) {
                    String json = stringBuilder.toString();
                    Msg msg = getMsgFromJSON(json);
                    input.add(msg);
                    stringBuilder = new StringBuilder();
                }
            }
        } catch (IOException | ParseException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Msg getMsgFromJSON(String json) throws ParseException, ClassNotFoundException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(json);
        String className = (String) jsonObject.get(Msg.CLASS_NAME_VARIABLE);
        Class<?> msgClass = Class.forName(className);
        return (Msg) new Gson().fromJson(json, msgClass);
    }

    private void sendMessage() {
        try (PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {
            while (client.isConnected()) {
                Msg msg = output.take();
                String json = new Gson().toJson(msg);
                out.println(json);
                out.println("\n");
            }
        } catch (InterruptedException | IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    @Override
    public void send(Msg msg) {
        output.add(msg);
    }

    @Override
    public Msg pool() {
        return input.poll();
    }

    @Override
    public Msg take() throws InterruptedException {
        return input.take();
    }

    @Override
    public void close() throws IOException {
        executor.shutdown();
    }
}

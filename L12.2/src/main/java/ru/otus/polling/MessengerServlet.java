package ru.otus.polling;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by tully.
 */
public class MessengerServlet extends HttpServlet {

    private final BlockingQueue<Message> messages = new LinkedBlockingQueue<>();

    public MessengerServlet() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                messages.add(new Message("Time: " + getTime()));
            }
        }, 5000, 5000);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        try {
            Message message = messages.take(); //this line blocks till messages.size() != 0.

            response.getWriter().println(new Gson().toJson(message));

            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String getTime() {
        Date date = new Date();
        date.getTime();
        DateFormat formatter = new SimpleDateFormat("HH.mm.ss");
        return formatter.format(date);
    }
}

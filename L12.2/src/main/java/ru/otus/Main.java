package ru.otus;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.otus.ajax.AjaxTimerServlet;
import ru.otus.polling.MessengerServlet;
import ru.otus.websocket.WebSocketChatServlet;
import ru.otus.timer.TimerServlet;

/**
 * @author v.chibrikov
 */
public class Main {
    private final static int PORT = 8090;
    private final static String PUBLIC_HTML = "public_html";

    public static void main(String[] args) throws Exception {

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(PUBLIC_HTML);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(AjaxTimerServlet.class, "/server-time");
        context.addServlet(TimerServlet.class, "/timer");
        context.addServlet(WebSocketChatServlet.class, "/chat");

        context.addServlet(new ServletHolder(new MessengerServlet()), "/messenger");

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));

        server.start();
        server.join();
    }
}

package ru.otus.servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.*;

/**
 * @author v.chibrikov
 */
public class Main {
    private static Logger logger = Logger.getLogger(Main.class.getName());

    static {
        try {
            Handler handler = new FileHandler("%h/tmp/my-logger.log");
            //handler.setFormatter(new SimpleFormatter());
            //handler.setFormatter(new XMLFormatter());
            handler.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    return record.getLoggerName() + " "
                            + record.getMillis() + ": "
                            + record.getLevel() + " "
                            + record.getMessage() + "\n";
                }
            });
            logger.addHandler(handler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }


    private final static int PORT = 8090;
    private final static String PUBLIC_HTML = "/static";

    public static void main(String[] args) throws Exception {
        logger.info("Starting server");

        logger.setLevel(Level.WARNING);
        logger.info("Not visible");
        logger.log(Level.SEVERE, "Server error (fake)");

        ResourceHandler resourceHandler = new ResourceHandler();

        URL url = Main.class.getResource(PUBLIC_HTML + "/index.html");

        System.out.println(url);

        Resource resource = Resource.newClassPathResource(PUBLIC_HTML);
        resourceHandler.setBaseResource(resource);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new TimerServlet()), "/timer");

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));

        server.start();

        logger.setLevel(Level.FINEST);
        logger.info("Server started");

        server.join();
    }
}

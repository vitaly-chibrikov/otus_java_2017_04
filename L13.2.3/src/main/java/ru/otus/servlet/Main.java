package ru.otus.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;

import java.net.URL;


/**
 * %d{yyyy-MM-dd HH:mm:ss} = Date and time format, refer to SimpleDateFormat JavaDoc.
 * %-5p = The logging priority, like DEBUG or ERROR. The -5 is optional, for the pretty print format.
 * %c{1} = The logging name we set via getLogger(), refer to log4j PatternLayout guide.
 * %L = The line number from where the logging request.
 * %m%n = The message to log and line break.
 * <p>
 * Log message examples :
 * <p>
 * 2017-07-02 20:52:39 DEBUG className:200 - This is debug message
 * 2017-07-02 20:52:39 DEBUG className:201 - This is debug message2
 */
public class Main {
    private static Logger logger = LogManager.getLogger(Main.class);

    private final static int PORT = 8090;
    private final static String PUBLIC_HTML = "/static";

    public static void main(String[] args) throws Exception {
        if(logger.isDebugEnabled()){
            logger.debug("This is debug message");
        }

        if(logger.isInfoEnabled()){
            logger.info("This is info message");
        }

        logger.warn("This is warn message");
        logger.error("This is error message");
        logger.fatal("This is fatal message");

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

        logger.info("Server started");

        server.join();
    }
}

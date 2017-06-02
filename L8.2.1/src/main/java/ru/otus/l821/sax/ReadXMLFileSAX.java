package ru.otus.l821.sax;


import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.function.Supplier;

public class ReadXMLFileSAX {
    public static Object readXML(String xmlFile, Supplier<BuilderHandler> handlerFactory) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            BuilderHandler handler = handlerFactory.get();
            saxParser.parse(xmlFile, handler);

            return handler.build();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

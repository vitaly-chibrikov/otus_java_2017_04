package ru.otus.l821.main;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.otus.l821.sax.ObjectBuilderSaxHandler;
import ru.otus.l821.sax.ReadXMLFileSAX;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {
        saxExample();
        //domExample();
    }

    private static void saxExample() {
        //Person object = (Person) ReadXMLFileSAX.readXML("person.xml", LogSaxHandler::new);
        Person object = (Person) ReadXMLFileSAX.readXML("person.xml", ObjectBuilderSaxHandler::new);
        if (object != null)
            System.out.println(object.toString());
    }

    private static void domExample() throws ParserConfigurationException, IOException, SAXException {
        File file = new File("person.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        Element element = doc.getDocumentElement();
            NodeList nodes = element.getChildNodes();

            for (int i = 0; i < nodes.getLength(); i++) {
                System.out.println("" + nodes.item(i).getTextContent());
            }

    }
}

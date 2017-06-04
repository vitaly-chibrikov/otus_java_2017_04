package ru.otus.l821.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import ru.otus.l821.reflection.ReflectionHelper;

public class ObjectBuilderSaxHandler extends BuilderHandler {
	private static final String CLASS_ATTRIBUTE_NAME = "class";
	private String element;
	private Object object;
	
	public void startDocument() throws SAXException {
		System.out.println("Start document");
	}
 
	public void endDocument() throws SAXException {
		System.out.println("End document ");
	}
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if(!qName.equals(CLASS_ATTRIBUTE_NAME)){
			element = qName;
		}
		else{
			String className = attributes.getValue(0);
			System.out.println("Class name: " + className);
			object = ReflectionHelper.createInstance(className);
		}	
	}
 
	public void endElement(String uri, String localName, String qName) throws SAXException {
		element = null;
	}
 
	public void characters(char ch[], int start, int length) throws SAXException {
		if(element != null){
			String value = new String(ch, start, length);
			System.out.println(element + " = " + value);
			ReflectionHelper.setFieldValue(object, element, value);
		}
	}

	@Override
	public Object build() {
		return object;
	}
}

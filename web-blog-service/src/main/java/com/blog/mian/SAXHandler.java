package com.blog.mian;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler{

	private String currentElement;
	private String currentValue;
	private String attrName;
	private String attrValue;
	
	@Override
	public void startElement(String uri, String localName,
            String qName, Attributes attributes) {
		currentElement = qName;
		for (int i = 0; i < attributes.getLength(); i++) {
			attrName = attributes.getQName(i);
			attrValue = attributes.getValue(i);
			System.out.println("属性:" + attrName + "=" + attrValue);
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		currentValue = new String(ch,start,length);
		System.out.println("characters开始" + currentValue);
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) {
		if (currentElement.equals(qName)) {
			System.out.println(currentElement + "=" + currentValue);
		}
	}
}

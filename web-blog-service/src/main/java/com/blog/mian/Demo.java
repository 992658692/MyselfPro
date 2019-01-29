package com.blog.mian;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Demo {
	public static void main(String[] args) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new File("dubbo.xml"));
		Element eoc = doc.getDocumentElement();
		eoc.getAttribute("xmlns");
		parseElement(eoc);
	}
	
	private static void parseElement(Element element) {
		String name = element.getNodeName();
		System.out.println("<" + name);
		NamedNodeMap map = element.getAttributes();
		if (null != map) {
			for (int i = 0; i < map.getLength(); i++) {
				Attr attr = (Attr)map.item(i);
				String attrName = attr.getName();
				String attrValue = attr.getValue();
				System.out.println("" + attrName + "=\""+attrValue+"\"");
			}
		}
		System.out.println(">");
		NodeList child = element.getChildNodes();
		for (int i = 0; i < child.getLength(); i++) {
			Node node = child.item(i);
			Short noteType = node.getNodeType();
			if (noteType == Node.ELEMENT_NODE) {
				parseElement((Element)node);                     
			} else {
				if (noteType == Node.TEXT_NODE) {
					System.out.println(node.getTextContent());
				} else {
					if (noteType == Node.COMMENT_NODE) {
						System.out.println("<!--"+node.getTextContent() + "-->");
					}
				}
			}
		}
		
		System.out.println("</" +name+ ">");
	}
}

package com.blog.mian;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SaxDemo {
	
	public static void main(String[] args) throws Exception {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		SAXHandler handler = new SAXHandler();
		sp.parse(new File("dubbo.xml"), handler);
	}
}

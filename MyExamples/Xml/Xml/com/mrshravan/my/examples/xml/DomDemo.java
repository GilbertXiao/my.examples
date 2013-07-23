package com.mrshravan.my.examples.xml;
import org.w3c.dom.*;			 // DOM API in JAVA
import org.xml.sax.*;			 // SAX API in JAVA
import javax.xml.parsers.*;// JAXP API

class DOMDemo 
{
	public static void main(String[] args) throws Exception
	{
		DocumentBuilderFactory dbFactory = 
				DocumentBuilderFactory.newInstance();

		DocumentBuilder docBuilder = 
				dbFactory.newDocumentBuilder();

		java.io.File xmlFile = 
			new java.io.File("c:/ejb/xml/phone.xml");

		Document doc = docBuilder.parse(xmlFile);
		Element root = doc.getDocumentElement();
		
		//NodeList childList = root.getChildNodes();

		NodeList childList = 
			root.getElementsByTagName("contact");

		System.out.println( childList.getLength() );
	}
}
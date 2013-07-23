package com.mrshravan.my.examples.xml;
import java.io.*;				// DOM API in JAVA
import org.w3c.dom.*;				// DOM API in JAVA
import org.xml.sax.*;				// SAX API in JAVA
import javax.xml.parsers.*;	// JAXP 

class CreateDemo
{
	public static void main(String[] args) throws Exception
	{
		DocumentBuilderFactory dbFactory = 
			DocumentBuilderFactory.newInstance();

		DocumentBuilder docBuilder = 
			dbFactory.newDocumentBuilder();

		Document doc = docBuilder.newDocument();
			
		Element phoneBookEle = 
			doc.createElement("phone-book");

		Element contactEle = 
			doc.createElement("contact");

		Element nameEle = 
			doc.createElement("name");

		Element phoneNumberEle = 
			doc.createElement("phone-number");

		Text nameText = 
			doc.createTextNode("Deccansoft");

		Text phoneText = 
			doc.createTextNode("27841517");

		nameEle.appendChild( nameText );
		phoneNumberEle.appendChild( phoneText );

		contactEle.appendChild( nameEle );
		contactEle.appendChild( phoneNumberEle );
		contactEle.setAttribute("category","office");

		phoneBookEle.appendChild(contactEle);
		
FileWriter fw = new FileWriter("newphone.xml");
		
com.sun.xml.tree.ElementNode sunEle = 
	(com.sun.xml.tree.ElementNode)phoneBookEle;

		sunEle.write( fw );

		fw.flush();
		fw.close();

	}
}

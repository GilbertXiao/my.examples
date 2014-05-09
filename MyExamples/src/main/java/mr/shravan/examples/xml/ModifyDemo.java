package mr.shravan.examples.xml;
import java.io.*;				
import org.w3c.dom.*;				// DOM API in JAVA
import org.xml.sax.*;				// SAX API in JAVA
import javax.xml.parsers.*;	// JAXP 

class ModifyDemo 
{
	public static void main(String[] args) throws Exception
	{
		DocumentBuilderFactory dbFactory = 
			DocumentBuilderFactory.newInstance();

		DocumentBuilder docBuilder = 
			dbFactory.newDocumentBuilder();

		java.io.File source = 
			new java.io.File("newphone.xml");

		Document doc = docBuilder.parse(source);
		Element root = doc.getDocumentElement();

Element firstContact = (Element)
(root.getElementsByTagName("contact").item(0));

Node phoneNumberNode = 

firstContact.getElementsByTagName
										("phone-number").item(0);

System.out.println
(phoneNumberNode.getFirstChild().getNodeValue());

phoneNumberNode.getFirstChild().setNodeValue("12345678");

System.out.println
(phoneNumberNode.getFirstChild().getNodeValue());

FileWriter dest = new FileWriter(source);

//com.sun.xml.tree.ElementNode sunEle = 
//	(com.sun.xml.tree.ElementNode)root;
//
//sunEle.write(dest);
//dest.flush();
//dest.close();

	}
}
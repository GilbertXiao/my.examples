package mr.shravan.examples.xml;
import org.w3c.dom.*;				// DOM API in JAVA
import org.xml.sax.*;				// SAX API in JAVA
import javax.xml.parsers.*;	// JAXP 

class JAXPDemo 
{
	public static void main(String[] args) throws Exception
	{

/*
System.setProperty
("javax.xml.parsers.DocumentBuilderFactory",
 "com.sun.xml.parser.DocumentBuilderFactoryImpl");

System.setProperty
("javax.xml.parsers.DocumentBuilderFactory",
 "weblogic.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
*/
		
		DocumentBuilderFactory dbFactory = 
			DocumentBuilderFactory.newInstance();

		DocumentBuilder docBuilder = 
			dbFactory.newDocumentBuilder();

		Document doc = docBuilder.parse
						("file:/c:/ejb/xml/student.xml");

		Element root = doc.getDocumentElement();

		NodeList list = 
			root.getElementsByTagName("student");

		int n = list.getLength();

		for(int i=0; i<n; i++)
		{
			Element studentEle=(Element)list.item(i);
	
			String roll = 
				studentEle.getAttribute("roll-no");

		if( roll.equals(args[0]) )
		{

Node resultNode = 
studentEle.getElementsByTagName("result").item(0);
	
System.out.println
( resultNode.getFirstChild().getNodeValue() );

			return;
		}
	}

		System.out.println("No such Student");
	}
}
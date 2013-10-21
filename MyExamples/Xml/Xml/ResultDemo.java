
import org.w3c.dom.*;				// DOM API in JAVA
import org.xml.sax.*;				// SAX API in JAVA
import javax.xml.parsers.*;	// JAXP 

class ResultDemo 
{
	public static void main(String[] args) throws Exception
	{
		DocumentBuilderFactory docFactory = 
			DocumentBuilderFactory.newInstance();

		DocumentBuilder docBuilder = 
			docFactory.newDocumentBuilder();

	java.io.File xmlSource = 
		new java.io.File("c:/ejb/xml/student.xml");
	
	Document doc = docBuilder.parse(xmlSource);

	Element root = doc.getDocumentElement();

	NodeList list = 
		root.getElementsByTagName("student");

		int n = list.getLength();

		for(int i=0; i<n; i++)
		{
			Node studentNode = list.item(i);
			Element studEle = (Element)studentNode;
			String roll = 
				studEle.getAttribute("roll-no");

			if(roll.equals(args[0]))
			{
				Node resultNode = 
					studEle.getElementsByTagName("result").item(0);
		
				System.out.println
					(resultNode.getFirstChild().getNodeValue());

				return;
			}
		}

		System.out.println("No such Student");
	}
}

import java.io.*;
import org.xml.sax.*;
import javax.xml.parsers.*;

public class CheapestProduct extends HandlerBase
{
	String name;
	double price = Double.MAX_VALUE;

	public void startDocument()
	{
		System.out.println("Document Begins");
	}

	public void endDocument()
	{
		System.out.println("Document Ends");
	}

	public void startElement
	(String eleName,AttributeList attrList)
	{
		System.out.println("Start " + eleName);
		
		if(eleName.equals("vendor"))
		{
			double vendorPrice = Double.parseDouble
									(attrList.getValue("price"));
	
			if(vendorPrice < price)
			{
				price = vendorPrice;
				name = attrList.getValue("name");
			}
		}
	}

	public void endElement(String eleName)
	{
		System.out.println("End   " + eleName);
	}

	public static void main(String args[])
	throws Exception
	{
		CheapestProduct cp = 
			new CheapestProduct();

		SAXParserFactory saxFactory = 
			SAXParserFactory.newInstance();

		SAXParser parser = 
			saxFactory.newSAXParser();

		parser.parse( args[0], cp );

		System.out.println("Name : " + cp.name);
		System.out.println("Price: " + cp.price);
	}
}
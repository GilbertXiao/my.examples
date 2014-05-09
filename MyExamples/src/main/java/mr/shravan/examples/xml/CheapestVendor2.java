package mr.shravan.examples.xml;
import org.xml.sax.*;
import javax.xml.parsers.*;

public class CheapestVendor2 extends HandlerBase
{
	public void startDocument()
	{
		System.out.println("Document Parsing Begins");
	}

	public void endDocument()
	{
		System.out.println("Document Parsing Ends");
	}

	public void startElement(String name,AttributeList attrs)
	{
		System.out.println("<" + name + ">");
	}
	public void endElement(String name)
	{
		System.out.println("</" + name + ">");
	}

	public static void main(String args[]) 
		throws Exception
 	{
 		if(args.length < 1)
 	 	{
     		System.out.println("XML File URL Missing");
     		return;
 		}

		CheapestVendor handler = new CheapestVendor();

		SAXParserFactory factory = 
							SAXParserFactory.newInstance();

		SAXParser parser = factory.newSAXParser();

		parser.parse(args[0], handler);
	 }
}
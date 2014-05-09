package mr.shravan.examples.xml;

import org.xml.sax.*;
import javax.xml.parsers.*;

public class CheapestVendor extends HandlerBase
{
	int n=0;
	private double minPrice = Double.MAX_VALUE;
	private String vendorName;
	public String getVendorName()
	{
		return vendorName;
	}
	public double getPrice()
	{
		return minPrice;
	}
	public void startDocument()
	{
		System.out.println("Document Parsing Begins\n");
	}
	public void endDocument()
	{
		System.out.println("\nDocument Parsing Ends");
	}

	public void startElement(String name,AttributeList attrs)
	{
		n++;
		spaces(n);
		System.out.print("<" + name);
		spaces(1);

		for(int i=0; i<attrs.getLength(); i++)
		{
			System.out.print(attrs.getName(i) + "=" + "\"" + attrs.getValue(i) + "\"");

			if(i < attrs.getLength()-1 )
			spaces(1);
		}

		System.out.println(">");

		if(name.equals("vendor"))
		{
			String str = attrs.getValue("price");
			double v = Double.parseDouble(str);

			if(v < minPrice)
			{
				minPrice = v;
				vendorName = attrs.getValue("name");
			}
		}
	}
	public void endElement(String name)
	{
		spaces(n);
		n--;
		System.out.println("</" + name + ">");
	}

	static void spaces(int a)
	{
		for(int i=0; i<a; i++)
				System.out.print("  ");
	}

	public static void main(String args[]) throws Exception
 	{
 		if(args.length < 1)
 	 	{
     		System.out.println("XML File URL Missing");
     		return;
 		}

		CheapestVendor h = new CheapestVendor();

		SAXParserFactory factory = 
			SAXParserFactory.newInstance();

		SAXParser parser = factory.newSAXParser();
		parser.parse(args[0], h);

 		System.out.println
			("\nThe cheapest offer is from ");

		System.out.println
			("Vendor : " + h.getVendorName());
  	
		System.out.println
			("Price  : " + h.getPrice());

	 }

}
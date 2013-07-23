package com.mrshravan.my.examples.xml;

import org.xml.sax.*;
import javax.xml.parsers.*;

public class BuildTree extends HandlerBase
{
	int n=0;

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
		n++;
		spaces(n);
		System.out.print("<" + name);
		spaces(1);

		for(int i=0; i<attrs.getLength(); i++)
		{
			System.out.print
				( attrs.getName(i) + "=" + "\"" + 
				  attrs.getValue(i) + "\"" );

			spaces(1);
		}
		System.out.println(">");
	}

	public void endElement(String name)
	{
		spaces(n);
		n--;
		System.out.println("</" + name + ">");
	}

	public static void main(String args[]) throws Exception
 	{
 		if(args.length < 1)
 	 	{
   		System.out.println("XML File URL Missing");
   		return;
		}

		BuildTree handler = new BuildTree();

		SAXParserFactory factory = 
							SAXParserFactory.newInstance();

		SAXParser parser = factory.newSAXParser();
		parser.parse(args[0], handler);

	 }

	static void spaces(int a)
	{
		for(int i=0; i<a; i++)
				System.out.print("  ");
	}

}
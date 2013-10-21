import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import com.sun.xml.tree.*;
import javax.xml.parsers.*;

public class SunDisplay
{
	org.w3c.dom.Document doc;
	
	public static void main(String args[])
	{
		SunDisplay sd = new SunDisplay(args[0]);
		sd.display();
	}

	public SunDisplay(String s)
	{
		try
		{
			DocumentBuilderFactory docFactory = 
				DocumentBuilderFactory.newInstance();

			DocumentBuilder docBuilder = 
					docFactory.newDocumentBuilder();

			doc = docBuilder.parse(s);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void display()
	{
		displayRecursive(doc.getDocumentElement(),0);
	}

public void displayRecursive(Node node,int space)
	{
		int childCount;
		NodeList children;
		
		if(node.getNodeType() == Node.ELEMENT_NODE)
		{
			spacer(space);
			System.out.println(node.getNodeName());

			children = node.getChildNodes();
			childCount = children.getLength();
			for(int i=0;i<childCount;i++)
			{
				displayRecursive(children.item(i),space+3);
			}
		}
	}

	void spacer(int n)
	{
		for(int i=0;i<n;i++)
			System.out.print(" ");		
	}
}
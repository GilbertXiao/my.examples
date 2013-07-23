
import java.awt.*;

class FontListDemo
{
	public static void main(String[] args) 
	{

GraphicsEnvironment ge = 
	GraphicsEnvironment.getLocalGraphicsEnvironment();
		
String list[] = 
	ge.getAvailableFontFamilyNames();
	
		for(int i=0; i<list.length;i++)
		{
			System.out.println(list[i]);
		}
	}
}

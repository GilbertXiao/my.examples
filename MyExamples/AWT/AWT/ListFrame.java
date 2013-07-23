import java.awt.*;
import java.awt.event.*;

class ListFrame extends Frame 
implements ItemListener
{
	List myList;
	String items[] = {"C","C++","Java","Delphi",
										"Fortran","Cobol","Algol"};

	ListFrame()
	{
		setSize(300,300);
		setLayout(new FlowLayout());

		myList = new List(4,true);
		myList.addItemListener(this);
		
		for(int i=0; i<items.length; i++)
			myList.add(items[i]);

		this.add(myList);	
		setVisible(true);
	}

	public void itemStateChanged(ItemEvent evt)
	{
		repaint();
	}

	public void paint(Graphics g)
	{
		int x = 120;
		int y = 120;

String selItems[] = myList.getSelectedItems();

		for(int i=0; i<selItems.length; i++)
		{
			g.drawString(selItems[i],x,y);
			y+=20;
		}
	}

	public static void main(String args[])
	{
		new ListFrame();
	}
}
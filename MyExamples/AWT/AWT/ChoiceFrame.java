import java.awt.*;
import java.awt.event.*;

class ChoiceFrame extends Frame 
implements ItemListener
{
	Choice c;
String items[] = {"red","green","blue"};
Color  colors[]= {Color.red,Color.green,Color.blue};

	ChoiceFrame()
	{
		setSize(300,300);
		setFont(new Font("Arial",Font.BOLD,20));
		setLayout(new FlowLayout());

		c = new Choice();
		c.addItemListener(this);

		for(int i=0; i<items.length; i++)
		{
			c.addItem( items[i] );
		}

		this.add(c);
		this.setVisible(true);
	}
	
	public void itemStateChanged(ItemEvent evt)
	{
		int index = c.getSelectedIndex();

		this.setBackground( colors[index] );
	}
	
	public static void main(String[] args) 
	{
		new ChoiceFrame();
	}
}

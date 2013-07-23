import java.awt.*;
import java.awt.event.*;

class CheckboxFrame extends Frame 
implements ItemListener
{
	Checkbox c1,c2;

	CheckboxFrame()
	{
		setSize(300,300);
		setFont(new Font("Arial",Font.BOLD,20));
		setLayout(new FlowLayout());

		c1 = new Checkbox("C++");
		c2 = new Checkbox("Java", true);

		c1.addItemListener(this);
		c2.addItemListener(this);

		this.add(c1);
		this.add(c2);

		this.setVisible(true);
	}
	
	public void itemStateChanged(ItemEvent evt)
	{
		repaint();
	}
	public void paint(Graphics g)
	{	
		int x = 80;
		int y = 80;
		
		g.drawString
		(c1.getLabel() + ":" + c1.getState(),x,y);

		y += 20;

		g.drawString
		(c2.getLabel() + ":" + c2.getState(),x,y);
	}
	
	public static void main(String[] args) 
	{
		new CheckboxFrame();
	}
}

import java.awt.*;
import java.awt.event.*;

class ChoiceFrame extends Frame 
implements ItemListener
{
	ChoiceFrame()
	{
		setSize(300,300);
		setFont(new Font("Arial",Font.BOLD,20));
		setLayout(new FlowLayout());

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
		(cg.getSelectedCheckbox().getLabel(),x,y);

	}
	
	public static void main(String[] args) 
	{
		new ChoiceFrame();
	}
}

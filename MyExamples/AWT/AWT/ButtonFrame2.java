import java.awt.*;
import java.awt.event.*;

class ButtonFrame2 extends Frame 
implements ActionListener
{
	String str = "";
	Button b1,b2;
	
	ButtonFrame2()
	{
		this.setSize(400,300);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension desktopSize = toolkit.getScreenSize();
		Dimension mySize = this.getSize();
		
		this.setLocation(	(desktopSize.width -mySize.width) /2,(desktopSize.height-mySize.height)/2);

		FlowLayout fl = new FlowLayout();
		this.setLayout( fl );

		b1 = new Button("Heads");	
		b2 = new Button("Tails");	

		b1.addActionListener(this);
		b2.addActionListener(this);

		this.add(b1);
		this.add(b2);

		this.setVisible(true);
	}

	public void paint(Graphics g)
	{
		g.drawString(str, 200, 150);
	}

	public void actionPerformed(ActionEvent evt)
	{
		Object src = evt.getSource();
		
		if(src instanceof Button)
		{
			Button temp = (Button)src;
			str = temp.getLabel();
			repaint();
		}
	}
}


class FrameDemo 
{
	public static void main(String[] args) 
	{
		new ButtonFrame2();
	}
}

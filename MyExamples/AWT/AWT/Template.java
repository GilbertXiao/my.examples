
import java.awt.*;
import java.awt.event.*;

class Frame extends Frame
{

	(String title)
	{		
		setTitle(title);
		setSize(300,300);

		FlowLayout fl = new FlowLayout();
		setLayout( fl );
		
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension desktop = t.getScreenSize();
		Dimension d = this.getSize();
		this.setLocation( (desktop.width-d.width)/2,(desktop.height-d.height)/2);
	

		
		this.setVisible(true);
	}
}

class FrameDemo 
{
	public static void main(String[] args) 
	{
		Frame myFrame = new ("AWT Frame");
	}
}

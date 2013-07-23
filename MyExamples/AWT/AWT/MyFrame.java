
import java.awt.*;
import java.awt.event.*;

class MyFrame extends Frame
{
	private int x;
	MyFrame()
	{
		addWindowListener
			(this.new MyWindowListener());

		setSize(400,400);
		setVisible(true);
	}

	class MyWindowListener extends WindowAdapter
	{
		public void windowClosing(WindowEvent evt)
		{
			x = 1;
			evt.getWindow().dispose();
			System.exit(0);
		}
	}


	public static void main(String[] args) 
	{
		new MyFrame();
	}
}

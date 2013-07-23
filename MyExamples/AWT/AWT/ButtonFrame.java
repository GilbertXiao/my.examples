import java.awt.*;
import java.awt.event.*;

class MyListener implements ActionListener
{
	public void actionPerformed(ActionEvent evt)
	{
		System.out.println("Event Fired");
	}
}

class ButtonFrame extends Frame
{
	Button b;
	
	ButtonFrame()
	{
		this.setSize(400,300);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension desktopSize = toolkit.getScreenSize();
		Dimension mySize = this.getSize();
		
		this.setLocation(	(desktopSize.width -mySize.width) /2,(desktopSize.height-mySize.height)/2);

		FlowLayout fl = new FlowLayout();
		this.setLayout( fl );

		b = new Button("Java");	

		MyListener listener = new MyListener(); 
		b.addActionListener( listener );

		this.add(b);
	
		this.setVisible(true);
	}
}


class FrameDemo 
{
	public static void main(String[] args) 
	{
		new ButtonFrame();	
	}
}

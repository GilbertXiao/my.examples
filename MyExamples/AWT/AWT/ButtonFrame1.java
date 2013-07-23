import java.awt.*;
import java.awt.event.*;

class ButtonFrame2 extends Frame 
implements ActionListener
{
	String str = "";
	Button b1,b2;
	
	ButtonFrame2(String title)
	{
		super(title);
		setSize(300,300);

		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension desktopSize = t.getScreenSize();

		Dimension mySize = this.getSize();
		
		this.setLocation
		(
			(desktopSize.width-mySize.width)/2 ,
			(desktopSize.height-mySize.height)/2
		);
		
		FlowLayout layout = new FlowLayout();
		this.setLayout( layout );

		b1 = new Button("Heads");		
		b2 = new Button("Tails");

		b1.addActionListener( this );
		b2.addActionListener( this );

		this.add( b1 );
		this.add( b2 );

		setVisible(true);
	}

	public void actionPerformed(ActionEvent evt)
	{
		Object src = evt.getSource();
		str = evt.getActionCommand();
		this.repaint();
	}

	public void paint(Graphics g)
	{
		g.drawString(str,80,50);
	}
}

class FrameDemo
{
	public static void main(String[] args) 
	{
		Frame f = new ButtonFrame2("First Frame");
	}
}

import java.awt.*;
import java.awt.event.*;

class ScrollbarFrame extends Frame 
implements AdjustmentListener,WindowListener
{
	Label l;
	Scrollbar s;

	ScrollbarFrame()
	{
		setSize(300,300);
		setLayout(new FlowLayout());
		setFont(new Font("Arial",Font.PLAIN,20));

		this.addWindowListener(this);

		l = new Label("Value");

		s = new Scrollbar(Scrollbar.HORIZONTAL,15,10,0,210);
		s.setBackground(Color.red);
		
		s.addAdjustmentListener(this);
		add(s);
		add(l);			
		setVisible(true);
	}

	public void adjustmentValueChanged(AdjustmentEvent evt)
	{
		l.setText( String.valueOf(s.getValue()) );
	}

public void windowOpened	   ( WindowEvent evt)
{}
public void windowActivated  ( WindowEvent evt)
{}
public void windowDeactivated( WindowEvent evt)
{}
public void windowIconified	 ( WindowEvent evt)
{}
public void windowDeiconified( WindowEvent evt)
{}
public void windowClosing		 ( WindowEvent evt)
{
	Window w = evt.getWindow();
	w.dispose();
	System.exit(0);
}
public void windowClosed		 ( WindowEvent evt)
{}



	public static void main(String args[])
	{
		new ScrollbarFrame();
	}
}
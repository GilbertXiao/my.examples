i
mport java.awt.*;
import java.awt.event.*;

public class FreeHandDrawingFrame extends Frame 
implements MouseListener,MouseMotionListener
{
	int oldX,oldY,newX,newY;

	FreeHandDrawingFrame()
	{
		setSize(400,400);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension desktopSize = t.getScreenSize();

		Dimension mySize = this.getSize();
		
		this.setLocation
		(
			(desktopSize.width-mySize.width)/2 ,
			(desktopSize.height-mySize.height)/2
		);



		setVisible(true);
	}

	public void update(Graphics g)
	{
		paint(g);
	}

	public void paint(Graphics g)
	{
		g.drawLine( oldX,oldY,newX,newY);
	}

	public void mousePressed (MouseEvent evt)
	{
		newX = evt.getX();
		newY = evt.getY();
	}

	public void mouseReleased(MouseEvent evt){}
	public void mouseClicked (MouseEvent evt){}
	public void mouseEntered (MouseEvent evt){}
	public void mouseExited  (MouseEvent evt){}

	public void mouseMoved    (MouseEvent evt){}

	public void mouseDragged  (MouseEvent evt)
	{
		oldX = newX;
		oldY = newY;
		newX = evt.getX();
		newY = evt.getY();

		repaint();	
	}


	public static void main(String[] args) 
	{
		new FreeHandDrawingFrame();
	}
}
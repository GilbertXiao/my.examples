
import java.awt.*;
import java.awt.event.*;

class MouseFrame extends Frame 
implements MouseListener,MouseMotionListener
{
	int oldx,oldy,newx,newy;
	
	MouseFrame()
	{
		setSize(400,400);
		addMouseListener(this);
		addMouseMotionListener(this);
		setVisible(true);		
	}

	public void mousePressed (MouseEvent evt)
	{
		newx = evt.getX();
		newy = evt.getY();
	}

	public void mouseReleased(MouseEvent evt){}
	public void mouseClicked (MouseEvent evt){}
	public void mouseEntered (MouseEvent evt){}
	public void mouseExited  (MouseEvent evt){}

	public void mouseMoved  (MouseEvent evt){}

	public void mouseDragged(MouseEvent evt)
	{
		oldx = newx;
		oldy = newy;
		newx = evt.getX();
		newy = evt.getY();
		repaint();
	}

	public void update(Graphics g)
	{
		paint(g);
	}

	public void paint(Graphics g)
	{
		g.drawLine(oldx,oldy,newx,newy);
	}
	public static void main(String[] args) 
	{
		new MouseFrame();
	}
}
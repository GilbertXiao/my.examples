import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

public class AnimationApplet extends Applet
{
	int x,y;
	int w,h;
	Image img;
	
	public void init()
	{
		Dimension d = getSize();
		w = d.width;
		h = d.height;
		
		img = createImage( w, h );		

		this.addMouseMotionListener
							( new MyMouseListener() );
	}

	public void update(Graphics g)
	{
		this.paint(g);
	}

	public void paint(Graphics g)
	{
		Dimension d = getSize();
		w = d.width;
		h = d.height;
	
		Graphics appletG = g;
		g = img.getGraphics();

		g.setColor(Color.black);
		g.fillRect(0,0,w,h);

		g.setColor(Color.red);
		for(int i=0; i<=h; i+=3)
			g.drawLine(0,i, w,h-i);

		g.setColor(Color.blue);
		for(int i=0; i<=w; i+=3)
			g.drawLine(i,0, w-i,h);

		g.setColor(Color.yellow);
		g.fillOval(x,y,20,20);

		appletG.drawImage(img,0,0,this);
	}

	class MyMouseListener extends MouseMotionAdapter
	{
		public void mouseDragged(MouseEvent evt)
		{
			AnimationApplet.this.x = evt.getX();
			AnimationApplet.this.y = evt.getY();
			AnimationApplet.this.repaint();
		}
	}
}
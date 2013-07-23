import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

public class ImageApplet2 extends Applet
{
	Image img;

	public void init()
	{
		img = this.createImage(200,200);
		Graphics ig = img.getGraphics();
	
		ig.setColor(Color.red);
		ig.fillRect(0,0,200,200);
		ig.setColor(Color.yellow);
		ig.fillRect(0,0,100,100);
	}

	public void paint(Graphics g)
	{
		g.drawImage(img,20,20,this);
	}
}
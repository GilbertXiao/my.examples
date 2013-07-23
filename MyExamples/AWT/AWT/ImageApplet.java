import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

public class ImageApplet extends Applet
{
	Image img;

	public void init()
	{
		String imgName = getParameter("myimage");
		if(imgName != null)
		{
			URL u = this.getCodeBase();
			img   = this.getImage(u ,imgName);
		}
	}

	public void paint(Graphics g)
	{
		g.drawImage(img,20,20,100,100,this);
	}
}
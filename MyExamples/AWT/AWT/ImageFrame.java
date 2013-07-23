
import java.awt.*;

class ImageFrame extends Frame
{
	Image img;

	ImageFrame()
	{
		setSize(400,400);
		Toolkit toolkit = 
			Toolkit.getDefaultToolkit();

		img = toolkit.getImage("ejbarch.gif");
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(img,40,40,200,200,this);
	}
	
	public static void main(String args[])
	{
		new ImageFrame().setVisible(true);
	}
}
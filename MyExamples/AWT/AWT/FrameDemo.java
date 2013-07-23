import java.awt.*;

class MyFrame extends Frame
{
	MyFrame()
	{
		this.setSize(400,300);
	
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension desktopSize = toolkit.getScreenSize();
		Dimension mySize = this.getSize();
		
		this.setLocation(
			(desktopSize.width -mySize.width) /2 ,
			(desktopSize.height-mySize.height)/2 
									);

		this.setVisible(true);
	}

	public void paint(Graphics g)
	{
		g.drawString("Hello Frame",100,100);

		Font f = new Font("Arial",Font.BOLD,20);
		g.setFont(f);
		g.setColor(Color.red);
		g.drawString("Hello Frame",100,150);
	}
}


class FrameDemo 
{
	public static void main(String[] args) 
	{
		new MyFrame();
	
	}
}
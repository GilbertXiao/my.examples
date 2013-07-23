import java.awt.*;

class Frame extends Frame
{
	Frame()
	{
		this.setSize(400,300);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension desktopSize = toolkit.getScreenSize();
		Dimension mySize = this.getSize();
		
		this.setLocation(
			(desktopSize.width -mySize.width) /2 ,
			(desktopSize.height-mySize.height)/2 
									);

		FlowLayout fl = new FlowLayout();
		this.setLayout( fl );
		
	
		this.setVisible(true);
	}
}


class FrameDemo 
{
	public static void main(String[] args) 
	{
		new Frame();
	
	}
}

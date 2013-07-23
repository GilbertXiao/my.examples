import java.awt.*;

class LabelFrame extends Frame
{
	Label myLabel;
		
	LabelFrame()
	{
		this.setSize(400,300);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension desktopSize = toolkit.getScreenSize();
		Dimension mySize = this.getSize();
		
		this.setLocation((desktopSize.width -mySize.width) /2 ,(desktopSize.height-mySize.height)/2 );

		FlowLayout fl = new FlowLayout();
		this.setLayout( fl );
		
		myLabel = new Label("Sample");
		myLabel.setForeground(Color.red);
		myLabel.setBackground(Color.yellow);

		this.add(myLabel);

		add( new Label("Second") );
		
		this.setVisible(true);
	}
}


class FrameDemo 
{
	public static void main(String[] args) 
	{
		new LabelFrame();
	
	}
}

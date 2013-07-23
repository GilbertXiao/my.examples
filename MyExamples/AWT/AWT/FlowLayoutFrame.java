
import java.awt.*;

class FlowLayoutFrame
{
	public static void main(String[] args) 
	{
		Frame f = new Frame();
		f.setSize(300,300);

		FlowLayout fl = 
			new FlowLayout(FlowLayout.CENTER,10,20);

		f.setLayout( fl );

		f.add( new Button("Ok") );

		f.setVisible(true);
	}
}

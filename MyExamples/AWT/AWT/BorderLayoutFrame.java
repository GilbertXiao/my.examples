import java.awt.*;

class BorderLayoutFrame
{
	public static void main(String[] args) 
	{
		Frame f = new Frame();
		f.setSize(300,300);
		f.setLocation(200,150);

		BorderLayout bl = new BorderLayout(10,30);
		f.setLayout( bl );

		Panel p = new Panel();
		Button b = new Button("OK");
		p.add(b);

		f.add("South", p);

		f.setVisible(true);
	}
}

import java.awt.*;

class PanelFrame
{
	public static void main(String[] args) 
	{
		Frame f = new Frame();
		f.setSize(300,300);
		f.setLocation(200,150);

		FlowLayout fl = 
			new FlowLayout(FlowLayout.CENTER,10,20);

		f.setLayout( fl );

		Panel p = new Panel();
		p.add(new Button("Button"));
		p.add(new Button("Button"));

		p.setBackground(Color.red);
		f.add( p );

		f.setVisible(true);
	}
}

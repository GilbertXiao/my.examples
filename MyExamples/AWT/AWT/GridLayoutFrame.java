import java.awt.*;

class GridLayoutFrame
{
	public static void main(String[] args) 
	{
		Frame f = new Frame();
		f.setSize(300,300);
		f.setLocation(200,150);
		f.setFont(new Font("Serif",Font.PLAIN,25));

		GridLayout gl = new GridLayout(0,1);
		Panel p = new Panel();
		p.setLayout( gl );

	p.add(new Scrollbar(Scrollbar.HORIZONTAL));
	p.add(new Scrollbar(Scrollbar.HORIZONTAL));
	p.add(new Scrollbar(Scrollbar.HORIZONTAL));
	p.add(new Scrollbar(Scrollbar.HORIZONTAL));

		f.add("South",p);

		f.setVisible(true);
	}
}

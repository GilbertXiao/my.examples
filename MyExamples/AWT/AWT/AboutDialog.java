import java.awt.*;
import java.awt.event.*;

class AboutDialog extends Dialog implements ActionListener
{
	Label l;
	Button ok;
	
	AboutDialog(Frame parent,String title,String msg)
	{
		super(parent,title,true);
		setSize(300,200);

		l = new Label(msg,Label.CENTER);
		ok = new Button(" OK ");
		ok.addActionListener(this);
		Panel p = new Panel();
		p.add(ok);

		add("Center",l);
		add("South",p);
	}

	public void actionPerformed(ActionEvent evt)
	{
		if(evt.getSource() == ok)
			dispose();
	}
}
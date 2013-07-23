import java.awt.*;
import java.awt.event.*;

public class InputFrame extends Frame implements ActionListener
{
	Button show;
	String s = "";
	InputDialog iDialog;

	InputFrame()
	{
		setSize	(400,300);
		setFont(new Font("SanSerif",Font.PLAIN,20));
		setLayout(new FlowLayout());

		show = new Button("Show");
		show.addActionListener(this);
		add(show);

		setVisible(true);
	}

	public void paint(Graphics g)
	{
		g.drawString(s,100,100);
	}

	public void actionPerformed(ActionEvent evt)
	{
	 if(iDialog == null)
	 {
		iDialog=new InputDialog(this,"Enter Text");
	 }

		System.out.println(s);
		iDialog.setString(s);
		iDialog.setVisible(true);
		String temp = iDialog.getString();
	
		if(temp != null)
		{
			s = temp;
			repaint();
		}
	}

	public static void main(String args[])
	{
		new InputFrame();
	}
}
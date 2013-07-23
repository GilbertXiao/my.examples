import java.awt.*;
import java.awt.event.*;

class TextFrame extends Frame 
{
	TextFrame()
	{
		setSize(300,300);
		setLayout(new FlowLayout());
		setFont(new Font("Arial",Font.PLAIN,20));


		add(new TextField());
		add(new TextField(15));
		add(new TextField("Hello"));
		add(new TextField("Java",10));

		TextField tf = new TextField(10);
		tf.setEchoChar('*');
		add(tf);
		
		add(new TextArea(8,25));




		setVisible(true);
	}


	public static void main(String args[])
	{
		new TextFrame();
	}
}
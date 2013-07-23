import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class MyApplet extends Applet
{
	public void init()
	{
		setFont(new Font("Arial",Font.BOLD,16));
		setBackground(Color.pink);

		add(new Button("Button"));
	}
	public void start()
	{
		System.out.println("\tStart");
	}
	public void paint(Graphics g)
	{}
	public void stop()
	{
		System.out.println("\t\t\tStop");
	}

	public void destroy()
	{
		System.out.println("\t\t\t\tDestroy");
	}
}
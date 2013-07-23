import java.awt.*;
import java.awt.event.*;

class MenuFrame extends Frame 
implements ActionListener,ItemListener
{
	boolean figureFlag;
	boolean transFlag;
	Color fillColor;

	MenuBar mbar;
	Menu obj,opn,clr,hlp;
	MenuItem rect,oval,exit,red,green,blue,about;
	CheckboxMenuItem trans;
	
	AboutDialog aDialog;

	MenuFrame()
	{
		this.setSize(400,400);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension desktopSize = toolkit.getScreenSize();
		Dimension mySize = this.getSize();
		
		this.setLocation(
			(desktopSize.width -mySize.width) /2 ,
			(desktopSize.height-mySize.height)/2 
									);

		mbar = new MenuBar();

		obj = new Menu("Object");
		opn = new Menu("Option");
		clr = new Menu("Color");
		hlp = new Menu("Help");

		MenuShortcut rectMS = 
				new MenuShortcut('R',true);

		rect = new MenuItem("Rectangle", rectMS);
		rect.addActionListener(this);

		MenuShortcut ovalMS = 
				new MenuShortcut('o');

		oval = new MenuItem("Oval",ovalMS);
		oval.addActionListener(this);

		exit = new MenuItem("Exit");
		exit.addActionListener(this);

		red = new MenuItem("Red");
		red.addActionListener(this);

		green = new MenuItem("Green");
		green.addActionListener(this);

		blue = new MenuItem("Blue");
		blue.addActionListener(this);

		about = new MenuItem("About");
		about.addActionListener(this);

		trans = 
			new CheckboxMenuItem("Transparent",false);

		trans.addItemListener(this);

		obj.add(rect);
		obj.add(oval);
		obj.addSeparator();
		obj.add(exit);

		clr.add(red);
		clr.add(green);
		clr.add(blue);

		opn.add(trans);
		opn.addSeparator();
		opn.add(clr);

		hlp.add(about);

		mbar.add(obj);
		mbar.add(opn);
		mbar.add(hlp);
		this.setMenuBar(mbar);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent evt)
	{
		Object src = evt.getSource();
		
		if(src == exit)
		{
			System.exit(0);
		}
		else if(src == about)
		{
			if(aDialog == null)
			{
				aDialog = new AboutDialog(this,"About Dialog","Menu App 1.0");
			}
			aDialog.setVisible(true);		
		}
		else
		{
			if(src == rect)
				figureFlag = false;

			else if(src == oval)
				figureFlag = true;

			else if(src == red)
				fillColor = Color.red;

			else if(src == green)
				fillColor = Color.green;

			else if(src == blue)
				fillColor = Color.blue;

			repaint();
		}	
	}

	public void itemStateChanged(ItemEvent evt)
	{
		boolean z = trans.getState();
		transFlag = z;
		clr.setEnabled( !z );
		repaint();
	}

	public void paint(Graphics g)
	{

		Dimension d = getSize();
		int w = d.width;
		int h = d.height;
		
		if(transFlag)
		{
			if(figureFlag)
				g.drawOval(w/4,h/4,w/2,h/2);
			else
				g.drawRect(w/4,h/4,w/2,h/2);
		}
		else
		{
			g.setColor( fillColor );

			if(figureFlag)
				g.fillOval(w/4,h/4,w/2,h/2);
			else 
				g.fillRect(w/4,h/4,w/2,h/2);		
		}		
	}

	public static void main(String[] args) 
	{
		new MenuFrame();
	
	}
}

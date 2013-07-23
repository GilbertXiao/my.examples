import java.awt.*;
import java.awt.event.*;
import java.applet.*;

class ButtonPanel extends Panel
{
	Button b[];

	ButtonPanel(String s[])
	{
		b = new Button[s.length];

		for(int i=0;i<b.length;i++)
		{
			b[i] = new Button(s[i]);
			this.add(b[i]);
		}
	}
}

class CheckboxPanel extends Panel
{
	Checkbox c[];

	CheckboxPanel(String s[])
	{
		c = new Checkbox[s.length];

		for(int i=0;i<c.length;i++)
		{
			c[i] = new Checkbox(s[i]);
			this.add(c[i]);
		}
	}
}
class ScrollbarPanel extends Panel
{
	Scrollbar s[];

	ScrollbarPanel(int a)
	{
		s = new Scrollbar[a];

		for(int i=0;i<s.length;i++)
		{
			s[i] = new Scrollbar();
			this.add(s[i]);
		}
	}
}	

public class CardLayoutApplet extends Applet 
implements ActionListener
{
	Panel mainPanel;
	ButtonPanel buttonCard,navPanel;
	CheckboxPanel checkboxCard;
	ScrollbarPanel scrollbarCard;
	CardLayout cl;

	public void init()
	{

	setFont(new Font("SanSerif",Font.BOLD,18));

	setLayout(new BorderLayout());
	
	navPanel = new ButtonPanel
		(new String[]{"First","Previous","Next","Last","Button","Checkbox","Scrollbar"});

	int n = navPanel.b.length;

	for(int i=0;i<n;i++)
		navPanel.b[i].addActionListener(this);

	add("South",navPanel);

	buttonCard = new ButtonPanel
		(new String[]{"Yes","No","Cancel"});

	checkboxCard = new CheckboxPanel
		(new String[]{"C","C++","Java"});

	scrollbarCard = new ScrollbarPanel(4);

	buttonCard.setBackground(Color.orange);
	checkboxCard.setBackground(Color.pink);
	scrollbarCard.setBackground(Color.yellow);

	mainPanel = new Panel();
	cl = new CardLayout();
	mainPanel.setLayout(cl);

	mainPanel.add("Button",buttonCard);
	mainPanel.add("Checkbox",checkboxCard);
	mainPanel.add("Scrollbar",scrollbarCard);

	add("Center",mainPanel);

	} // End of init

	public void actionPerformed(ActionEvent evt)
	{
		String str = evt.getActionCommand();
	
		if(str.equalsIgnoreCase("first"))
			cl.first(mainPanel);

		else if(str.equalsIgnoreCase("next"))
			cl.next(mainPanel);

		else if(str.equalsIgnoreCase("previous"))
			cl.previous(mainPanel);

		else if(str.equalsIgnoreCase("last"))
			cl.last(mainPanel);

		else 
			cl.show(mainPanel,str);
	}
}
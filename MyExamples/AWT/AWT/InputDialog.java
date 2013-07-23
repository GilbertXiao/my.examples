import java.awt.*;
import java.awt.event.*;

class InputDialog extends Dialog implements ActionListener
{
	Label l;
	Button ok,cancel;
	private TextField tf;
	private String str;

	InputDialog(Frame parent,String title)
	{
		super(parent,title,true);
		setSize(300,200);

		l = new Label("Enter Data");
		tf = new TextField(15);
		ok = new Button(" OK ");
		cancel = new Button("Cancel");
		ok.addActionListener(this);
		cancel.addActionListener(this);

		setLayout(new GridLayout(2,2));
		
		add(l);
		add(tf);
		add(ok);
		add(cancel);
	}

	public String getString()
	{
		return str;
	}

	public void setString(String s)
	{
		tf.setText(s);
	}

	public void actionPerformed(ActionEvent evt)
	{
		Object o = evt.getSource();

		if(o == ok)
			str = tf.getText();

		else if(o == cancel)
			str = null;
	
		dispose();
	}
}
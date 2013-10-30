package mr.shravan.jms;
import java.util.Properties;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class MyQueueSender
{
	public static void main(String args[]) throws Exception
	{
		String jndi_name =
			"weblogic.jndi.WLInitialContextFactory";

		String factory_name =
			"weblogic.jms.ConnectionFactory";

		String queue_name = "MyJMSQueueJNDI";

		Properties p = new Properties();

		p.put(Context.INITIAL_CONTEXT_FACTORY,
			"weblogic.jndi.WLInitialContextFactory");

		InitialContext ctx = new InitialContext(p);

		Object obj = ctx.lookup(factory_name);

		QueueConnectionFactory cf =
								(QueueConnectionFactory)obj;

		QueueConnection conn = 
					cf.createQueueConnection();

		conn.start();

		QueueSession session =
			conn.createQueueSession
			(false,QueueSession.AUTO_ACKNOWLEDGE);
		
		Queue q = (Queue)ctx.lookup(queue_name);

	QueueSender sender = session.createSender(q);

	TextMessage msg1 = session.createTextMessage();
	msg1.setText("One more Kidnapping by Veerappan");
	msg1.setStringProperty("category","politics");
	sender.send(msg1);
	System.out.println("1st Message Sent Successfully");

	TextMessage msg2 = session.createTextMessage();
	msg2.setText("India loses the 5th ODI match");
	msg2.setStringProperty("category","sports");
	sender.send(msg2);
	System.out.println("2nd Message Sent Successfully");

	TextMessage msg3 = session.createTextMessage();
	msg3.setText("This is demo message");
	sender.send(msg3);
	System.out.println("3rd Message Sent Successfully");


		session.close();
		conn.close();
	}
}
import java.util.*;
import javax.jms.*;
import javax.naming.*;

public class MyQueueSender2
{
	public static void main(String args[]) throws Exception
	{
		String jndi_name =
			"weblogic.jndi.WLInitialContextFactory";

		String factory_name =
			"weblogic.jms.ConnectionFactory";

		String queue_name = "MyJMSQueueJNDI";

Properties p = new Properties();
p.put(Context.INITIAL_CONTEXT_FACTORY,jndi_name);

		Context ctx = new InitialContext(p);

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

	TextMessage msg = session.createTextMessage();
	msg.setText(args[0]);
	sender.send(msg);
	System.out.println("Message Sent Successfully");


		session.close();
		conn.close();
	}
}
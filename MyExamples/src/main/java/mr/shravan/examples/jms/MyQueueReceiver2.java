package mr.shravan.examples.jms;
import java.util.Properties;

import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class MyQueueReceiver2
{
	public static void main(String args[])
	throws Exception
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

		String filter1 = "JMSPriority >= 6";

		String filter2 = "category='sports'";

		String ORfilter = 
			"JMSPriority >= 6 OR category='sports'";

		String filter3 = "category IS NOT NULL";

		QueueReceiver receiver = 
			session.createReceiver(q, filter3);

		boolean z = true;
		while(z)
		{
			Message msg = receiver.receive(5000);
			if(msg != null)
			{
				if(msg instanceof TextMessage)
				{
					TextMessage txtMsg = (TextMessage)msg;
					System.out.println(txtMsg.getText());
				}
				else 
					System.out.println
						("Message Received in Unknown format");
			}
			else 
				System.out.println("Not Available");
		}

		session.close();
		conn.close();
	}
}
import java.util.*;
import javax.jms.*;
import javax.naming.*;

class MyListener implements MessageListener
{
	public void onMessage(Message msg) 
	{
		if(msg instanceof TextMessage)
		{
			TextMessage txtMsg = (TextMessage)msg;
			try
			{
				String str = txtMsg.getText();
				System.out.println(str);
			}
			catch(JMSException e)
			{
				System.out.println("Error....");
				System.out.println(e.getMessage());
			}
		}
		else
		{
			System.out.println
				("Message Received in Unknown Format");
		}
	}
}


public class MyQueueReceiver3
{
	public static void main(String args[])
	throws Exception
	{
		String jndi_name =
			"weblogic.jndi.WLInitialContextFactory";

		String factory_name =
			"weblogic.jms.ConnectionFactory";

		String queue_name = "MyJMSQueue";

		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY,jndi_name);

		InitialContext ctx = new InitialContext(p);

		Object obj = ctx.lookup(factory_name);

		QueueConnectionFactory cf =
									(QueueConnectionFactory)obj;

		QueueConnection conn = cf.createQueueConnection();

		QueueSession session =
			conn.createQueueSession
			 (false,QueueSession.AUTO_ACKNOWLEDGE);

		Queue q = (Queue)ctx.lookup(queue_name);

		String filter1 = "JMSPriority >= 6";
		String filter2 = "category = 'sports'";
		String filter3 = "category IS NOT NULL";

		QueueReceiver receiver = 
						session.createReceiver(q,filter3);

		receiver.setMessageListener(new MyListener());

		conn.start();

		Thread.currentThread().join();

		session.close();
		conn.close();
	}
}
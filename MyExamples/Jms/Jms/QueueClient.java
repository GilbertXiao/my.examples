import java.util.*;
import javax.jms.*;
import javax.naming.*;

public class QueueClient
{
	public static void main(String args[])
	throws Exception
	{
		String factory_name =
			"weblogic.jms.ConnectionFactory";

		String queue_name = "MyQueue";

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

		Queue q = (Queue)ctx.lookup("MyQueue");

		QueueReceiver receiver =
		 session.createReceiver(q,"news = 'sports'");

		boolean z = true;
		while(z)
		{
			Message msg = receiver.receive(2000);

			if(msg != null)
			{
				if(msg instanceof TextMessage)
				{
					TextMessage txtMsg = 
								(TextMessage)msg;

					String str = txtMsg.getText();
					System.out.println(str);
				}
				else
				{
					System.out.println
						("Message Received in Unknown Format");
				}
			}
			else
			{
				System.out.println
					("Message NOT Available");
			}
		}
		session.close();
		conn.close();
	}
}
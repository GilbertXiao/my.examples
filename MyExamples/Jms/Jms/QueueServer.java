import java.util.*;
import javax.jms.*;
import javax.naming.*;

public class QueueServer
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

		QueueSender sender = session.createSender(q);

		TextMessage msg = session.createTextMessage();
		msg.setText(args[0]);

		sender.send(msg);

		System.out.println("Message Sent Successfully");

		session.close();
		conn.close();
	}
}
package mr.shravan.examples.jms;
import java.util.*;
import javax.jms.*;
import javax.naming.*;

public class TopicDurableSubscriberDemo
{
	public static void main(String args[])
	throws Exception
	{
		String factory_name =	
				   "weblogic.jms.ConnectionFactory";

		Properties p = new Properties();

		p.put(Context.INITIAL_CONTEXT_FACTORY,
			"weblogic.jndi.WLInitialContextFactory");

		InitialContext ctx = new InitialContext(p);

		Object obj = ctx.lookup(factory_name);

		TopicConnectionFactory topicFactory =
								(TopicConnectionFactory)obj;
		
		TopicConnection conn =
				topicFactory.createTopicConnection();
		
		// durable subscriber identification
		conn.setClientID("abcd");

		conn.start();

		TopicSession session =
				conn.createTopicSession
				(false,TopicSession.AUTO_ACKNOWLEDGE);

		Topic _topic = 
			(Topic)ctx.lookup("MyJMSTopicJNDI");

		TopicSubscriber subscriber = 
				session.createDurableSubscriber
					( _topic, conn.getClientID() );

		boolean z = true;

		while(z)
		{
			//Message msg = subscriber.receive();
			Message msg = subscriber.receive(5000);
			//Message msg = subscriber.receiveNoWait();

			if(msg != null)
			{
				if(msg instanceof TextMessage)
				{
					TextMessage txtMessage = (TextMessage)msg;
					System.out.println(txtMessage.getText());
				}
				else 
					System.out.println
						("Message Received in Unknown format");
			}
			else
				System.out.println("Message Not available");
		}

		session.close();
		conn.close();
	}
}

/*
weblogic.jms.extensions.

		JMSHelper.createPermanentTopicAsync
				(	javax.naming.Context,
					String jmsServerName,
				  String topicName,
				  String jndiName
				)
*/
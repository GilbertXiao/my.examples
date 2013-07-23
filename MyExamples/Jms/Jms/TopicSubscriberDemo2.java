import java.util.*;
import javax.jms.*;
import javax.naming.*;

public class TopicSubscriberDemo2
{
	public static void main(String args[])
	throws Exception
	{
		String conn_factory =
			"weblogic.jms.ConnectionFactory";

		String topic_name = "MyJMSTopicJNDI";

		Properties p = new Properties();

		p.put(Context.INITIAL_CONTEXT_FACTORY,
			"weblogic.jndi.WLInitialContextFactory");

		InitialContext ctx = new InitialContext(p);

		Object obj = ctx.lookup(conn_factory);

		TopicConnectionFactory topicFactory =
								(TopicConnectionFactory)obj;
		
		TopicConnection conn =
				topicFactory.createTopicConnection();
		
		conn.start();

		TopicSession session =
			conn.createTopicSession
			 (false,TopicSession.AUTO_ACKNOWLEDGE);
	
		Topic _topic = 
		  //session.createTopic(topic_name);
			(Topic)ctx.lookup(topic_name);

	String filter = 
			"JMSPriority >= 6";

	TopicSubscriber subscriber = 
		session.createSubscriber
			(_topic, null, true);

		boolean z = true;

	while(z)
	{
		Message msg = subscriber.receive(3000);

		if(msg != null)
		{
			if(msg instanceof TextMessage)
			{
				TextMessage txtMessage = (TextMessage)msg;
				System.out.println(txtMessage.getText());
				txtMessage.acknowledge();
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
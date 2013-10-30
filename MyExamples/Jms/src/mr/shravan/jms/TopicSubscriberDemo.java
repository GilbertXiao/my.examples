package mr.shravan.jms;
import java.util.*;
import javax.jms.*;
import javax.naming.*;

public class TopicSubscriberDemo
{
	public static void main(String args[])
	throws Exception
	{
		String jndi_factory =
			"weblogic.jndi.WLInitialContextFactory";
	
		String conn_factory =
			"weblogic.jms.ConnectionFactory";

		String topic_name = "MyJMSTopicJNDI";

		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY,	jndi_factory);

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
							(Topic)ctx.lookup(topic_name);

		TopicSubscriber subscriber = 
				session.createSubscriber( _topic );

		boolean z = true;

	while(z)
	{
		Message msg = subscriber.receive(5000);
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
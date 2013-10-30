package mr.shravan.jms;

import java.util.*;
import javax.jms.*;
import javax.naming.*;

public class TopicPublisherDemo
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
		p.put(Context.INITIAL_CONTEXT_FACTORY,jndi_factory);

		Context ctx = new InitialContext(p);

		Object obj = ctx.lookup(conn_factory);

		TopicConnectionFactory topicFactory =
							( TopicConnectionFactory ) obj;
		
		TopicConnection conn =
				topicFactory.createTopicConnection();
		
		conn.start();

		TopicSession session =
			conn.createTopicSession
			(false, TopicSession.AUTO_ACKNOWLEDGE);

		Topic  _topic = 
							(Topic) ctx.lookup(topic_name);


		TopicPublisher publisher = 
				session.createPublisher( _topic );

		TextMessage txtMsg = 
			session.createTextMessage();

		txtMsg.setText(args[0]);

		publisher.publish(
							 txtMsg,
							 Message.DEFAULT_DELIVERY_MODE,
							 Message.DEFAULT_PRIORITY,
							 10000 );
					
		System.out.println("Message Sent Successfully");

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
												String jndiNameForTopic
											)
*/

package mr.shravan.jms;
import java.util.*;
import javax.jms.*;
import javax.naming.*;

class MyListener2 implements MessageListener
{
	public void onMessage( Message msg )
	{
		try	{
			if(msg instanceof TextMessage)
			{
				TextMessage txtMsg = (TextMessage)msg;
				System.out.println
				 ("In Listener : "+ txtMsg.getText());
			}
			else 
				System.out.println
				("Message Received in Unknown format");
		}
		catch(JMSException e)
		{
			System.out.println("ERROR...");
			e.printStackTrace();
		}
	} // end of onMessage
}		// end of class


public class TopicSubscriberListenerDemo
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
		
		TopicSession session =
			conn.createTopicSession
			 (false,TopicSession.AUTO_ACKNOWLEDGE);

		Topic _topic = 
			(Topic)ctx.lookup("MyJMSTopicJNDI");

		TopicSubscriber subscriber = 
					session.createSubscriber(_topic);

		MyListener2 listener = new MyListener2();
		subscriber.setMessageListener( listener);
		conn.start();
		
		Thread t = Thread.currentThread();
		t.join();


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
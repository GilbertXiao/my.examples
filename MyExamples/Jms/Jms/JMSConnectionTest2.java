import java.util.Properties;
import javax.jms.*;
import javax.naming.*;

class JMSConnectionTest
{
	public static void main(String[] args) throws Exception 
	{
		Properties p = new Properties();

		p.put(Context.INITIAL_CONTEXT_FACTORY,
			 "weblogic.jndi.WLInitialContextFactory");

		InitialContext ctx = new InitialContext(p);
		
		Object obj = 
			ctx.lookup("weblogic.jms.ConnectionFactory");

		TopicConnectionFactory cf = 
			(TopicConnectionFactory)obj;

		System.out.println
			("Got the ConnectionFactory");

		TopicConnection conn = 
			cf.createTopicConnection();

		System.out.println("Got the Connection");

		TopicSession session = 
			conn.createTopicSession
			(false,TopicSession.CLIENT_ACKNOWLEDGE);

		System.out.println("Got the Session");

		String topicName = args[0];
		
		Topic _topic = null;
		try
		{
			_topic = ctx.lookup(topicName);
		}
		catch(Exception e)
		{
			System.out.println("Unable to get Topic");
			return;
		}

		TopicPublisher pub = 
			session.createPublisher(_topic);

		TextMessage msg = session.createTextMessage();

		msg.setText(String msgText)
		
		pub.publish(msg);

		pub.publish(Message,int deliveryMode,
			        int priority,long timeToLive );

		session.close();
		conn.close();
	}
}
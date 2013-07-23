import java.util.*;
import javax.jms.*;
import javax.naming.*;

public class Client
{
	public static void main(String args[]) 
	throws Exception
	{
		String jndi_factory = 
			"weblogic.jndi.WLInitialContextFactory";

		String conn_factory = 
			"weblogic.jms.ConnectionFactory";

		String topic_name = "mytopic2";

		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY,jndi_factory);

		Context ctx = new InitialContext(p);

		Object obj = ctx.lookup(conn_factory);

		TopicConnectionFactory tcf = 
				(TopicConnectionFactory)obj;
		
		TopicConnection conn = 
				tcf.createTopicConnection();

		conn.start();

		TopicSession session = 
			conn.createTopicSession
				(false,TopicSession.AUTO_ACKNOWLEDGE);

		Topic _topic = (Topic)ctx.lookup(topic_name);
		
		TopicPublisher pub = 
			session.createPublisher(_topic);

		TextMessage msg1 = session.createTextMessage();
		TextMessage msg2 = session.createTextMessage();

		msg1.setText("This is 1 message");
		msg2.setText("This is 2 message");

		pub.publish(msg1);
		pub.publish(msg2);

		session.close();
		conn.close();
	}
}
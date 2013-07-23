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

p.put(Context.PROVIDER_URL,"t3://localhost:7001");

		InitialContext ctx = new InitialContext(p);
		
	Object obj = 
		ctx.lookup("weblogic.jms.ConnectionFactory");

	TopicConnectionFactory topicConnFactory = 
	(TopicConnectionFactory)obj;

	System.out.println("Got the Connection Factory");

	TopicConnection conn = 
			topicConnFactory.createTopicConnection();

	System.out.println("Got the Connection");


		conn.close();
	}
}
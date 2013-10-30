package mr.shravan.jms;

import java.util.Properties;
import javax.jms.*;
import javax.naming.*;

class JMSConnectionTest3
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
			(false,TopicSession.AUTO_ACKNOWLEDGE);

		System.out.println("Got the Session");

		session.close();
		conn.close();
	}
}
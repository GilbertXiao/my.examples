package mr.shravan.examples.jms;

import java.util.*;
import javax.jms.*;
import javax.jms.Queue;
import javax.naming.*;

public class MyQueueReceiverListenerForGroups{
	
	public static void main(String[] args) throws Exception {
		new MyQueueReceiverListenerForGroups().addGroupedMessagesListener(null);
	}
	public void addGroupedMessagesListener(QueueSession session) throws Exception {

		String providerURL = "jnp://localhost:1099";
		String facName = "ConnectionFactory";
		String queueName = "queue/A";
		queueName = "/queue/ExampleQueue";
		
		InitialContext ctx = getInitialContext(providerURL,"JBOSS");

		QueueConnection conn = getQueueConnection(facName, ctx);

		conn.start();
		if(session == null){
			session = conn.createQueueSession(false,
					QueueSession.AUTO_ACKNOWLEDGE);
		}
		

		Queue q = (Queue) ctx.lookup(queueName);

		MessageConsumer consumer1 = session.createConsumer(q);
        consumer1.setMessageListener(new SimpleMessageListener("consumer-1"));
        MessageConsumer consumer2 = session.createConsumer(q);
        consumer2.setMessageListener(new SimpleMessageListener("consumer-2"));
		Thread.sleep(10000);
		session.close();
		conn.close();
	}
	private static QueueConnection getQueueConnection(String facName,
			InitialContext ctx) throws NamingException, JMSException {
		
		Object obj = ctx.lookup(facName);

		QueueConnectionFactory cf = (QueueConnectionFactory) obj;

		QueueConnection conn = cf.createQueueConnection();
		return conn;
	}
	private static InitialContext getInitialContext(
			String providerURL, String appType) throws NamingException {
		Properties p = new Properties();
		p.put(Context.PROVIDER_URL, providerURL);
		if("JBOSS".equalsIgnoreCase(appType)){
			p.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
			p.put(Context.URL_PKG_PREFIXES,"org.jboss.naming:org.jnp.interfaces");
		}else{
			p.put(Context.URL_PKG_PREFIXES,"com.ibm.websphere.naming.WsnInitialContextFactory");
		}
		return new InitialContext(p);
	}
	private class SimpleMessageListener implements MessageListener
	   {
	      private String name;
	      public SimpleMessageListener(String listenerName)
	      {
	         name = listenerName;
	      }

	      public void onMessage(Message message)
	      {
	         try
	         {
	            TextMessage msg = (TextMessage)message;
				 String groupID = msg.getStringProperty("JMSXGroupID");
	            System.out.format("Message: [%s] received by %s, (%s in the group), (%s group id)\n",
	                              msg.getText(),
	                              name,
	                              "",groupID);
	         }
	         catch (JMSException e)
	         {
	            e.printStackTrace();
	         }
	      }
	   }
}
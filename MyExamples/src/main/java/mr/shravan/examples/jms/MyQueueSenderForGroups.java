package mr.shravan.examples.jms;

import java.util.Enumeration;
import java.util.Properties;

import javax.jms.ConnectionMetaData;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MyQueueSenderForGroups{
	
	public static void main(String[] args) throws Exception {
		putGroupedMessages();
	}
	private static void putGroupedMessages() throws Exception {

		String providerURL = "jnp://localhost:1099";
		String facName = "ConnectionFactory";
		String queueName = "queue/A";
		queueName = "/queue/ExampleQueue";
		
		InitialContext ctx = getInitialContext(providerURL,"JBOSS");

		QueueConnection conn = getQueueConnection(facName, ctx);

		conn.start();
		ConnectionMetaData mData = conn.getMetaData();
		Enumeration enums = mData.getJMSXPropertyNames();
		while(enums.hasMoreElements()){
			System.out.println("JMSXProperties:"+enums.nextElement());
		}
		System.out.println("JMSProvider Name:"+mData.getJMSProviderName());
		
		System.out.println("JMSProvider Version:"+mData.getJMSVersion());

		QueueSession session = conn.createQueueSession(false,
				QueueSession.AUTO_ACKNOWLEDGE);

		Queue q = (Queue) ctx.lookup(queueName);

		QueueSender sender = session.createSender(q);

		for(int i =0;i<10;i++){
			TextMessage msg1 = session.createTextMessage();
			String gpID = "Group-0";
			msg1.setStringProperty("JMSXGroupID", gpID);
			String xmlString = "<Message +" +i+":GroupID:"+gpID+">";
			msg1.setText(xmlString);
			
			sender.send(msg1);
			
			TextMessage msg2 = session.createTextMessage();
			gpID = "Group-1";
			msg2.setStringProperty("JMSXGroupID", gpID);
			xmlString = "<Message +" +i+":GroupID:"+gpID+">";
			msg2.setText(xmlString);
			sender.send(msg2);
		}
		new MyQueueReceiverListenerForGroups().addGroupedMessagesListener(session);
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
//	private class SimpleMessageListener implements MessageListener
//	   {
//	      private String name;
//	      public SimpleMessageListener(String listenerName)
//	      {
//	         name = listenerName;
//	      }
//
//	      public void onMessage(Message message)
//	      {
//	         try
//	         {
//	            TextMessage msg = (TextMessage)message;
//				 String groupID = msg.getStringProperty("JMSXGroupID");
//	            System.out.format("Message: [%s] received by %s, (%s in the group), (%s group id)\n",
//	                              msg.getText(),
//	                              name,
//	                              "",groupID);
//	         }
//	         catch (JMSException e)
//	         {
//	            e.printStackTrace();
//	         }
//	      }
//	   }
}
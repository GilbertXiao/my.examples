package mr.shravan.jms;

import java.rmi.*;
import java.util.*;

import javax.jms.*;
import javax.ejb.*;
import javax.naming.*;

public class Client2 {
	static private String TOPIC_NAME = "MyTopic";

	private Context m_context;

	private TopicConnection m_topicConnection;

	public Client2(String url) throws NamingException {
		try {

			m_context = getInitialContext();

			Object obj = m_context.lookup("weblogic.jms.ConnectionFactory");

			TopicConnectionFactory cf = (TopicConnectionFactory) obj;

			m_topicConnection = cf.createTopicConnection();

			m_topicConnection.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {

		log("\nBeginning message.Client...\n");

		String url = "t3://localhost:7001";

		Client2 client = null;
		try {
			client = new Client2(url);
		} catch (NamingException ne) {
			System.exit(1);
		}

		try {
			client.example();
		} catch (Exception e) {
			log("There was an exception while creating and using the Trader.");
			log("This indicates that there was a problem communicating with the server: "
					+ e);
			// e.printStackTrace();
		}

		log("\nEnd message.Client...\n");
	}

	public void example() throws RemoteException, JMSException, NamingException {
		Topic newTopic = null;
		TopicSession session = null;
		try {
			session = m_topicConnection.createTopicSession(false,
					Session.AUTO_ACKNOWLEDGE);

			newTopic = (Topic) m_context.lookup(TOPIC_NAME);
		} catch (NamingException ex) {
			newTopic = session.createTopic(TOPIC_NAME);
			m_context.bind(TOPIC_NAME, newTopic);
		}

		TopicPublisher sender = session.createPublisher(newTopic);

		TextMessage tm = session.createTextMessage();

		String[] quotes = new String[] { "BEAS 40 1/8", "SUNW 79 1/2",
				"IBM 82 1/4" };

		for (int i = 0; i < quotes.length; i++) {
			tm.setText(quotes[i]);
			sender.publish(tm);
		}
	}

	private Context getInitialContext() throws NamingException {

		try {
			Properties h = new Properties();

			h.put(Context.INITIAL_CONTEXT_FACTORY,
					"weblogic.jndi.WLInitialContextFactory");

			h.put(Context.PROVIDER_URL, "t3://localhost:7001");

			return new InitialContext(h);
		} catch (NamingException ex) {
			log("We were unable to get a connection to the WebLogic server at Localhost:7001");
			log("Please make sure that the server is running.");
			throw ex;
		}
	}

	/**
	 * This is the Java2 version to get an InitialContext. This version relies
	 * on the existence of a jndi.properties file in the application's
	 * classpath.
	 * 
	 */
	// private static Context getInitialContext()
	// throws NamingException
	// {
	// return new InitialContext();
	// }

	private static void log(String s) {
		System.out.println(s);
	}
}

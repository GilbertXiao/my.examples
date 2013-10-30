package mr.shravan.jms;

import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.naming.Context;
import javax.naming.InitialContext;

public class JMSListenerContainer {

	String ctxFactory = null;

	String factoryName = null;

	String queueName = null;

	private JMSListener listener = null;

	private static JMSListenerContainer container = new JMSListenerContainer();

	public static JMSListenerContainer getContainer() {
		return container;
	}

	public static void setContainer(JMSListenerContainer container) {
		JMSListenerContainer.container = container;
	}
	public static JMSListener getJMSListener(String ctxFactory,
			String providerURL, String factoryName, String queueName){
		String key = ListenerCache.getKey(ctxFactory, providerURL, factoryName,
				queueName);
		return (JMSListener)ListenerCache.getInstance().get(key);
	}
	public static JMSListener getJMSListener(String ctxFactory,
			String providerURL, String factoryName, String queueName,boolean reIntListener) {

		JMSListener listener = container.getListener();
		try {
			
			if(reIntListener){
				listener = null;
			}
			
			if (listener == null) {

				log("Listener is null. Creating a listener");
				log("Params Passed...");
				log("ctxFactory:" + ctxFactory);
				log("providerURL:" + providerURL);
				log("factoryName:" + factoryName);
				log("queueName:" + queueName);

				Properties p = new Properties();

				p.put(Context.INITIAL_CONTEXT_FACTORY, ctxFactory);
				p.put(Context.PROVIDER_URL, providerURL);

				log("Creating initial context");
				InitialContext ctx = new InitialContext(p);
				log("Initial context got created.." + ctx);
				Object obj = ctx.lookup(factoryName);
				log("Looking up queue factory " + factoryName);
				QueueConnectionFactory cf = (QueueConnectionFactory) obj;
				log("Now typecasting to queue factory..");
				QueueConnection conn = cf.createQueueConnection();
				log("Creating queue connection..");
				QueueSession session = conn.createQueueSession(false,
						QueueSession.AUTO_ACKNOWLEDGE);
				log("Queue session got create..." + session);
				log("Looking up queue..." + queueName);
				Queue q = (Queue) ctx.lookup(queueName);
				log("Queue found..." + q);
				QueueReceiver receiver = session.createReceiver(q, null);
				log("Creating a receiver.." + receiver);
				listener = new JMSListener(conn);
				receiver.setMessageListener(listener);
				log("Setting the message listener...");
				ListenerCache.getInstance().put(ctxFactory, providerURL, factoryName, queueName, listener);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listener;
	}

	public static boolean startListener(String ctxFactory, String providerURL, String factoryName, String queueName) {
		try {
			String key = ListenerCache.getKey(ctxFactory, providerURL, factoryName, queueName);
			JMSListener listener = (JMSListener)ListenerCache.getInstance().get(key);
			listener.getConnection().start();
			
		} catch (JMSException e) {
			e.printStackTrace();
			log("Exception while starting the connection...");
			return false;
		}
		return true;
	}

	public static boolean stopListener(String ctxFactory, String providerURL, String factoryName, String queueName) {
		try {
			String key = ListenerCache.getKey(ctxFactory, providerURL, factoryName, queueName);
			JMSListener listener = (JMSListener)ListenerCache.getInstance().get(key);
			listener.getConnection().stop();
		} catch (JMSException e) {
			e.printStackTrace();
			log("Exception while starting the connection...");
			return false;
		}
		return true;
	}

	public String getCtxFactory() {
		return ctxFactory;
	}

	public void setCtxFactory(String ctxFactory) {
		this.ctxFactory = ctxFactory;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public JMSListener getListener() {
		return listener;
	}

	public void setListener(JMSListener listener) {
		this.listener = listener;
	}

	private static void log(String entry) {
		System.out.println( entry);
	}
	public static void main(String[] args) {
		String ctxFactory = "com.ibm.websphere.naming.WsnInitialContextFactory";

		String factoryName = "IFW_Q_Factory";
		String queueName ="IFW_IN";

				String providerURL = "corbaloc:iiop:localhost:9100";
		JMSListenerContainer.getJMSListener(ctxFactory, providerURL,
				factoryName, queueName, true);
		JMSListenerContainer.startListener(ctxFactory, providerURL,
				factoryName, queueName);
	}
}

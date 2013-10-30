package mr.shravan.jms;

import java.util.Properties;

import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

class JMSListenerThread extends Thread {

	JMSListenerConfig config = null;

	public JMSListenerThread(JMSListenerConfig config) {
		this.config = config;
	}

	public void run() {
		try {
			String ctxFactory = config.getCtxFactory();
			String factoryName = config.getQcFactory();
			String queueName = config.getQueueName();
			String providerURL = config.getProviderURL();

			Properties p = new Properties();
			p.put(Context.INITIAL_CONTEXT_FACTORY, ctxFactory);
			Context ctx = new InitialContext(p);
			Object obj = ctx.lookup(factoryName);
			QueueConnectionFactory cf = (QueueConnectionFactory) obj;
			QueueConnection conn = cf.createQueueConnection();
			conn.start();

			QueueSession session = conn.createQueueSession(false,
					QueueSession.AUTO_ACKNOWLEDGE);

			Queue q = (Queue) ctx.lookup(queueName);

			QueueReceiver receiver = session.createReceiver(q);

			boolean shouldListen = true;
			while (shouldListen) {
				String key = ListenerCache.getKey(ctxFactory, providerURL,
						factoryName, queueName);
				shouldListen = ((Boolean) ListenerCache.getInstance().get(key))
						.booleanValue();
				if (shouldListen) {
					break;
				}
				Message msg = receiver.receiveNoWait();
				if (msg != null) {
					if (msg instanceof TextMessage) {
						TextMessage txtMsg = (TextMessage) msg;
						System.out.println(txtMsg.getText());
					} else
						System.out
								.println("Message Received in Unknown format");
				}
			}

			session.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			sendToDLQ(config);
		}
	}

	private void sendToDLQ(JMSListenerConfig config2) {

	}
}
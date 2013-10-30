package mr.shravan.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.QueueConnection;
import javax.jms.TextMessage;

public class JMSListener implements MessageListener {

	private QueueConnection conn = null;

	public QueueConnection getConnection() {
		return conn;
	}

	public void setConnection(QueueConnection conn) {
		this.conn = conn;
	}

	public JMSListener(QueueConnection conn) {
		this.conn = conn;
	}

	public void onMessage(Message msg) {
		if (msg instanceof TextMessage) {
			TextMessage txtMsg = (TextMessage) msg;
			try {
				String xmlString = txtMsg.getText();

				log(xmlString);

			} catch (JMSException e) {
				log("JMSException...." + e);
				e.printStackTrace();
			} catch (Exception e) {
				log("Exception...." + e);
				e.printStackTrace();
				log("Now stopping the listener");
				try {
					getConnection().stop();
				} catch (JMSException e1) {
					log("Exception while stopping the listener");
					e1.printStackTrace();
				}
				log("Listener stopped successfully");
			}
		} else {
			log("Message Received in Unknown Format");
		}
	}

	private static void log(String entry) {
		System.out.println(entry);
	}

}

package mr.shravan.examples.mq.msggroup;

import java.math.BigInteger;
import java.util.Random;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.ibm.mq.jms.JMSC;
import com.ibm.mq.jms.MQConnectionFactory;
import com.ibm.mq.jms.MQQueue;

/**
 * Sends a group of messages using the WebSphere MQ JMS API.
 */
public final class MqJmsGroupSender {

	/**
	 * The name of the queue manager.
	 */
	private final String _queueManagerName;

	/**
	 * The number of bits in a 24-byte group identifier.
	 */
	private static final int BITS_IN_GROUPID = 8 * 24;

	/**
	 * Constructor.
	 * 
	 * @param queueManagerName
	 *            the name of the queue manager
	 */
	public MqJmsGroupSender(final String queueManagerName) {

		_queueManagerName = queueManagerName;

	}

	/**
	 * Sends a group of messages to the named queue.
	 * 
	 * @param queueName
	 *            the name of the queue to receive the messages from
	 * @param messageText
	 *            the text of the message to send
	 * @param groupSize
	 *            the number of messages to send
	 */
	public void sendGroup(final String queueName, final String messageText,
			final int groupSize) {

		try {

			/*
			 * Create the connection factory
			 */

			final MQConnectionFactory factory = new MQConnectionFactory();
			factory.setQueueManager(_queueManagerName);

			/*
			 * Create the queue specifying that the target client may not be
			 * using JMS
			 */

			final MQQueue destination = new MQQueue(queueName);
			destination.setTargetClient(JMSC.MQJMS_CLIENT_NONJMS_MQ);

			/*
			 * Create the connection
			 */

			final Connection connection = factory.createConnection();

			/*
			 * Create the session
			 */

			final Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);

			/*
			 * Create the message producer
			 */

			final MessageProducer producer = session
					.createProducer(destination);

			/*
			 * Construct a random 24-byte group identifier and convert to a
			 * hexadecimal string
			 */

			final String groupIdInHex = new BigInteger(BITS_IN_GROUPID,
					new Random()).toString(16);

			/*
			 * Send the messages
			 */

			for (int i = 1; i <= groupSize; i++) {

				final TextMessage message = session.createTextMessage();

				/*
				 * Set the group identifier and sequence number
				 */

				message.setStringProperty("JMSXGroupID", "ID:" + groupIdInHex);
				message.setIntProperty("JMSXGroupSeq", i);

				if (i == groupSize) {

					/*
					 * Indicate that this is the last message in the group
					 */

					message.setBooleanProperty("JMS_IBM_Last_Msg_In_Group",
							true);
				}

				message.setText(messageText + " " + i);
				producer.send(message);

				System.out.println("Sent message " + message.getText());

			}

			/*
			 * Close the connection
			 */

			connection.close();

		} catch (final JMSException exception) {

			exception.printStackTrace();

		}

	}

	/**
	 * Sends a group of messages.
	 * 
	 * @param args
	 *            an array containing the queue manager name, the queue name and
	 *            the text of the message
	 */
	public static void main(final String[] args) {

		if (args.length != 4) {
			System.out
					.println("Usage: MqJmsGroupSender <QM_NAME> <Q_NAME> <MSG_TEXT> <GROUP_SIZE>");
		}

		final MqJmsGroupSender sender = new MqJmsGroupSender(args[0]);
		sender.sendGroup(args[1], args[2], Integer.parseInt(args[3]));

	}

}

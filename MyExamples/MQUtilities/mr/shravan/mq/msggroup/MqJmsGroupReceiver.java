package mr.shravan.mq.msggroup;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.ibm.mq.jms.MQConnectionFactory;
import com.ibm.mq.jms.MQQueue;

/**
 * Receives a group of messages using the WebSphere MQ JMS API.
 */
public final class MqJmsGroupReceiver {

	/**
	 * The name of the queue manager.
	 */
	private final String _queueManagerName;

	/**
	 * Constructor.
	 * 
	 * @param queueManagerName
	 *            the name of the queue manager
	 */
	public MqJmsGroupReceiver(final String queueManagerName) {

		_queueManagerName = queueManagerName;

	}

	/**
	 * Receives a group of messages from the named queue and outputs them to the
	 * console.
	 * 
	 * @param queueName
	 *            the name of the queue to receive the messages from
	 */
	public void receiveGroup(final String queueName) {

		try {

			/*
			 * Create the connection factory
			 */

			final MQConnectionFactory factory = new MQConnectionFactory();
			factory.setQueueManager(_queueManagerName);

			/*
			 * Create the queue
			 */

			final MQQueue destination = new MQQueue(queueName);

			/*
			 * Create and start the connection
			 */

			final Connection connection = factory.createConnection();
			connection.start();

			/*
			 * Create the session specifying the use of local transactions
			 */

			final Session session = connection.createSession(true,
					Session.AUTO_ACKNOWLEDGE);

			/*
			 * Receive the last message in any group and close the consumer
			 */

			final MessageConsumer lastMessageConsumer = session.createConsumer(
					destination, "JMS_IBM_Last_Msg_In_Group=TRUE");
			final TextMessage lastMessage = (TextMessage) lastMessageConsumer
					.receiveNoWait();
			lastMessageConsumer.close();

			if (lastMessage != null) {

				/*
				 * Retrieve the group size and identifier
				 */

				final int groupSize = lastMessage
						.getIntProperty("JMSXGroupSeq");
				final String groupId = lastMessage
						.getStringProperty("JMSXGroupID");

				boolean failed = false;

				/*
				 * Retrieve the other messages in the group in order
				 */

				for (int i = 1; (i < groupSize) && !failed; i++) {

					final MessageConsumer consumer = session.createConsumer(
							destination, "JMSXGroupID='" + groupId
									+ "'AND JMSXGroupSeq=" + i);
					final TextMessage message = (TextMessage) consumer
							.receiveNoWait();

					if (message != null) {
						System.out.println("Received message "
								+ message.getText());
					} else {
						failed = true;
					}

					consumer.close();

				}

				if (failed) {
					System.out.println("Failed to receive all messages");
					session.rollback();
				} else {
					System.out.println("Received message "
							+ lastMessage.getText());
					session.commit();
				}

			} else {

				System.out.println("No groups available");

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
	 * Receives a group of messages.
	 * 
	 * @param args
	 *            an array containing the queue manager name and queue name
	 */
	public static void main(final String[] args) {

		if (args.length != 2) {
			System.out.println("Usage: MqJmsGroupReceiver <QM_NAME> <Q_NAME>");
		}

		final MqJmsGroupReceiver receiver = new MqJmsGroupReceiver(args[0]);
		receiver.receiveGroup(args[1]);

	}

}

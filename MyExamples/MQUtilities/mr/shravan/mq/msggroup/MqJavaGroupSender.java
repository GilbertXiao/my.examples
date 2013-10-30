package mr.shravan.mq.msggroup;

import java.io.IOException;

import com.ibm.mq.MQC;
import com.ibm.mq.MQException;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;

/**
 * Sends a group of messages using the WebSphere MQ Java API.
 */
public final class MqJavaGroupSender {

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
	public MqJavaGroupSender(final String queueManagerName) {

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
			 * Create a connection to the queue manager
			 */

			final MQQueueManager queueManager = new MQQueueManager(
					_queueManagerName);

			/*
			 * Open the queue for output
			 */

			final MQQueue queue = queueManager.accessQueue(queueName,
					MQC.MQOO_OUTPUT);

			/*
			 * Create the put message options with logical ordering
			 */

			final MQPutMessageOptions pmo = new MQPutMessageOptions();
			pmo.options = MQC.MQPMO_LOGICAL_ORDER;

			/*
			 * Send the messages
			 */

			for (int i = 1; i <= groupSize; i++) {

				/*
				 * Create a message
				 */

				final MQMessage message = new MQMessage();

				/*
				 * Set the message flags to indicate the message is in the group
				 * (and if it is the last)
				 */

				if (i < groupSize) {
					message.messageFlags = MQC.MQMF_MSG_IN_GROUP;
				} else {
					message.messageFlags = MQC.MQMF_LAST_MSG_IN_GROUP;
				}

				/*
				 * Send the message (setting the format to MQSTR to enable it to
				 * be received as a JMS text message)
				 */

				final String messageString = messageText + " " + i;
				message.writeString(messageString);
				message.format = "MQSTR";
				queue.put(message, pmo);

				System.out.println("Sent message " + messageString);

			}

			/*
			 * Close the queue
			 */

			queue.close();

			/*
			 * Disconnect from the queue manager
			 */

			queueManager.disconnect();

		} catch (final MQException exception) {

			exception.printStackTrace();

		} catch (final IOException exception) {

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

		final MqJavaGroupSender sender = new MqJavaGroupSender(args[0]);
		sender.sendGroup(args[1], args[2], Integer.parseInt(args[3]));

	}

}

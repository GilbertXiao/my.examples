package mr.shravan.examples.mq.msggroup;

import java.io.IOException;

import com.ibm.mq.MQC;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;

/**
 * Sends a group of messages using the WebSphere MQ Java API.
 */
public final class MqJavaGroupReceiver {

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
	public MqJavaGroupReceiver(final String queueManagerName) {

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
			 * Create a connection to the queue manager
			 */

			final MQQueueManager queueManager = new MQQueueManager(
					_queueManagerName);

			/*
			 * Open the queue for input using the default options
			 */

			final MQQueue queue = queueManager.accessQueue(queueName,
					MQC.MQOO_INPUT_AS_Q_DEF);

			/*
			 * Create a buffer to receive the message
			 */

			final MQMessage message = new MQMessage();

			/*
			 * Set the get message options to receive the messages in logical
			 * order once they are all available
			 */

			final MQGetMessageOptions gmo = new MQGetMessageOptions();
			gmo.options = MQC.MQGMO_ALL_MSGS_AVAILABLE
					| MQC.MQGMO_LOGICAL_ORDER;

			/*
			 * Match any message initially
			 */
			gmo.matchOptions = MQC.MQMO_NONE;

			/*
			 * Receive messages until we reach the last in the group
			 */

			do {

				/*
				 * Get a message
				 */

				queue.get(message, gmo);

				/*
				 * Now match other messages in the same group
				 */

				gmo.matchOptions = MQC.MQMO_MATCH_GROUP_ID;

				System.out.println("Received message "
						+ message.readStringOfCharLength(message
								.getDataLength()));

			} while (gmo.groupStatus != MQC.MQGS_LAST_MSG_IN_GROUP);

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
	 * Receives a group of messages.
	 * 
	 * @param args
	 *            an array containing the queue manager name and queue name
	 */
	public static void main(final String[] args) {

		if (args.length != 2) {
			System.out.println("Usage: MqJmsGroupReceiver <QM_NAME> <Q_NAME>");
		}

		final MqJavaGroupReceiver receiver = new MqJavaGroupReceiver(args[0]);
		receiver.receiveGroup(args[1]);

	}

}

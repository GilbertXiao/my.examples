package mr.shravan.mq.spring;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class SpringMQSender {
	
	/**
	 * Send the message to JMS destination
	 * 
	 * @return
	 */
	private int sendMessageToDestination() {
		try {
			JmsTemplate jmsTemplate = SPRING_JMS_BEAN_FACTORY
					.getJMSProducerTemplateFromSpring(endPoint);

			if (jmsTemplate != null) {
				jmsTemplate.send(new MessageCreator() {

					public Message createMessage(Session session)
							throws JMSException {
						TextMessage message = session
								.createTextMessage(messageData.toString());
						return message;
					}
				});
			} else {
				responseCode = JMS_CONNECT_ERROR;
			}
		} catch (Exception ex) {
			logError("Exception while sending message to queue", ex);
			responseCode = JMS_CONNECT_ERROR;
		}
		return responseCode;

	}
}

package mr.shravan.mq.spring;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.ExceptionListener;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.util.ErrorHandler;


public class SpringJMSBeanFactory {

	private static final String MQ_QUEUE_CONN_FACTORY_CLASS_NAME = "com.ibm.mq.jms.MQQueueConnectionFactory";
	private static final String MQ_QUEUE_CLASS_NAME = "com.ibm.mq.jms.MQQueue";
	private static final String RECEIVER_QUEUE_BEAN_NAME_PREFIX = "DIFQueue";
	private static final String REPLY_TO_QUEUE_BEAN_NAME_PREFIX = "DIFReplyQueue";
	private static final String SENDER_QUEUE_BEAN_NAME_PREFIX = "DIFSenderQueue";
	private static final String MESSAGE_LISTENER_BEAN_NAME_PREFIX = "DIFMsgListener";
	private static final String CONN_ERROR_HANDLER_BEAN_NAME_PREFIX = "DIFJMSConnErrorHandler";
	private static final String CONN_EXCEPTION_LISTENER_BEAN_NAME_PREFIX = "DIFJMSConnExceptionListener";

	public static final String MSG_LISTENER_CONTAINER_BEAN_NAME_PREFIX = "DIFMsgListenerContainer";
	public static final String RECEIVER_CONN_FACTORY_BEAN_NAME_PREFIX = "DIFReceiverConnFactory";
	public static final String SENDER_CONN_FACTORY_BEAN_NAME_PREFIX = "DIFSenderConnFactory";
	public static final String SENDER_CACHING_CONN_FACTORY_BEAN_NAME_PREFIX = "DIFSenderCachingConnFactory";
	public static final String JMS_PRODUCER_TEMPLATE_BEAN_NAME_PREFIX = "DIFJMSProducerTemplate";

	public static final SpringJMSBeanFactory SPRING_JMS_BEAN_FACTORY = new SpringJMSBeanFactory();

	private SpringJMSBeanFactory() {

	}

	/**
	 * Register Destination object
	 * 
	 * @param endPoint
	 * @param queueName
	 * @param queueBeanNamePrefix
	 * @return
	 * @throws Exception
	 */
	private Destination registerDestinationBean(EndPoint endPoint,
			String queueName, String queueBeanNamePrefix) throws Exception {
		Destination destinationBean = null;
		try {
			GenericBeanDefinition destination = prePareGenericBeanDefinition(MQ_QUEUE_CLASS_NAME);

			MutablePropertyValues destinationPropValues = new MutablePropertyValues();
			destinationPropValues.add("baseQueueName", queueName);
			//destinationPropValues.add("targetClient", "1");

			destination.setPropertyValues(destinationPropValues);

			logInfo("MQ Queue Details :" + destinationPropValues,
					endPoint.getName());

			String beanName = constructBeanName(endPoint, queueBeanNamePrefix);

			logInfo("MQ Queue Bean Name :" + beanName, endPoint.getName());

			DIFApplicationContext.getDIFGenericAppCtx().registerBeanDefinition(
					beanName, destination);
			destinationBean = (Destination) DIFApplicationContext
					.getDIFGenericAppCtx().getBean(beanName);
		} catch (Exception ex) {
			logError("Error occured while registering Destination bean",
					endPoint.getName(), ex);
			throw new Exception(ex);
		}
		return destinationBean;
	}

	/**
	 * Register JMSConnectionErrorHandler object
	 * 
	 * @param endPoint
	 * @return
	 */
	private ErrorHandler registerJMSConnectionErrorHandlerBean(
			EndPoint endPoint, MaxFailCounter failCounter) throws Exception {

		JMSConnectionErrorHandler jmsCnnectionErrorHandlerBean = null;
		try {
			GenericBeanDefinition jmsConnectionErrorHandler = prePareGenericBeanDefinition(JMSConnectionErrorHandler.class
					.getName());

			String beanName = constructBeanName(endPoint,
					CONN_ERROR_HANDLER_BEAN_NAME_PREFIX);

			logInfo("DIFJMSConnectionErrorHandler Bean Name :" + beanName,
					endPoint.getName());

			DIFApplicationContext.getDIFGenericAppCtx().registerBeanDefinition(
					beanName, jmsConnectionErrorHandler);
			jmsCnnectionErrorHandlerBean = (JMSConnectionErrorHandler) DIFApplicationContext
					.getDIFGenericAppCtx().getBean(beanName);

			// save endpoint reference
			jmsCnnectionErrorHandlerBean.setEndPoint(endPoint);
			jmsCnnectionErrorHandlerBean.setFailCounter(failCounter);
		} catch (Exception ex) {
			logError(
					"Error occured while registering JMSConnectionErrorHandler bean",
					endPoint.getName(), ex);
			throw new Exception(ex);
		}
		return jmsCnnectionErrorHandlerBean;
	}

	/**
	 * Register JMS producer template
	 * 
	 * @param endPoint
	 * @param failCounter
	 * @throws Exception
	 */
	public void registerJMSProducerTemplateBean() throws Exception {
		try {
			ConnectionFactory connectionFactoryBean = registerConnectionFactoryBean(
					endPoint, SENDER_CONN_FACTORY_BEAN_NAME_PREFIX);

			CachingConnectionFactory senderCachingConnectionFactoryBean = registerSenderCachingConnectionFactoryBean(
					endPoint, connectionFactoryBean, failCounter);

			Destination destinationBean = registerDestinationBean(endPoint,
					endPoint.getMqProtocol().getQueueName(),
					SENDER_QUEUE_BEAN_NAME_PREFIX);

			GenericBeanDefinition jmsTemplate = prePareGenericBeanDefinition(JmsTemplate.class
					.getName());

			MutablePropertyValues jmsTemplateProperties = prepareJMSTemplateProperties(
					senderCachingConnectionFactoryBean, destinationBean,
					endPoint);
			jmsTemplate.setPropertyValues(jmsTemplateProperties);

			String beanName = constructBeanName(endPoint,
					JMS_PRODUCER_TEMPLATE_BEAN_NAME_PREFIX);

			logInfo("JMSProducerTemplate Bean Name :" + beanName,
					endPoint.getName());

			DIFApplicationContext.getDIFGenericAppCtx().registerBeanDefinition(
					beanName, jmsTemplate);
		} catch (Exception ex) {
			logError(
					"Error occured while registering JMS ProducerTemplate bean",
					endPoint.getName(), ex);
			throw new Exception(ex);
		}
	}

	/**
	 * Prepare JMSTemplate properties
	 * 
	 * @param endPoint
	 * @param failCounter
	 * @return
	 */
	private MutablePropertyValues prepareJMSTemplateProperties(
			CachingConnectionFactory senderCachingConnectionFactoryBean,
			Destination destinationBean, EndPoint endPoint) throws Exception {
		MutablePropertyValues jmsTemplateProperties = null;
		try {
			jmsTemplateProperties = new MutablePropertyValues();
			jmsTemplateProperties.add("connectionFactory",
					senderCachingConnectionFactoryBean);

			jmsTemplateProperties.add("defaultDestination", destinationBean);

			logInfo("JMSTemplate Details :" + jmsTemplateProperties,
					endPoint.getName());
		} catch (Exception ex) {
			logError(
					"Error occured while preparing property values for JMS ProducerTemplate bean",
					endPoint.getName(), ex);
			throw new Exception(ex);
		}
		return jmsTemplateProperties;
	}

	/**
	 * Register ConnectionFactory
	 * 
	 * @param endPoint
	 * @param connectionFactory
	 * @param failCounter
	 * @return
	 * @throws Exception
	 */
	private CachingConnectionFactory registerSenderCachingConnectionFactoryBean(
			EndPoint endPoint, ConnectionFactory connectionFactory,
			MaxFailCounter failCounter) throws Exception {
		CachingConnectionFactory cachingConnectionFactoryBean = null;
		try {
			MutablePropertyValues cachingConnectionFactoryProperties = prepareSenderConnectionFactoryProperties(
					endPoint, connectionFactory, failCounter);
			GenericBeanDefinition cachingConnectionFactory = prePareGenericBeanDefinition(CachingConnectionFactory.class
					.getName());

			cachingConnectionFactory
					.setPropertyValues(cachingConnectionFactoryProperties);

			String beanName = constructBeanName(endPoint,
					SENDER_CACHING_CONN_FACTORY_BEAN_NAME_PREFIX);

			logInfo("SenderCachingConnectionFactory Bean Name :" + beanName,
					endPoint.getName());

			DIFApplicationContext.getDIFGenericAppCtx().registerBeanDefinition(
					beanName, cachingConnectionFactory);
			cachingConnectionFactoryBean = (CachingConnectionFactory) DIFApplicationContext
					.getDIFGenericAppCtx().getBean(beanName);
		} catch (Exception ex) {
			logError(
					"Error occured while registering CachingConnectionFactory bean",
					endPoint.getName(), ex);
			throw new Exception(ex);
		}
		return cachingConnectionFactoryBean;
	}

	/**
	 * Prepare ConnectionFactory properties
	 * 
	 * @param endPoint
	 * @param failCounter
	 * @return
	 */
	private MutablePropertyValues prepareSenderConnectionFactoryProperties(
			EndPoint endPoint, ConnectionFactory connectionFactory,
			MaxFailCounter failCounter) throws Exception {
		MutablePropertyValues cachingConnectionFactoryPropValues = null;
		try {
			cachingConnectionFactoryPropValues = new MutablePropertyValues();
			cachingConnectionFactoryPropValues.add("targetConnectionFactory",
					connectionFactory);

			cachingConnectionFactoryPropValues.add("sessionCacheSize", endPoint
					.getMqProtocol().getMaxThreadCount());

			cachingConnectionFactoryPropValues
					.add("reconnectOnException", true);

			ExceptionListener exceptionListener = registerJMSConnectionExceptionListenerBean(
					endPoint, failCounter);

			cachingConnectionFactoryPropValues.add("exceptionListener",
					exceptionListener);

			logInfo("CachingConnectionFactory Details :"
					+ cachingConnectionFactoryPropValues, endPoint.getName());

		} catch (Exception ex) {
			logError(
					"Error occured while preparing property values for  CachingConnectionFactory bean",
					endPoint.getName(), ex);
			throw new Exception(ex);
		}
		return cachingConnectionFactoryPropValues;
	}

	/**
	 * Construct spring bean name from end point name
	 * 
	 * @param endPoint
	 * @param beanIdPrefix
	 * @return
	 */
	public String constructBeanName(EndPoint endPoint, String beanIdPrefix) {
		StringBuilder queueBeanId = new StringBuilder(beanIdPrefix);
		queueBeanId.append("_");
		queueBeanId.append(endPoint.getEndPointId());
		queueBeanId.append("_");
		queueBeanId.append(endPoint.getName());
		return queueBeanId.toString();
	}

	/**
	 * Prepare GenericBeanDefinition
	 * 
	 * @param beanClass
	 * @return
	 */
	private GenericBeanDefinition prePareGenericBeanDefinition(String beanClass) {
		GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
		genericBeanDefinition.setBeanClassName(beanClass);
		genericBeanDefinition.setLazyInit(false);
		genericBeanDefinition.setAbstract(false);
		genericBeanDefinition.setAutowireCandidate(true);
		genericBeanDefinition.setScope(BeanDefinition.SCOPE_PROTOTYPE);
		return genericBeanDefinition;
	}

	/**
	 * Remove bean definition from spring context
	 * 
	 * @param beanName
	 */
	public void removeBeanDefinitionFromSpringContext(String beanName,
			String endPointName) {
		try {
			logInfo("Removing bean " + beanName + " from spring context",
					endPointName);

			DIFApplicationContext.getDIFGenericAppCtx().removeBeanDefinition(
					beanName);
		} catch (Exception ex) {
			logError(
					"Error occured while removing bean definition from spring context",
					beanName, ex);
		}
	}

	/**
	 * Get JMSProducerTemplateBean from Spring context
	 * 
	 * @return
	 */
	public JmsTemplate getJMSProducerTemplateFromSpring() {
		JmsTemplate jmsTemplateBean = null;
		String beanName = 
		jmsTemplateBean = (JmsTemplate) DIFApplicationContext
				.getDIFGenericAppCtx().getBean(beanName);
		return jmsTemplateBean;
	}

	private void logError(String msg, String epName, Exception e) {
		String eMsg = "<" + epName + ">" + msg;
		System.out.println(eMsg);
	}

	private void logInfo(String msg, String epName) {
		String eMsg = "<" + epName + ">" + msg;
		System.out.println(eMsg);
	}
}

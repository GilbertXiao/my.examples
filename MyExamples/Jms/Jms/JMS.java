import javax.jms.*;
-------------------

InitialContext ctx = 
				new InitialContext(Properties);

Object ref = 
	ctx.lookup("weblogic.jms.ConnectionFactory");

TopicConnectionFactory topicConnFactory = 
										(TopicConnectionFactory)ref;

TopicConnection conn = 
			topicConnFactory.createTopicConnection();

TopicSession topicSession = 
							conn.createTopicSession
									 (boolean trans,int ack_mode);

TopicSession.AUTO_ACKNOWLEDGE
TopicSession.CLIENT_ACKNOWLEDGE
TopicSession.DUPS_OK_ACKNOWLEDGE

1)
	Topic topic = 
			(Topic)ctx.lookup("jndi_name_of_topic");

2)	
	Topic topic = 
				topicSession.createTopic
				("server_identified_name_of_topic");

TopicPublisher pub = 
		topicSession.createPublisher(topic);

Message msg = session.createTextMessage();
msg.setText(String);

pub.publish(msg);

pub.publish(Message msg,int deliveryMode,
					  int priority, long timeToLive);

1) DeliveryMode.PERSISTANT   NON_PERSISTANT
2) 0 - 9
-----------------------------------------------------

TopicSubscriber sub = 
	topicSession.createSubscriber
	(Topic dest,String msgSelector,boolean noLocal)

TopicSubscriber sub = 
		topicSession.createDurableSubscriber
		(Topic dest,String name,String msgSelector,
														boolean noLocal)


Message msg = sub.receive();
Message msg = sub.receive(3000);
Message msg = sub.receiveNoWait();

MessageListener listener = 
	instance_of_class_implementing_MessageListener

sub.setMessageListener(listener);
conn.start();


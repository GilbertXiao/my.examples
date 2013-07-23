
Object obj = ctx.lookup(factory_name);

QueueConnectionFactory queueFactory =
									(QueueConnectionFactory)obj; 
		

QueueConnection conn = 
	queueFactory.createQueueConnection()

QueueSession session = 
		conn.createQueueSession(boolean,int);
		
1) 
	Queue q = (Queue)ctx.lookup("jndi name of queue");

2) 
	Queue q = session.createQueue("jmsserver/queue_name");

---------------------------------------------------
QueueSender sender = session.createSender(Queue);
Message msg = session.createXxxMessage();
sender.send(msg);
-----------------------------------------------------

QueueReceiver receiver = 
			session.createReceiver(Queue);

conn.start();

Message msg = receiver.receive();
Message msg = receiver.receive(long millis);
Message msg = receiver.receiveNoWait();

receive.setMessageListener(MessageListener)
----------------------------------------------------

QueueBrowser browser = session.createBrowser(Queue);

Enumeration e = browser.getEnumeration();

while(e.hasMoreElements())
{
	Message msg = (Message)e.nextElement();

}

-----------------------------------------------------
TextMessage msg = session.createTextMessage();
TextMessage msg = session.createTextMessage(String);

msg.setText(String);  // sender

String str = msg.getText(String); // Receiver
-----------------------------------------------------

// only headers and properties....no body
Message msg = session.createMessage();

// for sending raw/binary data as message
BytesMessage msg  = session.createBytesMessage();


// for sending java data types written and read 
// sequentially
StreamMessage msg = session.createStreamMessage();

// for sending Serializable java Object
ObjectMessage msg = session.createObjectMessage();
msg.setObject(Object)

MapMessage msg = session.createMapMessage();

msg.setInt   (String name,int);
msg.setString(String name,String);


-----------------------------------------------------

msg.setXxxProperty(String name, Xxx value);

msg.setStringProperty(String, String);
msg.setIntProperty(String, int);

String = msg.getStringProperty(String);
int		 = msg.getIntProperty(String);

msg.clearProperties();

boolean = msg.propertyExists(String name)


set / get

Header Type		Header name
-----------		-----------

Destination		JMSDestination
int						JMSDeliveryMode
long					JMSExpiration
int						JMSPriority
String				JMSMessageID
long					JMSTimeStamp
String				JMSCorrelationID // if replying
Destination		JMSReplyTo			 // where to send Reply message
String				JMSType	
boolean				JMSRedelivered

-----------------------------------------------------

msg.setXxxProperty(String name, Xxx value);

msg.setStringProperty("category","SPORTS");

msg.setIntProperty("age", 25);






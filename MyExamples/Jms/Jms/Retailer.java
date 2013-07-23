
import javax.jms.*;
import java.io.*;
import java.util.*;
import javax.naming.*;
public class Retailer implements MessageListener
{
	private TopicConnection connect = null;
	private TopicSession session = null;
	private TopicPublisher publisher = null;
	private Topic hotDealsTopic  = null;


	public Retailer(String broker,String username,String password)
	{
		try{
		String factory_name =
			"weblogic.jms.ConnectionFactory";
		Properties p=new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
		Context ctx=new InitialContext(p);
		TopicConnectionFactory topicFactory =
									(TopicConnectionFactory)ctx.lookup(factory_name);
	
		connect = topicFactory.createTopicConnection();
		
		session  = connect.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
		hotDealsTopic = (Topic)ctx.lookup("MyJMSTopic");
		TopicSubscriber subscriber = session.createDurableSubscriber(hotDealsTopic,"HotDalestopic"); 	
		subscriber.setMessageListener(this);
		connect.start();
			}catch(Exception e ){System.out.println("Constructor--->"+e.toString());}
	}

	public void autoBuy(Message msg)
	{
		try{
		StreamMessage message=(StreamMessage)msg;
		String dealDesc = message.readString();
		String itemDesc = message.readString();
		float price = message.readFloat();
		float newprice = message.readFloat();
		TextMessage txtMsg = session.createTextMessage();
		txtMsg.setText("Hello this is response");
		Topic buyTopic =(Topic)message.getJMSReplyTo();
		publisher =session.createPublisher(buyTopic);
		txtMsg.setJMSCorrelationID("DurableSubscriber");
		publisher.publish(txtMsg,DeliveryMode.PERSISTENT,Message.DEFAULT_PRIORITY,1800000);
System.out.println("Deal successful");
		
	}catch(Exception e ){System.out.println("publishPriceQuotes--->"+e.toString());}
	}

	public static void main(String[]args) throws Exception
	{
		String broker ,username,password;
		broker = args[0];
		username = args[1];
		password = args[2];
		Retailer retailer =new Retailer(broker ,username,password);
	try{
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	while(true)
	{

System.out.println("Subscribed");
Thread.sleep(1000);
	}
  }catch(Exception e){System.out.println("Error Occured");}
}


	public void onMessage(Message msg)
	{
		try{
	autoBuy(msg);
	}catch(Exception e){System.out.println("Exception e"+e.toString());}
	}
}


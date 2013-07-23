import javax.jms.*;
import java.io.*;
import java.util.*;
import javax.naming.*;
public class Wholesaler implements MessageListener
{
	private TopicConnection connect = null;
	private TopicSession pubSession = null;
	private TopicSession subSession = null;
	private TopicPublisher publisher = null;
	private TopicSubscriber subscriber = null;
	private Topic hotDealsTopic  = null;
	private TemporaryTopic buyOrdersTopic = null;

	public Wholesaler(String broker,String username,String password)
	{try{
		String factory_name =
			"weblogic.jms.ConnectionFactory";
		Properties p=new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
		Context ctx=new InitialContext(p);
		TopicConnectionFactory topicFactory =
									(TopicConnectionFactory)ctx.lookup(factory_name);
	
		connect = topicFactory.createTopicConnection();
		pubSession  = connect.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
		subSession  = connect.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
		hotDealsTopic = (Topic)ctx.lookup("MyJMSTopic");
		publisher =pubSession.createPublisher(hotDealsTopic);
		buyOrdersTopic = subSession.createTemporaryTopic();
		subscriber = subSession.createSubscriber(buyOrdersTopic);
		subscriber.setMessageListener(this);
		connect.start();
			}catch(Exception e ){System.out.println("Constructor--->"+e.toString());}
	}

	public void publishPriceQuotes(String dealsDesc, String username,String item, float price,float newprice)
	{try{
		String deals = dealsDesc;
		String itemName= item;
		float prices = price;
		float newprices = newprice ; 
		StreamMessage message = pubSession.createStreamMessage();
		message.writeString(deals);
		message.writeString(itemName);
		message.writeFloat(prices);
		message.writeFloat(newprices);
		message.setStringProperty("username",username);
		message.setStringProperty("ItemDesc",item);
		message.setJMSReplyTo(buyOrdersTopic);
		publisher.publish(message,DeliveryMode.PERSISTENT,Message.DEFAULT_PRIORITY,1800000);
	}catch(Exception e ){System.out.println("publishPriceQuotes--->"+e.toString());}
	}

	public static void main(String[]args) throws Exception
	{
		String broker ,username,password;
		broker = args[0];
		username = args[1];
		password = args[2];
		Wholesaler wholesaler =new Wholesaler(broker ,username,password);
	try{
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	while(true)
	{
	String dealsDesc=br.readLine();
	StringTokenizer tokenizer=new StringTokenizer(dealsDesc,",");
	String item = tokenizer.nextToken();
	float price = Float.valueOf(tokenizer.nextToken().trim()).floatValue();
  float newprice = Float.valueOf(tokenizer.nextToken().trim()).floatValue();

  wholesaler.publishPriceQuotes(dealsDesc,username,item,price,newprice);
	}
  }catch(Exception e){System.out.println("Error Occured");}
}


	public void onMessage(Message msg)
	{
		try{
		TextMessage txtMsg=(TextMessage)msg;
		String txt = txtMsg.getText();
		System.out.println("The message Received"+txt);
	  }catch(Exception e){System.out.println("Exception e"+e.toString());}
	}
}

package mr.shravan.examples.jms;
import java.util.*;
import javax.jms.*;
import javax.naming.*;

class MyListener implements MessageListener
{
	public void onMessage(Message msg) 
	{
		try
		{

		if(msg instanceof TextMessage)
		{
			TextMessage txtMsg = (TextMessage)msg;
			try
			{
				String str = txtMsg.getText();
				System.out.println(str);
			}
			catch(JMSException e)
			{
				System.out.println("Error....");
				System.out.println(e.getMessage());
			}
		}
		else if(msg instanceof ObjectMessage)

		{
			System.out.println("Employee Created...");
			ObjectMessage o = (ObjectMessage)msg;
//			EmployeeVO newEmp = (EmployeeVO)o.getObject();

//			System.out.println(newEmp.empno + " " + 
//												 newEmp.ename + " " + 
//												 newEmp.sal );
		}

		}
		catch(Exception e)
		{}

	}
}



public class TopicSubscriberDemo1 implements MessageListener
{
	public static void main(String args[])
	throws Exception
	{
		String factory_name =
			"weblogic.jms.ConnectionFactory";

		Properties p = new Properties();

		p.put(Context.INITIAL_CONTEXT_FACTORY,
			"weblogic.jndi.WLInitialContextFactory");

		InitialContext ctx = new InitialContext(p);

		Object obj = ctx.lookup(factory_name);

		TopicConnectionFactory topicFactory =
									(TopicConnectionFactory)obj;
		
		TopicConnection conn =
							topicFactory.createTopicConnection();
		
		conn.start();

		TopicSession session =
			conn.createTopicSession
			 (false,TopicSession.AUTO_ACKNOWLEDGE);

		Topic _topic = 
			session.createTopic("MyJMSServer/KanthJMSTopic");

		TopicSubscriber subscriber = 
			session.createSubscriber(_topic);

		boolean z = true;
			while(z)
		{
				try{
          	System.out.println("----");
						Thread.sleep(1000);
						}
						catch(Exception e){}

		}

		session.close();
		conn.close();
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

/*
weblogic.jms.extensions.

		JMSHelper.createPermanentTopicAsync
				(	javax.naming.Context,
					String jmsServerName,
				  String topicName,
				  String jndiName
				)
*/
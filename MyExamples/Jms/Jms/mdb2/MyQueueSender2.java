
import emp.*;

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
				System.out.println("Employee Not Created. Reason is : ");
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

			EmployeeVO newEmp = 
					(EmployeeVO)o.getObject();

			System.out.println(newEmp.empno + "\t" + 
												 newEmp.ename + "\t" + 
												 newEmp.sal );
		}

		}
		catch(JMSException e)
		{}

	}
}


public class MyQueueSender2
{
	public static void main(String args[]) throws Exception
	{
		String factory_name =
			"weblogic.jms.ConnectionFactory";

		String queue_name = "MyJMSQueueJNDI";

		Properties p = new Properties();

		p.put(Context.INITIAL_CONTEXT_FACTORY,
			"weblogic.jndi.WLInitialContextFactory");

		InitialContext ctx = new InitialContext(p);

		Object obj = ctx.lookup(factory_name);

		QueueConnectionFactory cf =
								(QueueConnectionFactory)obj;

		QueueConnection conn = 
						cf.createQueueConnection();

		conn.start();

		QueueSession session =
							conn.createQueueSession
			 (false,QueueSession.AUTO_ACKNOWLEDGE);

		TemporaryQueue tempQ = 
					session.createTemporaryQueue();

		System.out.println("Temp Q Created");

		QueueReceiver rx = 
					session.createReceiver(tempQ);

		rx.setMessageListener(new MyListener());

		Queue q = (Queue)ctx.lookup(queue_name);

		QueueSender sender = session.createSender(q);

		ObjectMessage objMsg = 
						session.createObjectMessage();

		EmployeeVO empValue = 
				new EmployeeVO
				(-1,args[0],Float.parseFloat(args[1]));

		objMsg.setObject(empValue);

		objMsg.setJMSReplyTo(tempQ);

		sender.send(objMsg);
		System.out.println("Object MSG Sent");
		System.out.println();
				
		Thread.currentThread().join();
	}
}
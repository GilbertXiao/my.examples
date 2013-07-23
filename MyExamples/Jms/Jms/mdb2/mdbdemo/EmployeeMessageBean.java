
package mdbdemo;

import emp.*;
import javax.ejb.*;
import javax.jms.*;
import javax.naming.*;
import javax.rmi.*;

public class EmployeeMessageBean
implements MessageDrivenBean, MessageListener 
{
  private MessageDrivenContext mCtx;

  public void ejbActivate() {}
  public void ejbPassivate(){}
  public void ejbRemove()   {}

  public void ejbCreate() throws CreateException
  {}

  public void setMessageDrivenContext
  (MessageDrivenContext ctx)
  {
    mCtx = ctx;
  }

  public void onMessage(Message msg)
  {
		QueueConnectionFactory cf = null;
		QueueConnection conn      = null;
		QueueSession session      = null;
		QueueSender sender        = null;

		try
		{
			if(msg instanceof ObjectMessage)
			{
				ObjectMessage objMsg = (ObjectMessage)msg;

				EmployeeVO empValue = 
							(EmployeeVO) objMsg.getObject();

				Context ctx = new InitialContext();

			Object obj = 
				ctx.lookup("java:comp/env/ejb/employee");

			EmployeeHome h = (EmployeeHome)
			PortableRemoteObject.narrow(obj,emp.EmployeeHome.class);

			Employee myEmp = 
					h.create(empValue.ename,empValue.sal);

			EmployeeVO newEmp = myEmp.getValue();

		cf = (QueueConnectionFactory)
			ctx.lookup("weblogic.jms.ConnectionFactory");
		
		conn = cf.createQueueConnection();

		session = 
			conn.createQueueSession(false,QueueSession.AUTO_ACKNOWLEDGE);

		Destination dest = msg.getJMSReplyTo();

		sender = session.createSender( (Queue)dest );

		ObjectMessage replyMsg = 
					session.createObjectMessage();

		replyMsg.setObject(newEmp);

		sender.send(replyMsg);			
		System.out.println("Message Replied");

			}
		else 
				System.out.println("Message in Unknown Format");
		}
		catch(Exception e)
		{
			//mCtx.setRollbackOnly();

			String str = e.getMessage();
			
			try
			{	
				TextMessage textMsg = 
						session.createTextMessage(str);

				sender.send(textMsg);
			}
			catch(JMSException ex)
			{}
		}
	}
}
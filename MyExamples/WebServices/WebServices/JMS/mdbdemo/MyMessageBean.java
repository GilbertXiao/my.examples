package mdbdemo;

import javax.ejb.*;
import javax.jms.*;

public class MyMessageBean
implements MessageDrivenBean, MessageListener 
{
  private MessageDrivenContext mdbCtx;

  public void ejbActivate() {}
  public void ejbPassivate(){}
  public void ejbRemove()   {}

  public void ejbCreate() throws CreateException
  {}

  public void setMessageDrivenContext
  (MessageDrivenContext ctx)
  {
    mdbCtx = ctx;
  }

  public void onMessage(Message msg)
  {
		try
		{

			if(msg instanceof ObjectMessage)
			{
				ObjectMessage objMsg = (ObjectMessage)msg;
				String str= (String)objMsg.getObject();
				System.out.println(str);
			}

			else if(msg instanceof TextMessage)
			{
				TextMessage txtMsg = (TextMessage)msg;
				String str= txtMsg.getText();
				System.out.println(str);
			}
			else 
				System.out.println("Message in Unknown Format");
		}
		catch(JMSException e)
		{}
	}
}
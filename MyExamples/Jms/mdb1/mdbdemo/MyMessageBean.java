
package mdbdemo;

import javax.ejb.*;
import javax.jms.*;

public class MyMessageBean
implements MessageDrivenBean, MessageListener 
{
	private MessageDrivenContext mCtx;

  public void ejbCreate() throws CreateException
  {}

  public void ejbRemove()  
	{}

  public void setMessageDrivenContext
  (MessageDrivenContext ctx)
  {
    mCtx = ctx;
  }

  public void onMessage(Message msg)
  {
		try{
			if(msg instanceof TextMessage)
			{
				TextMessage txtMsg = (TextMessage)msg;
				String str = txtMsg.getText();
				System.out.println(str);
			}
			else 
				System.out.println("Message in Unknown Format");
		}
		catch(JMSException e)
		{
			System.out.println("ERROR...");
			e.printStackTrace();
		}
	}
}
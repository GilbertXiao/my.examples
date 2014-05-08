package mr.shravan.examples.mq;

import java.io.File;
import java.io.FileInputStream;

import com.ibm.mq.constants.MQConstants;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;

public class MQHelper {
	
//	protected static String hostName = "vsstawin-001";
//	protected static int port = 1414;
//	protected static String qManager = "IFW_Q_Manager";
//	protected static String qName = "IFW_Q";
//	protected static String channel = "IFW_Channel";
	
	protected static String hostName = "intg-rhel3";
	protected static int port = 1420;
	protected static String userName = null;
	protected static String pwd = null;
	protected static String qManager = "INTG_QUEUE_MANAGER";
	protected static String qName = "INTG_OUT";
	protected static String qMsg = "Hello World!";
	protected static String channel = "ILS_SRVCON_CHANNEL";
	
	public static void main(String args[]) {
		String msg = "This is the msg to be delivered";
		putMessage(msg);
//		String retMsg = getMessage();
//		System.out.println("Successfully sent and retrived the msg. The msg is: "+retMsg);
	}

	public static void putMessage(String qMsgString) {
		MQQueue myQ = null;
		MQQueueManager qMgr = null;
		try {
			qMgr = getQManager();
			int openOptions = 
	        	 MQConstants.MQOO_INPUT_AS_Q_DEF 
	               | MQConstants.MQOO_OUTPUT 
	               | MQConstants.MQOO_INQUIRE 
	               | MQConstants.MQOO_SET_IDENTITY_CONTEXT; 
			myQ = qMgr.accessQueue(qName, openOptions);
			
			
			
			MQPutMessageOptions pmo = new MQPutMessageOptions(); 
			 pmo.options = 
	        	 MQConstants.MQPMO_LOGICAL_ORDER 
	               | MQConstants.MQPMO_SET_IDENTITY_CONTEXT 
	               | MQConstants.MQPMO_SYNCPOINT; 
			 
			 MQMessage qMsg = new MQMessage();
				qMsg.format = MQConstants.MQFMT_STRING; 
				qMsg.messageFlags = MQConstants.MQMF_SEGMENTATION_ALLOWED; 
			int i = 0; 
	         
	         File f = new File("C:/Users/stalupula/Documents/0Trash/Samples/DO_1MB.xml"); 
//	         File f = new File("C:/Users/stalupula/Documents/0Trash/Samples/VERIFY_EJB_CHANNEL.xml"); 

	         FileInputStream fileInput = new FileInputStream(f); 

	         while ((i = fileInput.read()) != -1) { 
	        	 qMsg.write(i); 
	         } 

	         myQ.put(qMsg, pmo); 

	         qMgr.commit(); 

		} catch (MQException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				myQ.close();
				qMgr.disconnect();
			} catch (MQException e) {
				e.printStackTrace();
			}
		}
	}
	public static void putMessage2(String qMsgString) {
		MQQueue myQ = null;
		MQQueueManager qMgr = null;
		try {
			qMgr = getQManager();
			int openOptions = MQConstants.MQOO_INPUT_AS_Q_DEF | MQConstants.MQOO_OUTPUT;
			myQ = qMgr.accessQueue(qName, openOptions);
			MQMessage qMsg = new MQMessage();
			qMsg.writeUTF(qMsgString);
			MQPutMessageOptions pmo = new MQPutMessageOptions();
			myQ.put(qMsg, pmo);
		} catch (MQException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				myQ.close();
				qMgr.disconnect();
			} catch (MQException e) {
				e.printStackTrace();
			}
		}
	}

	protected static MQQueueManager getQManager() throws MQException {
		MQQueueManager qMgr;
		MQEnvironment.hostname = hostName;
		MQEnvironment.port = port;
		if (userName != null) {
			MQEnvironment.userID = userName;
		}
		if (pwd != null) {
			MQEnvironment.password = pwd;
		}

		MQEnvironment.channel = channel;
		MQEnvironment.properties.put(MQConstants.TRANSPORT_PROPERTY,
				MQConstants.TRANSPORT_MQSERIES);// Connection
		qMgr = new MQQueueManager(qManager);
		return qMgr;
	}

	public static String getMessage() {
		MQQueue myQ = null;
		MQQueueManager qMgr = null;
		try {
			qMgr = getQManager();

			int openOptions = MQConstants.MQOO_INPUT_AS_Q_DEF | MQConstants.MQOO_OUTPUT;

			myQ = qMgr.accessQueue(qName, openOptions);

			MQMessage retrievedMessage = new MQMessage();

			MQGetMessageOptions gmo = new MQGetMessageOptions();

			myQ.get(retrievedMessage, gmo);

			String msgText = retrievedMessage.readUTF();

			return msgText;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				myQ.close();
				qMgr.disconnect();
			} catch (MQException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
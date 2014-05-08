package mr.shravan.examples.mq.segmentation;


import java.io.File;
import java.io.FileInputStream;

import mr.shravan.examples.mq.MQHelper;

import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.MQConstants;


public class Seg extends MQHelper { 

   private static MQQueueManager qMgr; 
   private static MQQueue myQueue; 

   public Seg() { 

   } 

   public static void main(String args[]) { 

      try { 

         qMgr = new MQQueueManager(qManager); 

         int openOptions = 
        	 MQConstants.MQOO_INPUT_AS_Q_DEF 
               | MQConstants.MQOO_OUTPUT 
               | MQConstants.MQOO_INQUIRE 
               | MQConstants.MQOO_SET_IDENTITY_CONTEXT; 
         
         myQueue = qMgr.accessQueue(qName, openOptions); 

         MQPutMessageOptions pmo = new MQPutMessageOptions(); 
         pmo.options = 
        	 MQConstants.MQPMO_LOGICAL_ORDER 
               | MQConstants.MQPMO_SET_IDENTITY_CONTEXT 
               | MQConstants.MQPMO_SYNCPOINT; 

         MQMessage m = new MQMessage(); 
         m.format = MQConstants.MQFMT_STRING; 
         m.messageFlags = MQConstants.MQMF_SEGMENTATION_ALLOWED; 

         int i = 0; 
         
         File f = new File("c:/sw/sshkey.7z"); 

         FileInputStream fileInput = new FileInputStream(f); 

         while ((i = fileInput.read()) != -1) { 
            m.write(i); 
         } 

         myQueue.put(m, pmo); 

         qMgr.commit(); 

      } catch (Exception e) { 
         e.printStackTrace(); 
      } 
   } 

}

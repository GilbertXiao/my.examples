public class MultiThreading {
  public static void main(String[] args) {
	
	int tdCnt= 3;
	Thread[] threads = new Thread[tdCnt];
	
	for(int i=0; i<tdCnt; i++)
	{
		try {
			threads[i] = new CommMgrClientThread("Thread-"+i);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	for(int i=0; i<tdCnt; i++)
	{
		try {
			threads[i].start();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	for(int i=0; i<tdCnt; i++)
	{
		try {
			threads[i].join();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	System.out.println("Main Finished.");
  }
} 
class CommMgrClientThread extends Thread
{

	public CommMgrClientThread(String name)
	{
		super(name);
	}
	public void run() {

        System.out.println("Reading::"+getName());
		if(getName().equals("Thread2")){
			try{
			Thread.sleep(200);
			}
			catch(Exception e)
			{
			}
		}

      }
}
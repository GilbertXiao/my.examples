package mr.shravan.examples.velocity;
public class VMMultiThreading {
  public static void main(String[] args) {
	
	int tdCnt= 3;
	Thread[] threads = new Thread[tdCnt];
	
	for(int i=0; i<tdCnt; i++)
	{
		try {
			threads[i] = new VMThread("Thread-"+i);
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
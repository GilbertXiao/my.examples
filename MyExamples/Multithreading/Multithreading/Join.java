public class Join {
  public static void main(String[] args) {
    
	Thread t1 = new Thread() {

      public void run() {
        System.out.println("Reading");
      }

    };

	Thread t2 = new Thread() {
      public void run() {
        System.out.println("Reading2");
        System.out.println("Thread Finished2.");
      }
    };

    System.out.println("Starting");
    
	t1.start();
	t2.start();

	System.out.println("Joining");
    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
		e.printStackTrace();
    }
    System.out.println("Main Finished.");
  }
} 
class CommMgrClientThread extends Thread
{
	String name;
	
	public void run() {
        System.out.println("Reading::"+getName());
      }
}
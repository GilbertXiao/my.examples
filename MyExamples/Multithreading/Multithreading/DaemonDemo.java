
class DaemonThread extends Thread
{
	public void run()
	{
		while(true)
		{
			System.out.println("I am daemon");
			yield();
		}
	}
}
class DaemonDemo 
{
	public static void main(String[] args) 
	{
		DaemonThread t = new DaemonThread();
		t.setDaemon( true );
		t.start();

		for(int i=0; i<5; i++)
		{
			System.out.println("In main");
			Thread.currentThread().yield();
		}
	}
}
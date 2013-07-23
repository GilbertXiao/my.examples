
class MyTarget implements Runnable
{
	public void run()
	{
		while(true)
		{
			System.out.println
				(Thread.currentThread().getName());
		}
	}
}
class ThreadDemo 
{
	public static void main(String[] args) 
	{
		Runnable r = new MyTarget();
		Thread t = new Thread( r );
		t.setName("My Thread");
		t.start();
	}
}
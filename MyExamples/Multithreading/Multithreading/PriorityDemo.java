
class MyThread extends Thread
{
	public void run()
	{
		while(true)
			System.out.println( getName() );
	}
}

class PriorityDemo
{
	public static void main(String[] args) 
	{
		MyThread t1 = new MyThread();
		t1.setName("First");

		MyThread t2 = new MyThread();
		t2.setName("\tSecond");

		t1.setPriority(Integer.parseInt(args[0]));
		t2.setPriority(Integer.parseInt(args[1]));

		t1.start();
		t2.start();
	}
}
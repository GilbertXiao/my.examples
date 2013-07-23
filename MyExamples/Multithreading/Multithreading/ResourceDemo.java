
class Resource
{
	private int data;

	public void setData(int value)
	{
		synchronized ( this )
		{
			data = value;
			try{
				Thread.sleep(1000);
			}
			catch(InterruptedException e){}
			System.out.println(Thread.currentThread().getName() + " : "  + data);
		}
	}
}

class ResourceThread extends Thread
{
	int a;
	Resource r;
	
	ResourceThread(int a, Resource r)
	{
		this.a = a;
		this.r = r;
	}

	public void run()
	{
		this.r.setData( a );
	}
}

class ResourceDemo 
{
	public static void main(String[] args) 
	throws InterruptedException
	{
		Resource r = new Resource();

		ResourceThread t1 = new ResourceThread(10,r);
		ResourceThread t2 = new ResourceThread(25,r);

		t1.setName("First");
		t2.setName("Second");

		t1.start();
		t2.start();

		t1.join();
		t2.join();
	}
}
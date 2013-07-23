class JoinDemo 
{
	public static void main(String[] args) 
	throws InterruptedException
	{
		Thread t = Thread.currentThread();
		System.out.println("Before Join");
		t.join();
		System.out.println("After  Join");
	}
}

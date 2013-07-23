class CurrentThreadDemo 
{
	public static void main(String[] args) 
	{
		Thread t = Thread.currentThread();

		System.out.println("Name     : "+t.getName());
		System.out.println("Priority : "+t.getPriority());		

		t.setName("MyThread");
		t.setPriority(7);
		System.out.println();

		System.out.println("Name     : "+t.getName());
		System.out.println("Priority : "+t.getPriority());		
	}
}

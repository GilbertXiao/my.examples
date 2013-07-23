class Goods
{
	private int data;
	private boolean canProduce = true;

	synchronized void put(int value)
	{
		if( !canProduce )
		{
			try{
				this.wait();
			}
			catch(InterruptedException e){}
		}		
		data = value;
		System.out.println("PUT : " + data);
		canProduce = false;
		notify();
	}
	
	synchronized int get()
	{
		if(canProduce)
		{
			try{
				wait();
			}
			catch(InterruptedException e){}
		}
				
		System.out.println("\tGET : " + data);
		canProduce = true;
		notify();
		return data;
	}
}

class Producer extends Thread
{
	Goods g;
	Producer( Goods g )
	{
		this.g = g;
	}
	public void run()
	{
		int i = 1;

		while(true)
		{
			g.put(i++);
		}
	}
}

class Consumer extends Thread
{
	Goods g;
	Consumer(Goods g)
	{
		this.g = g;
	}
	public void run()
	{
		while(true)
		{
			g.get();
		}
	}
}

class ProducerConsumerDemo
{
	public static void main(String[] args) throws InterruptedException
	{
		Goods g = new Goods();

		Producer p = new Producer(g);
		Consumer c = new Consumer(g);
		
		p.start();
		c.start();

		p.join();
		c.join();
	}
}

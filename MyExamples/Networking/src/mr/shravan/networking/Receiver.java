package mr.shravan.networking;

import java.io.*;
import java.net.*;

class Receiver
{
	public static void main(String args[]) throws Exception
	{
		byte b[] = new byte[1024];

		DatagramPacket dp = 
			new DatagramPacket(b,b.length);
	
		DatagramSocket ds = 
			new DatagramSocket(4040);

		while(true)
		{
			ds.receive(dp);

			byte buf[] = dp.getData();
			int	 n		 = dp.getLength();

			System.out.println
				( new String( buf, 0, n ) );

			b = new byte[1024];
			dp = new DatagramPacket(b,b.length);
		}
	}
}
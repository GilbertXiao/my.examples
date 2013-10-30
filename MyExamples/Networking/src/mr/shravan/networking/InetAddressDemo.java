package mr.shravan.networking;

import java.net.*;

class InetAddressDemo 
{
	public static void main(String[] args) 
	throws UnknownHostException
	{
		InetAddress myAddr = 
			InetAddress.getByName( "ma-lt6255.us.manh.com" );

		System.out.println
			("Name : " + myAddr.getHostName());

		System.out.println
			("Addr : " + myAddr.getHostAddress());
	}
}

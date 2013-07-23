import java.net.*;

class InetAddressDemo 
{
	public static void main(String[] args) 
	throws UnknownHostException
	{
		InetAddress myAddr = 
			InetAddress.getByName( args[0] );

		System.out.println
			("Name : " + myAddr.getHostName());

		System.out.println
			("Addr : " + myAddr.getHostAddress());
	}
}


import java.net.*;

class IPAddressDemo
{
	public static void main(String[] args) 
	throws UnknownHostException
	{
		InetAddress addr = 
			InetAddress.getByName(args[0]);

		System.out.println
			("Name = " + addr.getHostName());

		System.out.println
			("Addr = " + addr.getHostAddress());
	}
}
import java.io.*;
import java.net.*;

class Sender
{
	public static void main(String args[]) throws Exception
	{
		InetAddress destHost=InetAddress.getLocalHost();
		int destPort = 4040;

		DatagramSocket ds = new DatagramSocket();

		DataInputStream console = 
			new DataInputStream(System.in);

		String s = console.readLine();

		while(true)
		{
			byte b[] = s.getBytes();

			DatagramPacket dp = new DatagramPacket
					 (b, b.length, destHost, destPort);

			ds.send(dp);
			s = console.readLine();
		}
	}
}
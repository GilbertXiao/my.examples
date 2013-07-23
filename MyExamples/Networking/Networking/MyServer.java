import java.io.*;
import java.net.*;

class MyServer 
{
	public static void main(String[] args) throws Exception
	{
		ServerSocket ss	= new ServerSocket(1122);

		while(true)
		{
			Socket s = ss.accept();
			new CommThread( s );		
		}
	}
}
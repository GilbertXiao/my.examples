import java.io.*;
import java.net.*;

class URLDemo
{
	public static void main(String args[]) throws Exception
	{
		URL u = new URL(args[0]);
		InputStream is = u.openStream();
		int x = is.read();

		while(x != -1)
		{
			System.out.print( (char)x );
			x = is.read();
		}

		is.close();
	}
}
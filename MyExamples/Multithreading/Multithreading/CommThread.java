import java.io.*;
import java.net.*;

class CommThread extends Thread
{
	Socket s;

	CommThread(Socket t)
	{
		s = t;
		this.start();
	}

	public void run()
	{
		try{
			OutputStream os = s.getOutputStream();
			InputStream is	= s.getInputStream();

			PrintStream ps = new PrintStream(os,true);
			DataInputStream dis = new DataInputStream(is);

			ps.println( "Welcome" );
			System.out.println( dis.readLine() );
		
			dis.close();
			is.close();
			ps.close();
			os.close();

			s.close();
		}
		catch(Exception e)
		{}
	}
}

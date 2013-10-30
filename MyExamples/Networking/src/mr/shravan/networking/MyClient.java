package mr.shravan.networking;

import java.io.*;
import java.net.*;

class MyClient
{
	public static void main(String[] args) throws Exception
	{
		Socket cs = new Socket("localhost",1122);

		InputStream  is = cs.getInputStream();
		OutputStream os = cs.getOutputStream();
		
		DataInputStream dis =	new DataInputStream(is);
		PrintStream ps=new PrintStream(os,true);
		
		System.out.println( dis.readLine() );
		
		DataInputStream console = 
			new DataInputStream( System.in );

		ps.println( console.readLine() );

		console.close();
		ps.close();
		os.close();
		dis.close();
		is.close();
		cs.close();	
	}
}

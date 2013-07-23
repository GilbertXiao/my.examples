
import java.io.*;

class MyFilter implements FilenameFilter
{
	public boolean accept(File a,String b)
	{
		File   temp = new File( a , b );

		return 
			temp.isFile() && b.endsWith(".java");
	}
}

class ListDemo 
{
	public static void main(String[] args) 
	{
		File f = new File(args[0]);
		if( f.isDirectory() )
		{
			FilenameFilter fm = new MyFilter();
			String list[] = f.list( fm );

			for(int i=0; i<list.length; i++)
			{
				File t = new File( f, list[i] );
				if( t.isFile() )
					System.out.println(list[i] + " is a File");
				else if( t.isDirectory() )
					System.out.println(list[i] + " is a Dir");
			}
		}
		else
		{
			System.out.println(args[0] + " is not a Dir");
		}
	}
}
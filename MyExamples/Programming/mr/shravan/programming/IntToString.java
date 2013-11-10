package mr.shravan.programming;

/**
Write a function that converts an int into its alpha-numeric equivalent represented as a null terminated string. The function should accept an int as input and return a string as output. For instance, calling the function with an int value of 324 would return a null terminated string containing "324". Ensure that your function checks for appropriate boundary conditions and edge cases. Assume you cannot use any standard libraries (for example, no itoa or sprintf).
*/
public class IntToString{
	public static void main(String... args)
	{
		if(args.length != 1)
		{
			System.out.println("Usage: IntToString <number>");
			return;
		}
		
		int i = new Integer(args[0]);
		
		String str = convertIntToString(i);
		System.out.println("String: "+str);
	}
	private static String convertIntToString(int i)
	{
		
		return ""+i; 
	}
}
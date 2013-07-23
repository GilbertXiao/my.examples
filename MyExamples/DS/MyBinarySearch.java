import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
public class MyBinarySearch
{
	private static int[] nos = null;
	public static void main(String[] args) throws Exception
	{
		int[] array = getNumbers();
		nos = array;
		array = bubbleSortNumbers(array);
		System.out.println("Enter the number to find");
		int no2Search = getNumberToFind();
		int index = binarySearch(0, array.length, no2Search);
		if(index > -1)
		{
			System.out.println("Your number found");
		}else{
			System.out.println("Your number is not found");
		}
	}
	private static int binarySearch(int lower,int higher,int no2Search)
	{
		System.out.println("Lower bound "+ lower);
		System.out.println("Higher Bound "+higher);
		printNumbers();
		int midIndex = (lower+higher)/2;
		if(nos[midIndex] == no2Search)
		{
			return midIndex;
		}
		else if(lower > higher)
		{
			return -1;
		}
		else {
			if(nos[midIndex] > no2Search)
			{
				return binarySearch(midIndex+1, higher, no2Search);
			} else if( nos[midIndex]<no2Search)
			{
				return binarySearch(lower,midIndex-1, no2Search);
			}
		}
		return -1;
	}
	private static int[] getNumbers() throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter number series seperated by ,");
		String series = br.readLine();
		if(series.length() == 0)
		{
				System.out.println("Usage: java MyBinarySearch ");
		}
		StringTokenizer tokens = new StringTokenizer(series, ",");
		int size=tokens.countTokens();
		int[] array = new int[size];
		int  i =0;
		while(tokens.hasMoreElements())
		{
				String token =""+ tokens.nextElement();
				array[i++] = Integer.parseInt(token);
		}
		return array;
	}
	private static int[] bubbleSortNumbers(int[] nos)
	{
		int size = nos.length;
		for(int i = 0; i < size ; size--)
		{
			 for(int j=0;j<(nos.length-1); j++)
				{
				  if(nos[j] > nos[j+1])
						{
								int n = nos[j];
								nos[j]=nos[j+1];
								nos[j+1] =n;
						}
				}
		}
		return nos;
	}
	private static int getNumberToFind() throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String numToSearch = br.readLine();
		if(numToSearch.length() == 0)
		{
				System.out.println("Enter a number to find: ");
		}
		return Integer.parseInt(numToSearch);
	}
	private static void printNumbers()
	{
		for(int i=0;i <nos.length;i++)
		{
			System.out.print(nos[i]+",");
		}
		System.out.println();
	}
}
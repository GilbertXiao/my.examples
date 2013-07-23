import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
public class SelectionSort
{
	public static void main(String[] args) throws Exception
	{
			int[] inputArray = getInputArray();
			for(int i =0;i<inputArray.length;i++)
			{
				int minPos = i;
				for(int j=0;j<inputArray.length;j++)
				{
					if(inputArray[j]<inputArray[i])
					{
						minPos = j;
					}
				}
				if(minPos > i)
				{
					int t = inputArray[i];
					inputArray[i] = inputArray[minPos];
					inputArray[minPos]=t;
				}
			}
			displayArray(inputArray);
	}
	private static void displayArray(int[] inputArray)
	{
		for(int i=0;i<inputArray.length;i++)
		{
			System.out.print(inputArray[i]+",");
		}
	}
	private static int[] getInputArray() throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter number series seperated by ,");
		String str = br.readLine();
		if(str.length() <0)
		{
			System.out.println("Enter number series seperated by ,");
		}
		StringTokenizer tokens = new StringTokenizer(str,",");
		int size = tokens.countTokens();
		int[] inputArray = new int[size];
		int i = 0;
		while(tokens.hasMoreElements())
		{
			String t = ""+tokens.nextElement();
			int n = Integer.parseInt(t);
			inputArray[i++]=n;
		}
		return inputArray;
	}
}
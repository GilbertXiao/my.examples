import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class FrequentLettersFinder
{
  public static void main(String[] args) throws IOException
  {
        String str = getString();
        int ln = str.length();
        if (ln <0)
        {
          System.out.println("Usage: java FrequentLettersFinder <Your text>");
        }
		int[] letters = new int[26];
		initializeLetters(letters);
		
		int aPos = 'a';
		char[] ip = str.toCharArray();
		for(int i=0;i<ip.length;i++)
		{
		   int c = ip[i];
		   int letterPos = c%aPos;
		   
		   letters[letterPos] += 1;
		}
		for(int i=0; i <letters.length;i++)
		{
			char c = (char)(aPos+i);
			System.out.println("Letter '"+c +"' occured "+ letters[i]+" Times");
		}
		
        
   }
   private static void initializeLetters(int[] letters)
   {
		for (int i=0;i<letters.length;i++)
		{
			letters[i]=0;
		}
   }
   private static String getString() throws IOException
   {
    System.out.println("Enter text:");
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	return br.readLine();
   }
}

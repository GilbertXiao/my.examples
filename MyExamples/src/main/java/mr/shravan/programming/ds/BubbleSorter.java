package mr.shravan.programming.ds;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

public class BubbleSorter
{
   public static void main(String[] args)
   {
        try
        {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter number series seperated by ,");
                String series = br.readLine();
                if(series.length() == 0)
                {
                        System.out.println("Usage: BubbleSorter <comma delimited number>");
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
                for(i = 0; i < size ; size--)
                {
                     for(int j=0;j<(array.length-1); j++)
                        {
                          if(array[j] > array[j+1])
                                {
                                        int n = array[j];
                                        array[j]=array[j+1];
                                        array[j+1] =n;
                                }
                        }
                }
                System.out.println("Numbers after sorting in ASC");
                for(i =0; i<array.length; i++)
                {
                        System.out.print(array[i] +",");
                }

        }catch(Exception e)
        {
                e.printStackTrace();
        }
   }
}

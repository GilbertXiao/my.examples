package mr.shravan.programming;

import java.util.*;
public class WordCounterTest {

    public static int countWords(String s) {
        /*
          Please implement this method to
          return the word count in a given String.
          Assume that the parameter String can only contain spaces and alphanumeric characters.
         */
		 StringTokenizer st = new StringTokenizer(s," ");
		 int i =0;
		 while(st.hasMoreTokens())
		 {
			st.nextToken();
			i++;
		 }
		 return i;
    }
	public static int[] retainPositiveNumbers(int[] a) {
        /*
          Please implement this method to
          return a new array with only positive numbers from the given array.
          The elements in the resulting array shall be sorted in the ascending order.
         */
		 if(a==null || a.length == 0)
			return null;
			
		 int res[] = new int[a.length];
		 int counter = 0;
		 for(int i =0; i<a.length;i++)
		 {
			if(a[i]>0){
				res[counter++]=a[i];
			}
		 }
		 return res;
    }

	public static void main(String... args)
	{
		String s = "This is simple";
		int i = countWords(s);
		System.out.println("No. of words in the given string ("+s +")are : "+i);
	}
}

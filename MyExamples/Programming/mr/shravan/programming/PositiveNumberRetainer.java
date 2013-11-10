package mr.shravan.programming;

import java.util.*;
public class PositiveNumberRetainer {

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
		 int out[] = new int[counter];
		 for (int i =0;i<counter;i++)
			{
				out[i]=res[i];
			}
		 return out;
    }

	public static void main(String... args)
	{
		
		int[] input = {1,-1,20,21,100};
		
		int[] output = retainPositiveNumbers(input);
		System.out.println("Positve Numbers in ("+Arrays.toString(input) +")are : "+Arrays.toString(output));
	}
}

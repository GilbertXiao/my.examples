package mr.shravan.programming.problems;

import java.util.Arrays;
public class RemoveDuplicatesTest {
	public static void main(String... args){
		int a[] = {2,1,2,3};
		int o[] = removeDuplicates(a);
		System.out.println("After removing the duplicates from "+Arrays.toString(a)+" to :"+Arrays.toString(o));
	}
	/*
    Please implement this method to
    remove all duplicates from the original array. Retain the order of the elements and
    always retain the first occurrence of the duplicate elements.
    For example, parameter: {2,1,2,3}, result: {2,1,3}
    */
	public static int[] removeDuplicates(int[] a) {
		 if(a.length ==0)
			return null;
		 int[] o = new int[a.length];
		 o[0] = a[0];
		 int counter = 1;
		 for(int i=1;i<a.length;i++)
		 {
				int num = a[i];
				boolean isDuplicate = doesNoExists(num,o);
				if(!isDuplicate){
					o[counter++]=num;
				}
		 }
		 int[] fArr = new int[counter];
		 
		 for (int i=0;i<counter;i++)
		 fArr[i] = o[i];
		 
		 return fArr;
    }
	public static boolean doesNoExists(int no, int[] o){
		for(int i=0;i<o.length;i++){
			if(no == o[i]){
				return true;
			}//if
		}//for
		return false;
	}

}

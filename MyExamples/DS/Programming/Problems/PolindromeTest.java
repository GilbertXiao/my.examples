public class PolindromeTest {
    
    public static boolean isPalindrome(String s) {
        /*
          Definition: A palindrome is a string that reads the same forward and backward.
          For example, "abcba" is a palindrome, "abab" is not.
          Please implement this method to
          return true if the parameter is a palindrome and false otherwise.
         */ 
		 char[] cArr = s.toCharArray();
		 int len = cArr.length;
		 for(int i=0, j=len-1; i<len; i++,j--)
		 {
			if(cArr[i] != cArr[j])
			{	
				return false;
			}
		 }
		 return true;
    }
	public static void main(String... args)
	{
		if(args.length !=1)
		{
			System.out.println("Usage PolindromeTest <Any String>");
		}
		System.out.println("Is this a polindrome: "+ isPalindrome(args[0]));
	}
	public static int[] removeDuplicates(int[] a) {
        /*
          Please implement this method to
          remove all duplicates from the original array. Retain the order of the elements and
          always retain the first occurrence of the duplicate elements.
          For example, parameter: {2,1,2,3}, result: {2,1,3}
         */
		 if(a.length ==0)
			return;
		 int[] o = new int[a.length];
		 
		 o[0]=a[0];
		 for(int i=1,j=1;i<a.length;i++,j++)
		 {
				boolean isDuplicate = doesNoExists(a[i],o);
				if(!isDuplicate){
					o[j++] =a[i];
				}
		 }
    }
	public static boolean doesNoExists(int no, int[] o){
		for(int i=0;i<o.length;i++){
			if(no == o[i]){
				return true;
			}
		}
	}

}

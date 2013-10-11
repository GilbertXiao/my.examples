public class PolindromeTest {
    
    public static boolean isPalindrome(String s) {
        /*
          Definition: A palindrome is a string that reads the same forward and backward.
          For example, "abcba" is a palindrome, "abab" is not.
          Please implement this method to
          return true if the parameter is a palindrome and false otherwise.
         */ 
		 Char[] cArr = s.toCharArray();
		 int len = cArr.length;
		 for(int i=0, j=len-1; i<len; i++,j--)
		 {
			if(cArr[i] != cArr[j])
			{	
				return false;
			}
		 }
    }
	public static void main(String... args)
	{
		if(args.length !=1)
		{
			System.out.println("Usage PolindromeTest <Any String>");
		}
		System.out.println("Is this a polindrome: "+ isPalindrome(args[0]));
	}
}

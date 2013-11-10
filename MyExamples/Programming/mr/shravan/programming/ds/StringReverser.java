package mr.shravan.programming.ds;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class StringReverser
{
  public static void main(String[] args)
  {
        BufferedReader br = null;
        String str = null;
        try{
                br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter text to reverse:");
        str = br.readLine();
        int ln = str.length();
        if (ln <0)
        {
          System.out.println("Enter text:");
        }
        }catch(Exception e){}
        System.out.println("Entered text is : "+str);
        char[] chArr = str.toCharArray();
        System.out.println("Char Array :"+chArr[1]);
        for(int i = str.length()-1 ;  ; i --)
        {
          System.out.print(chArr[i]);
          if(i==0)
          {
                break;
          }
        }

   }
}

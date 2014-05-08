package mr.shravan.programming.ds;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class FibanacciSeriesPrinter
{
   public static void main(String[] args)
   {
     BufferedReader br = null;
     try{
        br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter a number and I'll tell you whether your number can be found in fibanacci seris or not : ");
        String line = br.readLine();
        int len = line.length();
        int input = Integer.MIN_VALUE;
        if(len <0)
        {
                System.out.println("Enter a number ");
                return;
        }
        else
        {
                try{
                input  = Integer.parseInt(line);
                }catch(Exception e)
                {
                        System.out.println("Enter a number not text ");
                        return ;

                }

        }
        boolean isInFib = isInFibanacci(input);
        if(isInFib)
        {
                System.out.println("Your number is in Fib series");
        }else{
                System.out.println("Your number is not in Fib Series");
        }
     }catch(IOException ioe)
     {
        ioe.printStackTrace();
     }
     finally{
        try{
        br.close();
        }catch(IOException ioe)
        {
          System.out.println("while closing the BufferedReader Exception Occured ");
        }
     }

   }
   private static boolean isInFibanacci(int input)
   {
       int max = input;
       int fib = 0;
        int iteration = 0;
        while (fib < input+1)
        {
                fib = fibnacci(iteration++);
                System.out.println(fib +",");
                if(fib == input)
                return true;
        }
        return false;
   }
   private static int fibnacci(int n)
   {
        if(n== 0 || n ==1)
        {
                return 1;
        }
        

       return fibnacci(n-1) + fibnacci(n-2);

   }
}

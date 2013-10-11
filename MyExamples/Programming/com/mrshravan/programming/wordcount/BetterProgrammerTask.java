package com.mrshravan.programming.wordcount;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BetterProgrammerTask {

    public static void main(String[] args) throws Exception {

//        testCountWords();
        testAlmostPrimeNumbers();

    }

    private static void testAlmostPrimeNumbers() throws IOException {
        
        System.out.println("Test Name: Count Almost Prime Numbers");
        String from = readInput("Enter one number (From):");
        String to = readInput("Enter another number (To):");
        System.out.println("Almost Prime Numbers in the given range: "
            + countAlmostPrimeNumbers(new Integer(from), new Integer(to)));
    }
   
    private static void testCountWords() throws IOException {
        System.out.println("Test Name: Count Words ");
        String s = readInput("Enter sentense:");
        System.out.println("No. of words:" + countWords(s));
    }

    public static String readInput(String s) throws IOException {
        System.out.println(s);
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        return br.readLine();
    }
    public static int countWords(String s) {
        /*
         * Please implement this method to return the word count in a given String. Assume that the parameter String can
         * only contain spaces and alphanumeric characters.
         */
        StringTokenizer st = new StringTokenizer(s, " ");
        int i = 0;
        while (st.hasMoreTokens()) {
            ++i;
            st.nextToken();
        }
        return i;
    }
    public static int countAlmostPrimeNumbers(int from, int to) {
        /*
         * A prime number is a natural number that has exactly two distinct natural number divisors, which are 1 and the
         * prime number itself. The first prime numbers are: 2, 3, 5, 7, 11, 13.
         * 
         * Almost prime numbers are the non-prime numbers which are divisible by only a single prime number.
         * 
         * Please implement this method to return the number of almost prime numbers within the given range
         * (inclusively).
         */
        // 1. Identify prime numbers and non-prime nbrs. in a given range.
        for (int i = from; i <= to; i++) {
            if (isItPrimeNumber(i)) {
                System.out.println("Prime Number: "+i);
            }
        }
        // 2. for each non prime number, see if the reminder is zero if it is divisbible by only one prime number.

        return -1;
    }

    private static boolean isItPrimeNumber(int num) {
        
        for (int i = 0; i < num; i++) {
            if (i % num == 0) {
                return false;
            }
        }
        return true;
    }

    public static int getClosestToZero(int[] a) {
        /*
         * Please implement this method to return the number in the array that is closest to zero. If there are two
         * equally close to zero elements like 2 and -2 - consider the positive element to be "closer" to zero.
         */
        return -1;
    }

}

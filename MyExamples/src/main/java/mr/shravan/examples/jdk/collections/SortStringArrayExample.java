package mr.shravan.examples.jdk.collections;

import java.util.Arrays;

public class SortStringArrayExample {
 
 /**
  * Sort String Array Example in Java
  * Example will sort string elements of Array through sort() method.
  * We will get sorted elements of string array
  * in order of ascending form.
  */
  public static void main(String[] args) {
     
    // This is unsorted Strings array elements
    String[] stringArray = {"a", "c", "e", "d"};
     
    // This is unsorted String Array in java.
    for (String unsortedArray : stringArray) {
        System.out.println(unsortedArray);
    }
     
    // Arrays.sort method will sort string array in ascending order
    Arrays.sort(stringArray);
     
    System.out.println("String Array after Sorting");
     
    // This will sort String Array.
    for (String sortArray : stringArray) {
        System.out.println(sortArray);
    }
  }
}


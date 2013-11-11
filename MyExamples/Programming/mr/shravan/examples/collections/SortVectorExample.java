package mr.shravan.examples.collections;

import java.util.Collections;
import java.util.Vector;
 
public class SortVectorExample {
 
    /**
     * Sort Vector Elements Example in Java
     * This example will sort vector elements by sort() method.
     * We will get sorted elements of vector array
     * in order of ascending.
     */
    public static void main(String[] args) {
         
        Vector vectorSort = new Vector();
            
        // adding new elements in Vector
        vectorSort.add("a");
        vectorSort.add("c");
        vectorSort.add("d");
        vectorSort.add("b");
         
        for(int i=0; i < vectorSort.size();i++){
            System.out.println("Unsorting Vector :"+vectorSort.get(i));
        }
        
        // collections sort() is method to sort Vector 
        // This will give ascending order of Vector
         
        Collections.sort(vectorSort);
         
        for(int i=0; i < vectorSort.size();i++){
            System.out.println("sorting Vector :"+vectorSort.get(i));
        }
    }
}


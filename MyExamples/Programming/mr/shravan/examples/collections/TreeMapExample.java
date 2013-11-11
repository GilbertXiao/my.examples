package mr.shravan.examples.collections;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
 
public class TreeMapExample {
 
    /**
     * TreeMap Example in java
     */
    public static void main(String[] args) {
         
        TreeMap treemap = new TreeMap();
         
        treemap.put(new Integer(2), "Two");
        treemap.put(new Integer(1), "One");
        treemap.put(new Integer(4), "Four");
        treemap.put(new Integer(3), "Three");
        treemap.put(new Integer(5), "Five");
         
        Set s=treemap.keySet();
 
        //Move next key and value of TreeMap by iterator
        Iterator it=s.iterator();
 
        while(it.hasNext())
        {
            // next() method is used to get key
            Integer key = (Integer)it.next();
 
            // get is used to get value of key in TreeMap
            String value=(String)treemap.get(key);
 
            System.out.println("Key :"+key+"  Value :"+value);
        }
    }
}


package mr.shravan.programming.ds;

// mergeSort.java
// demonstrates recursive merge sort
// to run this program: C>java MergeSortApp
////////////////////////////////////////////////////////////////
class DArray
   {
   private long[] theArray;          // ref to array theArray 
   private int nElems;               // number of data items
   //-----------------------------------------------------------
   public DArray(int max)            // constructor
      {
      theArray = new long[max];      // create array
      nElems = 0;
      }
   //-----------------------------------------------------------
   public void insert(long value)    // put element into array
      {
      theArray[nElems] = value;      // insert it
      nElems++;                      // increment size
      }
   //-----------------------------------------------------------
   public void display()             // displays array contents
      {
      for(int j=0; j<nElems; j++)    // for each element,
         System.out.print(theArray[j] + " ");  // display it
      System.out.println("");
      }
   //-----------------------------------------------------------
   public void mergeSort()           // called by main()
      {                              // provides workspace
      recMergeSort(0, nElems-1);
      }
   //-----------------------------------------------------------
   private void recMergeSort(int lowerBound,int upperBound)
      {
      if(lowerBound == upperBound)            // if range is 1,
         return;                              // no use sorting
      else
         {                                     // find midpoint
         int mid = (lowerBound+upperBound) / 2; // sort low half
         recMergeSort(lowerBound, mid);
                                              // sort high half
         recMergeSort(mid+1, upperBound);
                                              // merge them
         merge(lowerBound, mid, upperBound);
         }  // end else
      }  // end recMergeSort()
   //-----------------------------------------------------------
   private void merge(int lowerBound,
                           int mid, int upperBound)
      {
	  
	  long[] workSpace = new long[upperBound+1];
	  int lowPtr = lowerBound;
	  int midPtr = mid+1;
	  int i = 0;                             // workspace index
      while(lowPtr <= mid && midPtr <= upperBound){
         if( theArray[lowPtr] < theArray[midPtr] )
            workSpace[i++] = theArray[lowPtr++];
         else
            workSpace[i++] = theArray[midPtr++];
	  }
	  
      while(lowPtr <= mid){
         workSpace[i++] = theArray[lowPtr++];
	  }

      while(midPtr <= upperBound){
         workSpace[i++] = theArray[midPtr++];
	  }
	  int n = upperBound-lowerBound+1;       // # of items
      for(i=0; i<n; i++)
         theArray[lowerBound+i] = workSpace[i];
		 
		 
      }  // end merge()
	  
   //-----------------------------------------------------------
}  // end class DArray
////////////////////////////////////////////////////////////////
class MergeSortApp
   {
   public static void main(String[] args)
      {
      int maxSize = 100;             // array size
      DArray arr;                    // reference to array
      arr = new DArray(maxSize);     // create the array

      
       // insert items
      arr.insert(12);
      arr.insert(11);
      arr.insert(10);
      arr.insert(9);
      arr.insert(8);
      arr.insert(7);
	  arr.insert(6);
	  arr.insert(5);                
      arr.insert(4);
      arr.insert(3);
      arr.insert(2);
      arr.insert(1);
      arr.display();                 // display items

      arr.mergeSort();               // merge sort the array

      arr.display();                 // display items again
      }  // end main()
   }  // end class MergeSortApp
////////////////////////////////////////////////////////////////

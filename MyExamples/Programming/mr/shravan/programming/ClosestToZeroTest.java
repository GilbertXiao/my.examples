package mr.shravan.programming;

import java.util.Arrays;
public class ClosestToZeroTest {

    public static void main(String... args){
		int i[] = {20,10,20};
		int num = getClosestToZero(i);
		System.out.println("Number closest to zero in this array "+ Arrays.toString(i)+" is :"+ num);
		int j[] = {-10,-10,-20};
		num = getClosestToZero(j);
		System.out.println("Number closest to zero in this array "+ Arrays.toString(j)+" is :"+ num);

		int k[]  = {-10,10,-20};
		num = getClosestToZero(k);
		System.out.println("Number closest to zero in this array "+ Arrays.toString(k)+" is :"+ num);

		int l[]  = {-10,5,-20};
		num = getClosestToZero(l);
		System.out.println("Number closest to zero in this array "+ Arrays.toString(l)+" is :"+ num);

	}
	public static int getClosestToZero(int[] a) {
        /*
          Please implement this method to
          return the number in the array that is closest to zero.
          If there are two equally close to zero elements like 2 and -2
          - consider the positive element to be "closer" to zero.
         */
		 int smallestPosNum = Integer.MIN_VALUE;
		 int biggestNegNum = Integer.MIN_VALUE;
		 
		 if(a.length ==0)
			return smallestPosNum;
		//Initialize with biggest Negative Number and Smallest Positive Number
		 if(a[0] <0){
			biggestNegNum = a[0];
		 }else{
			smallestPosNum =a[0];
		 }
		 
		 //scan through the array and set biggestNegNum, smallestPosNum
		 for(int i = 0 ; i<a.length; i++){
			boolean isPositive = true;
			if( a[i]<0)
				isPositive = false;
				
			if(isPositive){
				if(smallestPosNum < a[i])
					smallestPosNum = a[i];
			}else{
				if(a[i] > biggestNegNum){
					biggestNegNum = a[i];
					
				}//if
			}//if-else
		 }//for
		 
		 //System.out.println("biggestNegNum:"+biggestNegNum);
		 //System.out.println("smallestPosNum:"+smallestPosNum);
		 
		 //now find out who is closest to zero
		 if((biggestNegNum == Integer.MIN_VALUE) && (smallestPosNum != Integer.MIN_VALUE)) // Only positive numbers in the array
			return smallestPosNum; //then smallest positve number is closest to zero
		 else if ((biggestNegNum != Integer.MIN_VALUE) && (smallestPosNum == Integer.MIN_VALUE)) //only negative numbers in the array
			return biggestNegNum;
		 else if((biggestNegNum != Integer.MIN_VALUE) && (smallestPosNum != Integer.MIN_VALUE)){ //array has both positve and negative numbers
			int sum = biggestNegNum+smallestPosNum;
			if(sum==0)
				return smallestPosNum;
			else if(sum <0)
				return smallestPosNum;
			else
				return biggestNegNum;
		 }
		 return smallestPosNum;
    }
}

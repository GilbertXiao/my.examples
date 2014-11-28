import java.util.Arrays;

public class ZeroSetter {
	public static void main(String[] args) {
		int[][] mXn = { { 1, 0, 3 },
			        { 5, 6, 7 } 
			     };
			     
		
		int rows = mXn.length;
		int cols = mXn[0].length;
		int[][] result = new int[rows][cols];
		
		for(int row=0 ; row< rows;row++){
			for(int col=0;col<cols;col++){
				if(mXn[row][col] == 0){
					for(int i=0; i< cols; i++){
						result[row][i]=0;
					}
					for(int i =0;i<rows;i++){
						result[i][col]=0;
					}
				}else{
					//result[row][col] = mXn[row][col];
				}
			}
		}
		System.out.println(Arrays.deepToString(mXn));

		System.out.println(Arrays.deepToString(result));
	}//main
}

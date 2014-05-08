package mr.shravan.programming.problems;
/**
Write a function to convert a collection of strings into a single string and a function to convert it back.

*/
public class StringJoinerSeperatorTest {
	public static void main(String[] args) {
			String[] strs = {"Hello world","String joiner test","String seperator"};
			int[] lens = new int[strs.length];
			String cString = joinStrings(strs,lens);
			System.out.println("Contactenated String:"+cString);
			
			stringSeperator(lens,cString);
						
	}//main
	
	public static String joinStrings(String[] strs,int[] lens){
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<strs.length;i++){
			lens[i]=(strs[i].length());
			sb.append(strs[i]);
		}
		return sb.toString();
	}
	public static void stringSeperator(int[] lens,String cString){
			int offset = 0;
			for(int i =0;i<lens.length;i++){
				int oldOffset = offset; 
				offset = offset+lens[i];
				System.out.println("String["+i+"]:"+cString.substring(oldOffset,offset));
			}
	}
}

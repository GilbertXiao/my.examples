package mr.shravan.programming.problems.book;

public class UniqueVerifierTest {
	public static void main(String[] args) {
		boolean flag = verifyUniqueString("12345abcd");
		System.out.println(flag);
		flag = verifyUniqueString("12345abc5");
		System.out.println(flag);
		flag = verifyUniqueStringV2("12345abcd");
		System.out.println(flag);
		flag = verifyUniqueStringV2("12345abc5");
		System.out.println(flag);
	}
	private static boolean verifyUniqueStringV2(String string){
		char cArr[] = string.toCharArray();
		boolean asci[] = new boolean[128];
		for(int i=0;i<cArr.length;i++){
			int pos = cArr[i];
			if(asci[pos]){
				return false;
			}
			asci[pos]=true;
		}
		
		return true;
	}
	private static boolean verifyUniqueString(String string) {
		char cArr[] = string.toCharArray();
		for (int i = 0; i < cArr.length; i++) {
			for (int j = 0; j < cArr.length; j++) {
				if (i == j) continue;
				if (cArr[i] == cArr[j]) {
					return false;
				}
			}
		}
		return true;
	}
}

class Check
{
	static boolean checkInt(int a)
	{
		return a!=0 ? true : false;
	}
}
class CheckDemo
{
	public static void main(String[] args) 
	{
		int b = 0;
		boolean z = Check.checkInt(3);
		System.out.println(z);
	}
}

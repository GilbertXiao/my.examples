
class Check
{
	static boolean checkInt(int a)
	{
		return a!=0;
	}
}
class CheckDemo
{
	public static void main(String[] args) 
	{
		boolean z = Check.checkInt(0);
		System.out.println(z);
	}
}

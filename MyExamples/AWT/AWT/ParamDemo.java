
class A
{
	int z;

	A  meth()
	{
		A temp = new A();
		temp.z = 2*this.z;
		return temp;
	}
}

class ParamDemo
{
	public static void main(String[] args) 
	{
		A a = new A();
		a.z = 5;

		A b = a.meth();
		System.out.println(a.z);
		System.out.println(b.z);
	}
}
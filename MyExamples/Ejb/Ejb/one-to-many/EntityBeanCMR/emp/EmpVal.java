package emp;

public class EmpVal implements java.io.Serializable
{
	public int id;
    public String name;
    public float sal;

    public EmpVal(int id,String name,float sal)
    {
		this.id=id;
        this.name=name;
        this.sal = sal;
    }
 }
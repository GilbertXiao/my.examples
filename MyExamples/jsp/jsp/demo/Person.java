
package demo;
public class Person implements java.io.Serializable
{
	private String firstName;
	private String lastName;
	
	public Person() 
	{}
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String fn)
	{
		firstName = fn;
	}
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String ln)
	{
		lastName = ln;
	}
}
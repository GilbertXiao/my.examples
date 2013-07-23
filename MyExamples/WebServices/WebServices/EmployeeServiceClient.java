import emp.*;
import empdemo.*;

class EmployeeServiceClient
{
	public static void main(String[] args) throws Exception
	{
    EmployeeService service = 
			new EmployeeService("http://localhost:7001/WebServices/EmployeeService?WSDL");

    EmployeeServicePort port = 
			service.getEmployeeServicePort();

		EmployeeVO empVO = new EmployeeVO(101,"Abc",12500);

		port.createEmployee((weblogic.xml.schema.binding.internal.builtin.VoidType)empVO);

		System.out.println("Created");

		//weblogic.xml.schema.binding.internal.builtin.VoidType t = 
		//port.getEmployeeDetails (101);
		//System.out.println(t);
	}
}

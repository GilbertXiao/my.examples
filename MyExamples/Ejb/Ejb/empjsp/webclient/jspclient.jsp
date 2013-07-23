<%@page contentType="text/html" language="java" 
import="javax.ejb.*,javax.rmi.*,javax.naming.*,java.util.*,emp.*,java.rmi.*"%>
<html>
	<head>
		<title>Employee Management</title>
		<script>
			function formsubmit(s,id)
			{
				var f = document.form1;
				f.sub.value = s;
				f.empID.value = id;
				f.submit();
			}
		</script>
	</head>
<body>
	<form name="form1" method="post" action="">
		<input type="hidden" name="sub" value="">
		<input type="hidden" name="empID" value="">

		 <%!
			emp.EmployeeHome h;
			String value,name,salary,id,edit;
			Collection c;
			emp.EmployeeDO empDO;
			boolean flag;
		%>

		<%
			value = request.getParameter("sub");
			name = request.getParameter("empName");
			id = request.getParameter("empID");
			salary = request.getParameter("empSal");
			edit = request.getParameter("empid");
			flag = false;
			Context ctx = new InitialContext();
			Object obj = ctx.lookup("java:comp/env/ejb/employee");
			Object o = PortableRemoteObject.narrow(obj,emp.EmployeeHome.class);
			h = (emp.EmployeeHome)o;

			if(value != null)
			{
				if(value.equals("Add"))
				{
					float sal = Float.parseFloat(salary);
					emp.Employee ee = h.create(name,sal);
				}
				else if(value.equals("Delete"))
				{
					try
					{
						Integer pk = new Integer(id);
						emp.Employee ee = h.findByPrimaryKey(pk);
						ee.remove();
					}
					catch(Exception e)
					{
		%>
		<h2><%="Employee "+id +" not found"%></h2>
		<%
					}
				}
				else if(value.equals("Update"))
				{
					try
					{
						Integer pk = new Integer(id);
						emp.Employee ee = h.findByPrimaryKey(pk);
						ee.setName(name);
						float sal = Float.parseFloat(salary);
						ee.setSalary(sal);
						flag = true;
					}
					catch(Exception e)
					{
		%>
		<h2><%="Employee "+id +" not found"%></h2>
		<%
					}
				}
				else if(value.equals("Find"))
				{
					try
					{
						Integer pk = new Integer(id);
						emp.Employee ee = h.findByPrimaryKey(pk);
						flag = true;
					}
					catch(Exception e)
					{
		%>
			<h2><%="Employee "+id +" not found"%></h2>
		<%
					}
				}
			}
		%>
		<input type="hidden" name="edit" value="">
		<table border="2" align="center" bordercolor="#993300" bgcolor="#FFCCCC">
			<tr> 
			  <td width="27%"> 
			  <div align="center"></div></td>
			  <td width="26%"> 
				<div align="center"><strong>EmpID</strong></div></td>
			  <td width="26%"> 
				<div align="center"><strong>EmpName</strong></div></td>
			  <td width="21%"><div align="center"><strong>EmpSal</strong></div></td>
			</tr>
			<%
				c = h.findByAllPrimaryKey();
				Iterator it = c.iterator();
				while(it.hasNext())
				{
					emp.Employee ee = (emp.Employee)it.next();
					empDO = ee.getEmployee();
					String url="jspclient.jsp?action=edit&empid="+empDO.empno;
					if(flag)
					{
						if(Integer.parseInt(id)==empDO.empno)
						{
			%>
			<tr> 
			  <td bgcolor="#999900"><a href="<%=url%>" target="f2">edit</a></td>
			  <td bgcolor="#999900"><%=empDO.empno%></td>
			  <td bgcolor="#999900"><%=empDO.ename%></td>
			  <td bgcolor="#999900"><%=empDO.sal%></td>
			</tr>
			<%			
						}
						else
						{
			%>
			<tr> 
			  <td><a href="<%=url%>" target="f2">edit</a></td>
			  <td><%=empDO.empno%></td>
			  <td><%=empDO.ename%></td>
			  <td><%=empDO.sal%></td>
			</tr>
			<%
						}
					}
					else
					{
						if(edit!=null)
						{
							if(Integer.parseInt(edit)==empDO.empno)
							{
			%>
			<tr> 
				<td>
<a href="javascript:formsubmit('Update','<%=empDO.empno%>')" target="f2">Update</a>&nbsp;
<a href="jspclient.jsp" target="f2">Cancel</a>
				</td>
				<td><%=empDO.empno%></td>
				<td><input type="text" name="empName" value="<%=empDO.ename%>"></td>
				<td><input type="text" name="empSal" value="<%=empDO.sal%>"></td>
			</tr>
			<%
							}
							else
							{
			%>
			<tr> 
				<td><a href="<%=url%>" target="f2">edit</a></td>
				<td><%=empDO.empno%></td>
				<td><%=empDO.ename%></td>
				<td><%=empDO.sal%></td>
			</tr>
			<%
							}
							
						}
						else
						{
			%>
			<tr> 
				<td><a href="<%=url%>" target="f2">edit</a></td>
				<td><%=empDO.empno%></td>
				<td><%=empDO.ename%></td>
				<td><%=empDO.sal%></td>
			</tr>
			<%
						}
					}
				}
			%>
		</table>
	</form>
</body>
</html>
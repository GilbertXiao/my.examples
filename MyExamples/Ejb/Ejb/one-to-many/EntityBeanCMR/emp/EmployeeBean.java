package emp;

import javax.ejb.*;

public abstract class EmployeeBean implements EntityBean
{
    public void ejbActivate() {}
    public void ejbPassivate(){}
    public void ejbLoad()     {}
    public void ejbStore()    {}
    public void ejbRemove()   {}

    public void setEntityContext(EntityContext ec){}
    public void unsetEntityContext(){}

    public Integer ejbCreate(String name,float sal)
    {
		setName(name);
		setSal(sal);
		return null;
    }

    public void ejbPostCreate(String name,float sal)
    {}

    public String getName()
    {
        return getDBEmpname();
    }
    public void setName(String name)
    {
        setDBEmpname(name);
    }

    public float getSal()
    {
		return getDBSal();
	}
	public void setSal(float sal)
	{
		setDBSal(sal);
	}

    public abstract Integer getDBEmpno();
    public abstract void  setDBEmpno(Integer id);

    public abstract String getDBEmpname();
    public abstract void   setDBEmpname(String name);

    public abstract float getDBSal();
    public abstract void setDBSal(float sal);
    public abstract Integer getDBDeptno();
    public abstract void    setDBDeptno(Integer deptno);
}
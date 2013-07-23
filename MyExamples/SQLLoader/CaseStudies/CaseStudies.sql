CREATE TABLE emp (
   empno           NUMBER(4) NOT NULL,
 ename           VARCHAR2(10),
 job             VARCHAR2(9),
 mgr             NUMBER(4),
 hiredate        DATE,
 sal             NUMBER(7,2),
 comm            NUMBER(7,2),
 deptno          NUMBER(2) 
    );

	
CREATE TABLE dept (
   deptno          NUMBER(2) NOT NULL,
 dname           VARCHAR2(14),
 loc             VARCHAR2(13)
    );	
	 

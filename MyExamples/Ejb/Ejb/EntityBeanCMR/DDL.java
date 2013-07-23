
create table emp
		(
			 empno int primary key,
			 ename varchar(20),
			 sal	 float
		)
----------------------------------------------
create table address
		(
			 id				int primary key,
			 house_no varchar(20),
			 street   varchar(20),
			 city			varchar(20),
			 country	varchar(20),
			 empid		int references emp(empno)
		)
----------------------------------------------

create table emp_sequence(sequence int) 
insert into  emp_sequence values(100)

----------------------------------------------

create table address_sequence(sequence int) 
insert into  address_sequence values(0)

----------------------------------------------
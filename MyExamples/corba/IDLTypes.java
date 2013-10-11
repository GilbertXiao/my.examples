
IDL Type				Java Type
--------				---------
short						short
long						int
long long				long
octet						byte
float						float
double					double
char  (1 byte)	char
wchar	(2 byte)  char
string					java.lang.String
wstring					java.lang.String
boolean					boolean
void						void
const						final 

module					package

interface				1)Java interface which 
									extends from
									org.omg.CORBA.Object

								2)Stub class   3)Skel class
								4)Helper class 5)Holder class

exception			  Java exception class 
								which extends from
								org.omg.CORBA.UserException

array						array of equi Java type
sequence				array of equi Java type
struct					final Java class

IDL param mode	Java
--------------	----
in							equi Java type	
out							holder class of equi Java type
inout						holder class of equi Java type


Obtaining Stub for an Object implementing X
-------------------------------------------
obj is obtained by lookup into naming service

X objRef = XHelper.narrow(obj);

Note: 
a) pre-compiler generates XHelper class
b) "obj" is obtained from NamingService lookup



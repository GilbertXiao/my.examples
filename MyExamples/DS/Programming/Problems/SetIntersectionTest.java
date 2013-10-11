import java.util.*;
public class SetIntersectionTest {

    public static Set<Object> getIntersection(Set<Object> a, Set<Object> b) {
        /*
          Please implement this method to
          return a Set equal to the intersection of the parameter Sets
          The method should not chage the content of the parameters.
         */
		 if(a==null || b ==null)
			return null;
		 Set<Object> res = new HashSet<Object>();
		 
		 Iterator it = a.iterator();
		 while(it.hasNext()){
			int aNum = (Integer)it.next();
			
			Iterator it1 = b.iterator();
			while(it1.hasNext()){
				int bNum = (Integer)it1.next();
				if(aNum == bNum)
				{
					res.add(aNum);
				}
				
			}
		}
		 return res;
    }
	public static void main(String... args){
		HashSet<Object> obj = new HashSet<Object>();
		obj.add(1);
		obj.add(2);
		obj.add(3);
		obj.add(4);
		HashSet<Object> obj1 = new HashSet<Object>();
		obj1.add(4);
		obj1.add(25);
		obj1.add(3);
		obj1.add(4);
		
		System.out.println("Intersection:"+getIntersection(obj,obj1));
	}
}

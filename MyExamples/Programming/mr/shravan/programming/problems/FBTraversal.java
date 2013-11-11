package mr.shravan.programming.problems;

/**
	Problem Statement:
		Assume primitive Facebook. FB has Members.
		class Member {
			String name;
			String email;
			List<Member> friends;
		}
	Question: 
		Code printSocialGraph(Member m). Direct friends of m are Level 1 friends. Friends of friends are level 2 friends.....and so on
		Print level 1 friends first. Then print level 2 friends....and so on
		void printSocialGraph (Member m){
		//Your code here
		}
*/

//package
//imports
import java.util.*;
//jdoc

class Member {
    public String name;
    public String email;
    public List<Member> friends;
	public Member(String name,String email)
	{
		this.name = name;
		this.email = email;
	}
}

public class FBTraversal{
	public void printSocialGraph(Member m){
			System.out.println("Member Name: " +m.name);
			// System.out.println("Member email: " + m.email);
			
			if( (m.friends == null)||(m.friends.size() == 0 ))
			{
				return ;
			}
			
			for(Member mem : m.friends)
			{
				printSocialGraph(mem);
			}
			
	}
	public static void main(String... args)
	{
		
		Member root = new Member("Root","root@gmail.com");
		
		Member c1 = new Member("Child-1","c1@gmail.com");
		Member c2 = new Member("Child-2","c2a@gmail.com");
		
		//add children for root
		List<Member> rootChildren = new ArrayList<Member>();
		rootChildren.add(c1);
		rootChildren.add(c2);
		root.friends = rootChildren;
		
		
		//Child-1 children
		Member c1a = new Member("Grand Child-1A","l1a@gmail.com");
		Member c1b = new Member("Grand Child-1B","l1a@gmail.com");
		Member c1c = new Member("Grand Child-1C","l1a@gmail.com");
		
		List<Member> members = new ArrayList<Member>();
		members.add(c1a);
		members.add(c1b);
		members.add(c1c);
		c1.friends = members;
		
		//Child-2 children
		Member c2a = new Member("Grand Child-2A","l1a@gmail.com");
		Member c2b = new Member("Grand Child-2B","l1a@gmail.com");
		Member c2c = new Member("Grand Child-2C","l1a@gmail.com");
		
		members = new ArrayList<Member>();
		members.add(c2a);
		members.add(c2b);
		members.add(c2c);
		c2.friends = members;
		
		FBTraversal fbt = new FBTraversal();		
		fbt.printSocialGraph(root);	
	
	}
}


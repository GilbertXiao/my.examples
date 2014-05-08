package mr.shravan.programming.ds;

class UniNode
{
	private int data;
	private UniNode next;
	public UniNode(int d){this.data=d;}
	public int getData(){return data;}
	public void setData(int d){this.data=d;}
	public UniNode getNext(){return next;}
	public void setNext(UniNode n){this.next = n;}
}
class LinkedList
{
	private UniNode first;
	public LinkedList(UniNode n)
	{
		first = n;
	}
	public UniNode getFirst(){return first;}
	public void setFirst(UniNode n){
		if(first==null)
		{
			first = n;
		}
	}
	public void insertFirst(int num)
	{
		UniNode node = new UniNode(num);
		if(first == null){
			first = node;
		}else{
			UniNode t = first;
			first = node;
			node.setNext(t);
		}
	}
	public void insertLast(int n)
	{
		UniNode nde = new UniNode(n);
		UniNode i = first;
		while(i.getNext()!=null)
		{
			i = i.getNext();
		}
		i.setNext(nde);
	}
	public void display()
	{
		UniNode n = first;
		while(n!=null)
		{
			System.out.println(n.getData());
			n = n.getNext();
		}
	}
	public LinkedList reverseLinkedList()
	{
		if(first == null)
		{
			return null;
		}
		UniNode temp = first;
		LinkedList rList = new LinkedList(first);
		UniNode n = first.getNext();
		while(n!=null)
		{
			rList.insertFirst(n.getData());
			n = n.getNext();
			
		}
		temp.setNext(null);
		return rList;
	}
}
public class LinkedListReverser
{
	public static void main(String[] args)
	{
		UniNode first = new UniNode(1);
		LinkedList lList = new LinkedList(first);
		lList.insertLast(2);
		lList.insertLast(3);
		lList.insertLast(4);
		lList.insertLast(5);
		System.out.println("Displaying the first order");
		lList.display();
		LinkedList rList = lList.reverseLinkedList();
		System.out.println("Displaying after reverse");
		rList.display();
	}
}
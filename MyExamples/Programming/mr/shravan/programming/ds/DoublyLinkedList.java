package mr.shravan.programming.ds;

class Node
{
	private int data;
	private Node next;
	private Node previous;
	public Node(){}
	public Node(int n){}
	public int getData(){return data;}
	public void setData(int data){this.data = data;}
	public Node getNext(){return next;}
	public void setNext(Node n){this.next = n;}
	public Node getPrevious(){return previous;}
	public void setPrevious(Node p){this.previous = p;}
}
public class DoublyLinkedList
{
	private Node first;
	private Node last;
	
	public Node getFirst(){return this.first;}
	public Node setFirst(Node f){this.first=f;}
	public Node getLast(){return this.last;}
	public Node setLast(Node l){this.last=l;}
	
	public boolean insertFirst(Node n){
	 if(first == null)
	 {
		first = n;
		last = n;
	 } else{
		Node temp = first;
		first = n;
		n.setNext(temp);
		temp.setPrevious(n);
	 }
	 
	}
	public boolean insertLast(Node n){
		if(last == null){
			insertFirst(n);
		}else{
		last.setNext(n);
		n.setPrevious(last);
		}
	}
	
	public boolean deleteFirst(){
		if(first !=null){
		first = first.getNext();
		first.setPrevious(null);
		}
	}
	public boolean deleteLast(){
		if(last!=null)
		{
			last = last.previous();
			last.setNext(null);
		}
	}
	public void displayForward(){
		Node n = first;
		while(n!=null)
		{
			System.out.prinltn(n.getData());
			n = n.getNext();
		};
	}
	public void displayBackward(){
		Node n = last;
		while(n!=null)
		{
			System.out.prinltn(n.getData());
			n = n.getPrevious();
		};
	}
	public void insertAfter(int key,Node n2Insert){
		if(first==null)
		{
			System.out.println("List is empty");
		}
		Node n = first;
		boolean success=false;
		while(n!=null)
		{
			if(n.getData() == key)
			{
				Node t = n.getNext();
				n.setNext(n2Insert);
				n2.setNext(t);
				n2.setPrevious(n);
				success = true;
			}
			n=n.getNext();
		}
	}
	public void insertBefore(int key,Node n2Insert){
		if(first == null)
		{
			System.out.println("List is empty");
		}
		Node n = first;
		boolean success = false;
		while(n!=null)
		{
			if(n.getData() == key)
			{
				if(n.getPrevious()==null)
				{
					n2Insert.setPrevious(null);
				}
				n.setPrevious(n2Insert);
				n2Insert.setNext(n);
				n2Insert
			}
		}
	}
	
	
}

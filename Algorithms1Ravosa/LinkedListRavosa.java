//
// Christopher Ravosa
// Assignment 1
// Due February 13
//
// This program implements stacks, queues, arrays, and linked lists to read
// items from a list of "magic items". The program then determines which of
// the items' names are palindromes and prints them.
//

public class LinkedListRavosa {
	private NodeRavosa myHead;
	private NodeRavosa myTail;
	
	public LinkedListRavosa() {
		myHead = null;
		myTail = null;
	}//null constructor
	
	public void setHead (NodeRavosa newHead) {
		myHead = newHead;
	}//setHead
	
	public NodeRavosa getHead() {
		return myHead;
	}//getHead
	
	public NodeRavosa setTail (NodeRavosa newTail) {
		return myTail;
	}//setTail
	
	public NodeRavosa getTail() {
		return myTail;
	}//getTail
	
	public boolean isFull() {
		return false;
	}//isFull
	
	public boolean isEmpty() {
		boolean ans = false;
		if (myHead == null)
			ans = true;
		return ans;
	}//isEmpty
	
	public void add(NodeRavosa newGuy) {
		myTail.setNext(newGuy);
		myTail = newGuy;
	}//add
	
}//LinkedListRavosa
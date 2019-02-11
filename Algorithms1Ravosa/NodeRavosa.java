//
// Christopher Ravosa
// Assignment 1
// Due February 13
//
// This program implements stacks, queues, arrays, and linked lists to read
// items from a list of "magic items". The program then determines which of
// the items' names are palindromes and prints them.
//

public class NodeRavosa {
	private String myItem;
	private NodeRavosa myNext;
	
	public NodeRavosa() {
		myItem = null;
		myNext = null;
	}//null constructor
	
	public NodeRavosa(String newItem) {
		myItem = newItem;
		myNext = null;
	}//full constructor
	
	public void setItem(String newItem) {
		myItem = newItem;
	}//setData
	
	public void setNext(NodeRavosa newNext) {
		myNext = newNext;
	}//setNext
	
	public String getItem() {
		return myItem;
	}//getData
	
	public NodeRavosa getNext() {
		return myNext;
	}//getNext
	
}//NodeRavosa
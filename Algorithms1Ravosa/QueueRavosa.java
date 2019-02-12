//
// Christopher Ravosa
// Assignment 1
// Due February 13
//
// This program implements stacks, queues, arrays, and linked lists to read
// items from a list of "magic items". The program then determines which of
// the items' names are palindromes and prints them.
//

public class QueueRavosa {
	private NodeRavosa myFront;
	private NodeRavosa myRear;
	
	public QueueRavosa() {
		myFront = null;
		myRear = null;
	}//null constructor
	
	public boolean isEmpty() {
		boolean ans = false;
		if (myFront == null)
			ans = true;
		return ans;
	}//isEmpty
	
	public void enqueue(char newLetter) {
		NodeRavosa enqueue = new NodeRavosa();
		myRear.setNext(enqueue);
		myRear = enqueue;
		enqueue.setData(newLetter);
	}//enqueue
	
	public char dequeue() {
		char ans = myFront.getData();
		myFront = myFront.getNext();
		return ans;
	}//dequeue
}//QueueRavosa
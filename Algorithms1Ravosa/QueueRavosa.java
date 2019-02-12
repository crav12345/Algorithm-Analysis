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
		enqueue.setData(newLetter);
		if (isEmpty()) {
			myFront = enqueue;
			myRear = enqueue;
		}//if
		else {
			myRear.setNext(enqueue);
			myRear = enqueue;
		}//else
	}//enqueue
	
	public char dequeue() {
		char ans = myFront.getData();
		myFront = myFront.getNext();
		return ans;
	}//dequeue
	
	public void printDetails() {
		NodeRavosa current = myFront;
		while (current != null) {
			System.out.print(current.getData());
			current = current.getNext();
		}//while
	}//printDetails
}//QueueRavosa
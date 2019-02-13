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
	//Declare variables for QueueRavosa objects
	private NodeRavosa myFront;
	private NodeRavosa myRear;
	
	//Initialize variables with a null constructor
	//Full constructor is again unnecessary because no NodeRavosa
	//objects exist at this point
	public QueueRavosa() {
		myFront = null;
		myRear = null;
	}//null constructor
	
	//isEmpty() returns true if myFront which is the same as myHead is null
	public boolean isEmpty() {
		boolean ans = false;
		if (myFront == null)
			ans = true;
		return ans;
	}//isEmpty
	
	//enqueue() creates a new instance of NodeRavosa and sets its stored
	//value to the next character in the string being inspected in main[].
	//If the queue is empty, the new node is made both the head and tail.
	//If the queue is not empty, the tail's pointer will point to the added
	//NodeRavosa and the new Node will become the tail.
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
	
	//dequeue() returns the stored character value in the head of the linked
	//list/queue. It will then make the head of the list the next NodeRavosa
	//in line to leave.
	public char dequeue() {
		char ans = myFront.getData();
		myFront = myFront.getNext();
		return ans;
	}//dequeue
	
	//printDetails() was once again used as a debugging method here and
	//also proved not to be very helpful. It prints the stored characters
	//of each NodeRavosa in the queue.
	public void printDetails() {
		NodeRavosa current = myFront;
		while (current != null) {
			System.out.print(current.getData());
			current = current.getNext();
		}//while
	}//printDetails
}//QueueRavosa
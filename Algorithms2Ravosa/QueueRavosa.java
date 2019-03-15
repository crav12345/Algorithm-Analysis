//
// Christopher Ravosa
// Assignment 2
// Due March 15
//
// This program reads a list of "magic items" into an array. It then
// implements sorting, searching, and hashing to organize the "magic items" in
// the array.
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
	
	public NodeRavosa getFront() {
		return myFront;
	}//getFront
	
	//enqueue() creates a new instance of NodeRavosa and sets its stored
	//value to the next character in the string being inspected in main[].
	//If the queue is empty, the new node is made both the head and tail.
	//If the queue is not empty, the tail's pointer will point to the added
	//NodeRavosa and the new Node will become the tail.
	public void enqueue(String newValue) {
		NodeRavosa enqueue = new NodeRavosa();
		enqueue.setData(newValue);
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
	public String dequeue() {
		String ans = myFront.getData();
		myFront = myFront.getNext();
		return ans;
	}//dequeue
}//QueueRavosa
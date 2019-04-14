//
// Christopher Ravosa
// Assignment 3
// Due April 17
//
// This program reads a text file describing graphs and implements each as
// a matrices, adjacency lists, and linked objects. It then performs
// various traversals on the linked object graphs. It also makes a binary tree
// from 42 random values in the "magic items" text file and traverses it.
//

public class QueueRavosa {
	//Declare variables for QueueRavosa objects
	private VertexRavosa myFront;
	private VertexRavosa myRear;
		
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
	
	public VertexRavosa getFront() {
		return myFront;
	}//getFront
	
	//enqueue() creates a new instance of NodeRavosa and sets its stored
	//value to the next character in the string being inspected in main[].
	//If the queue is empty, the new node is made both the head and tail.
	//If the queue is not empty, the tail's pointer will point to the added
	//NodeRavosa and the new Node will become the tail.
	public void enqueue(VertexRavosa newVertex) {
		VertexRavosa enqueue = newVertex;
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
	public VertexRavosa dequeue() {
		VertexRavosa ans = myFront;
		myFront = myFront.getNext();
		return ans;
	}//dequeue
}//QueueRavosa
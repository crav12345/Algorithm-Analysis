//
// Christopher Ravosa
// Assignment 1
// Due February 13
//
// This program implements stacks, queues, arrays, and linked lists to read
// items from a list of "magic items". The program then determines which of
// the items' names are palindromes and prints them.
//

public class StackRavosa {
	//Declare variable for StackRavosa objects
	private NodeRavosa myTop;

	//Initialize variable with a null constructor
	//Full constructor is not necessary since myTop will be a NodeRavosa
	//object, of which no instances exist
	public StackRavosa() {
		myTop = null;
	}//null
	
	//isEmpty() simply checks if myTop has a value yet; returns false if not
	public boolean isEmpty() {
		boolean ans = false;
		if(myTop == null)
			ans = true;
		return ans;
	}//isEmpty
	
	//push() creates an instance of NodeRavosa and sets it as the stack's new
	//top. It also sets the stored value of the new NodeRavosa object as the
	//next letter in the string being read.
	public void push(char newLetter) {
		NodeRavosa push = new NodeRavosa();
		push.setNext(myTop);
		push.setData(newLetter);
		myTop = push;
	}//push
	
	//pop() returns the character that is removed from the stack. The method
	//calls isEmpty() to see if there is anything to remove. If there isn't,
	//this will return '\u0000', the default equivalent of null for a char.
	//The myTop value is also set to the next Node object that will be popped
	public char pop() {
		char ans = '\u0000';
		if(!isEmpty()) {
			ans = myTop.getData();
			myTop = myTop.getNext();
		}//if
		return ans;
	}//pop
	
	//printDetails() runs through the stack and prints out the stored char
	//of each Node in the linked list. This is not implemented in the final
	//program, but was used in debugging. It was surprisingly not as helpful
	//as I had hoped. ¯\_('u' )_/¯
	public void printDetails() {
		NodeRavosa current = myTop;
		while (current != null) {
			System.out.print(current.getData());
			current = current.getNext();
		}//while
	}//printDetails
}//StackRavosa
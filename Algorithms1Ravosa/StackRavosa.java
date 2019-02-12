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
	private NodeRavosa myTop;

	public StackRavosa() {
		myTop = null;
	}//null
	
	public boolean isEmpty() {
		boolean ans = false;
		if(myTop == null)
			ans = true;
		return ans;
	}//isEmpty
	
	public void push(char newLetter) {
		NodeRavosa push = new NodeRavosa();
		push.setNext(myTop);
		push.setData(newLetter);
		myTop = push;
	}//push
	
	public char pop() {
		char ans = '\u0000';
		if(!isEmpty()) {
			ans = myTop.getData();
			myTop = myTop.getNext();
		}//if
		return ans;
	}//pop
}//StackRavosa
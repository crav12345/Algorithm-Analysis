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

public class NodeRavosa {
	
	//The nodes have 'myData' to hold their magic item, a left and right
	//because binary trees require no more than two children per node, and a
	//'myParent' variable that points to something, but I can't remember what
	//it is...
	private String myData;
	private NodeRavosa myLeft;
	private NodeRavosa myRight;
	private NodeRavosa myParent;
	
	public NodeRavosa() {
		myData = "";
		myLeft = null;
		myRight = null;
		myParent = null;
	}//null constructor
	
	public NodeRavosa(String newData) {
		myData = newData;
		myLeft = null;
		myRight = null;
		myParent = null;
	}//full constructor
	
	//Getters and setters
	public String getData() {
		return myData;
	}//getData
	
	public NodeRavosa getLeft() {
		return myLeft;
	}//getLeft
	
	public NodeRavosa getRight() {
		return myRight;
	}//getRight
	
	public NodeRavosa getParent() {
		return myParent;
	}//getP
	
	public void setData(String newData) {
		myData = newData;
	}//setData
	
	public void setLeft(NodeRavosa newLeft) {
		myLeft = newLeft;
	}//setLeft
	
	public void setRight(NodeRavosa newRight) {
		myRight = newRight;
	}//setLeft
	
	public void setParent(NodeRavosa newPointer) {
		myParent = newPointer;
	}//setP
}//NodeRavosa
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
	private String myData;
	private NodeRavosa myLeft;
	private NodeRavosa myRight;
	private NodeRavosa p;
	
	public NodeRavosa() {
		myData = "";
		myLeft = null;
		myRight = null;
		p = null;
	}//null constructor
	
	public NodeRavosa(String newData) {
		myData = newData;
		myLeft = null;
		myRight = null;
		p = null;
	}//full constructor
	
	public String getData() {
		return myData;
	}//getData
	
	public NodeRavosa getLeft() {
		return myLeft;
	}//getLeft
	
	public NodeRavosa getRight() {
		return myRight;
	}//getRight
	
	public NodeRavosa getP() {
		return p;
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
	
	public void setP(NodeRavosa newP) {
		p = newP;
	}//setP
}//NodeRavosa
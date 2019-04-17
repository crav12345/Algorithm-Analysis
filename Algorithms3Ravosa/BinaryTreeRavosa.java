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

public class BinaryTreeRavosa {
	private NodeRavosa myRoot;
	
	public BinaryTreeRavosa() {
		myRoot = null;
	}//null constructor
	
	public NodeRavosa getRoot() {
		return myRoot;
	}//getRoot
	
	//This method allows us to populate the tree. It accepts the new entry
	//as a parameter.
	public void treeInsert(NodeRavosa newGuy) {
		NodeRavosa y = null;
		NodeRavosa x = myRoot;
		
		//While the tree is not empty...
		while (x != null) {
			y = x;
			//...if the new entry's String is less then the root's data, then
			//progress to the left child. If it is greater, progress to the
			//right child.
			if (newGuy.getData().compareTo(x.getData()) < 0)
				x = x.getLeft();
			else
				x = x.getRight();
		}//while
		
		//Set the new guy's parent to the root.
		newGuy.setParent(y);
		
		//If the the graph was empty, then the root is now the new entry.
		if (y == null)
			myRoot = newGuy;
		//Otherwise, set the root's child to the new entry.
		else if (newGuy.getData().compareTo(y.getData()) < 0)
				y.setLeft(newGuy);
		else
			y.setRight(newGuy);
	}//treeInsert
}//BinaryTreeRavosa

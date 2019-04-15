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
	
	/*public NodeRavosa treeSearch(NodeRavosa x, String key) {
		NodeRavosa ans;
		if (x == null || key == x.getData())
			ans = x;
		else if (key.compareTo(x.getData()) < 0)
			ans = treeSearch(x.getLeft(), key);
		else
			ans = treeSearch(x.getRight(), key);
		return ans;
	}//treeSearch*/
	
	public void treeInsert(NodeRavosa z) {
		NodeRavosa y = null;
		NodeRavosa x = myRoot;
		while (x != null) {
			y = x;
			if (z.getData().compareTo(x.getData()) < 0)
				x = x.getLeft();
			else
				x = x.getRight();
		}//while
		z.setP(y);
		if (y == null)
			myRoot = z;
		else if (z.getData().compareTo(y.getData()) < 0)
				y.setLeft(z);
		else
			y.setRight(z);
	}//treeInsert
}//BinaryTreeRavosa

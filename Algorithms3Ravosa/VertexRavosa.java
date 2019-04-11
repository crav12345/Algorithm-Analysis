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

import java.util.*;

public class VertexRavosa {
	//Declare necessary variables for VertexRavosa objects
	private int myVID;
	private ArrayList<Integer> myEdges;
	
	//Initialize variables with constructors
	public VertexRavosa() {
		myVID = 0;
		myEdges = null;
	}//null constructor
	
	public VertexRavosa(int newVID) {
		myVID = newVID;
		myEdges = new ArrayList<Integer>();
	}//full constructor
	
	//Getter and setter methods for each variable are used to update values of
	//instances of VertexRavosa
	public int getVID() {
		return myVID;
	}//getVID
	
	public ArrayList<Integer> getEdges() {
		return myEdges;
	}//getEdges
	
	public void setVID (int newVID) {
		myVID = newVID;
	}//setVID
	
	public void addEdge(int newEdge) {
		myEdges.add(newEdge);
	}//addEdge
	
	public boolean sharesEdge(int target) {
		boolean found = false;
		if(myEdges.contains(target))
			found = true;
		return found;
	}//sharesEdge
}//VertexRavosa
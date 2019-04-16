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
	private ArrayList<VertexRavosa> myEdges;
	private String myColor;
	private VertexRavosa myPrevious;
	private VertexRavosa myNext;
	private int myDiscoverTime;
	private int myFinishTime;
	private boolean visited;
	
	//Initialize variables with constructors
	public VertexRavosa() {
		myVID = 0;
		myEdges = null;
		myColor = "";
		myPrevious = null;
		myNext = null;
		myDiscoverTime = 0;
		myFinishTime = 0;
		visited = false;
	}//null constructor
	
	public VertexRavosa(int newVID) {
		myVID = newVID;
		myEdges = new ArrayList<VertexRavosa>();
		myColor = "WHITE";
		myPrevious = null;
		myNext = null;
		myDiscoverTime = 0;
		myFinishTime = 0;
		visited = false;
	}//full constructor
	
	//Getter and setter methods for each variable are used to update values of
	//instances of VertexRavosa
	public int getVID() {
		return myVID;
	}//getVID
	
	public ArrayList<VertexRavosa> getEdges() {
		return myEdges;
	}//getEdges
	
	public String getColor() {
		return myColor;
	}//getColor
	
	public VertexRavosa getPrevious() {
		return myPrevious;
	}//getPrevious
	
	public VertexRavosa getNext() {
		return myNext;
	}//getPrevious
	
	public int getDiscoverTime() {
		return myDiscoverTime;
	}//getDiscoverTime
	
	public int getFinishTime() {
		return myFinishTime;
	}//getFinishTime
	
	public void setVID (int newVID) {
		myVID = newVID;
	}//setVID
	
	public void addEdge(VertexRavosa newEdge) {
		myEdges.add(newEdge);
	}//addEdge
	
	public boolean sharesEdge(int target) {
		boolean found = false;
		for (int i = 0; i < myEdges.size(); i++) {
			if(myEdges.get(i).getVID() == target)
				found = true;
		}//for
		return found;
	}//sharesEdge
	
	public boolean getVisited() {
		return visited;
	}//getVisited
		
	public void setColor(String newColor) {
		myColor = newColor;
	}//setColor
	
	public void setPrevious(VertexRavosa newPrevious) {
		myPrevious = newPrevious;
	}//setPrevious
	
	public void setNext(VertexRavosa newNext) {
		myNext = newNext;
	}//setPrevious
	
	public void setDiscoverTime(int newDiscoverTime) {
		myDiscoverTime = newDiscoverTime;
	}//getDiscoverTime
	
	public void setFinishTime(int newFinishTime) {
		myFinishTime = newFinishTime;
	}//getFinishTime
	
	public void setVisited(boolean newVisited) {
		visited = newVisited;
	}//setVisited
}//VertexRavosa
//
// Christopher Ravosa
// Assignment 4
// Due May 15
//
// This program reads two files. The first file contains instructions for
// constructing several directed, weighted graphs. The program will produce
// those graphs and implement the Bellman-Ford path finding algorithm on them.
// The second file will contain info regarding several valuable spices. The
// program will implement the fractional knapsack algorithm to determine the
// maximum value that can be taken away given those spices.
//

import java.util.ArrayList;

public class VertexRavosa {
	//Declare necessary variables for VertexRavosa objects
	private int myVID;
	private ArrayList<VertexRavosa> myOutgoingEdges;
	private ArrayList<VertexRavosa> myIncomingEdges;
	private ArrayList<Integer> myOutgoingWeights;
	private ArrayList<Integer> myIncomingWeights;
	private String myColor;
	private VertexRavosa myPrevious;
	private VertexRavosa myNext;
	
	//Initialize variables with constructors
	public VertexRavosa() {
		myVID = 0;
		myOutgoingEdges = null;
		myIncomingEdges = null;
		myOutgoingWeights = null;
		myIncomingWeights = null;
		myColor = "";
		myPrevious = null;
		myNext = null;
	}//null constructor
	
	public VertexRavosa(int newVID) {
		myVID = newVID;
		myOutgoingEdges = new ArrayList<VertexRavosa>();
		myIncomingEdges = new ArrayList<VertexRavosa>();
		myOutgoingWeights = new ArrayList<Integer>();
		myIncomingWeights = new ArrayList<Integer>();
		myColor = "WHITE";
		myPrevious = null;
		myNext = null;
	}//full constructor
	
	//Getter and setter methods for each variable are used to update values of
	//instances of VertexRavosa
	public int getVID() {
		return myVID;
	}//getVID
	
	public ArrayList<VertexRavosa> getOutgoingEdges() {
		return myOutgoingEdges;
	}//getOutgoing
	
	public ArrayList<VertexRavosa> getIncomingEdges() {
		return myIncomingEdges;
	}//getIncoming
	
	public ArrayList<Integer> getOutgoingWeights() {
		return myOutgoingWeights;
	}//outgoingWeights
	
	public ArrayList<Integer> getIncomingWeights() {
		return myIncomingWeights;
	}//incomingWeights
	
	public String getColor() {
		return myColor;
	}//getColor
	
	public VertexRavosa getPrevious() {
		return myPrevious;
	}//getPrevious
	
	public VertexRavosa getNext() {
		return myNext;
	}//getPrevious
	
	public void setVID (int newVID) {
		myVID = newVID;
	}//setVID
	
	public void addOutgoingEdge(VertexRavosa newEdge, int newWeight) {
		myOutgoingEdges.add(newEdge);
		myOutgoingWeights.add(newWeight);
	}//addOutgoingEdge
	
	public void addIncomingEdge(VertexRavosa newEdge, int newWeight) {
		myIncomingEdges.add(newEdge);
		myIncomingWeights.add(newWeight);
	}//addIncomingEdge
	
	public boolean sharesEdge(int target) {
		boolean found = false;
		for (int i = 0; i < myOutgoingEdges.size(); i++) {
			if(myOutgoingEdges.get(i).getVID() == target)
				found = true;
		}//for
		return found;
	}//sharesEdge
		
	public void setColor(String newColor) {
		myColor = newColor;
	}//setColor
	
	public void setPrevious(VertexRavosa newPrevious) {
		myPrevious = newPrevious;
	}//setPrevious
	
	public void setNext(VertexRavosa newNext) {
		myNext = newNext;
	}//setPrevious
	
}//VertexRavosa
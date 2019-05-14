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

public class EdgeRavosa {
	//Declare necessary variables for VertexRavosa objects
	private int myStartVertex;
	private int myEndVertex;
	private int myWeight;
	
	//Initialize variables with constructors
	public EdgeRavosa() {
		myStartVertex = -1;
		myEndVertex = -1;
		myWeight = 0;
	}//null constructor
	
	public EdgeRavosa(int newStart, int newEnd, int newWeight) {
		myStartVertex = newStart;
		myEndVertex = newEnd;
		myWeight = newWeight;
	}//full constructor
	
	//Getters and setters
	public int getStartVertex() {
		return myStartVertex;
	}//getStartVertex
	
	public int getEndVertex() {
		return myEndVertex;
	}//getEndVertex
	
	public int getWeight() {
		return myWeight;
	}//getWeight
	
	public void setStartVertex(int newStart) {
		myStartVertex = newStart;
	}//setStartVertex
	
	public void setEndVertex(int newEnd) {
		myEndVertex = newEnd;
	}//setEndVertex
	
	public void setWeight(int newWeight) {
		myWeight = newWeight;
	}//setWeight
}//EdgeRavosa

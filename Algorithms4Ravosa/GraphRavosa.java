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

import java.util.*;

public class GraphRavosa {
	private ArrayList<EdgeRavosa> myEdges;
	private ArrayList<Integer> myVertices;

	public GraphRavosa() {
		myEdges = null;
		myVertices = null;
	}//null constructor
	
	public GraphRavosa(ArrayList<EdgeRavosa> newEdges, ArrayList<Integer> newVertices) {
		myEdges = newEdges;
		myVertices = newVertices;
	}//full constructor
	
	public ArrayList<EdgeRavosa> getEdges() {
		return myEdges;
	}//getEdges
	
	public ArrayList<Integer> getVertices() {
		return myVertices;
	}//getEdges
	
	public void setEdges(ArrayList<EdgeRavosa> newEdges) {
		myEdges = newEdges;
	}//getEdges
	
	public void setVertices(ArrayList<Integer> newVertices) {
		myVertices = newVertices;
	}//getEdges
	
}//GraphRavosa

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

public class SpiceRavosa {
	private String myName;
	private double myUnitValue;
	private int myQuantity;
	
	public SpiceRavosa() {
		myName = "";
		myUnitValue = 0;
		myQuantity = 0;
	}//null constructor
	
	public SpiceRavosa(String newName, double newUnitValue, int newQuantity) {
		myName = newName;
		myUnitValue = newUnitValue;
		myQuantity = newQuantity;
	}//full constructor
	
	public String getName() {
		return myName;
	}//getName
	
	public double getUnitValue() {
		return myUnitValue;
	}//getUnitValue
	
	public int getQuantity() {
		return myQuantity;
	}//getQuantity
	
	public void setName(String newName) {
		myName = newName;
	}//setName
	
	public void setUnitValue(double newUnitValue) {
		myUnitValue = newUnitValue;
	}//setUnitValue
	
	public void setQuantity(int newQuantity) {
		myQuantity = newQuantity;
	}//setQuantity
	
}//SpiceRavosa

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
	private int myAbundance;
	private SpiceRavosa myNext;
	private SpiceRavosa myPrevious;
	
	public SpiceRavosa() {
		myName = "";
		myUnitValue = 0;
		myAbundance = 0;
		myNext = null;
		myPrevious = null;
	}//null constructor
	
	public SpiceRavosa(String newName, double newValue, int newAbundance) {
		myName = newName;
		myUnitValue = newValue;
		myAbundance = newAbundance;
		myNext = null;
		myPrevious = null;
	}//null constructor
	
	public String getName() {
		return myName;
	}//getName
	
	public double getUnitValue() {
		return myUnitValue;
	}//getUnitValue
	
	public int getAbundance() {
		return myAbundance;
	}//getAbundance
	
	public SpiceRavosa getNext() {
		return myNext;
	}//getNext
	
	public SpiceRavosa getPrevious() {
		return myPrevious;
	}//getPrevious
	
	public void setName(String newName) {
		myName = newName;
	}//setName
	
	public void setUnitValue(double newUnitValue) {
		myUnitValue = newUnitValue;
	}//setUnitValue
	
	public void setAbundance(int newAbundance) {
		myAbundance = newAbundance;
	}//setAbundance
	
	public void setNext(SpiceRavosa newNext) {
		myNext = newNext;
	}//setNext
	
	public void setPrevious(SpiceRavosa newPrevious) {
		myPrevious = newPrevious;
	}//setPrevious
}//SpiceRavosa
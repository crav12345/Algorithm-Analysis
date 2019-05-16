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

public class KnapsackRavosa {
	private double space;
	private double remainingSpace;
	private double spaceUsed;
	
	public KnapsackRavosa() {
		space = 0;
		remainingSpace = 0;
		spaceUsed = 0;
	}//null constructor
	
	public KnapsackRavosa(int newSpace) {
		space = newSpace;
		remainingSpace = newSpace;
		spaceUsed = 0;
	}//full constructor
	
	public double getSpace() {
		return space;
	}//getSpace
	
	public double getRemainingSpace() {
		return remainingSpace;
	}//getRemainingSpace
	
	public double getSpaceUsed() {
		return spaceUsed;
	}//getSpaceUsed
	
	public void setSpace(int newSpace) {
		space = newSpace;
	}//setSpace
	
	public void setRemainingSpace(int newRemainingSpace) {
		remainingSpace = newRemainingSpace;
	}//setSpace
	
	public void setSpaceUsed(int newSpaceUsed) {
		spaceUsed = newSpaceUsed;
	}//setSpace
	
	public boolean hasRoom(SpiceRavosa thisSpice) {
		boolean ans = true;
		if (remainingSpace - thisSpice.getUnitValue() < 0)
			ans = false;
		return ans;
	}//hasRoom
	
	public void scoop(SpiceRavosa spice) {
		spaceUsed = spaceUsed + spice.getUnitValue();
		remainingSpace = space - spaceUsed;
	}//scoop
}//KnapsackRavosa

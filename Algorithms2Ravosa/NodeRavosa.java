//
// Christopher Ravosa
// Assignment 2
// Due March 15
//
// This program reads a list of "magic items" into an array. It then
// implements sorting, searching, and hashing to organize the "magic items" in
// the array.
//

public class NodeRavosa {
	//Declare necessary variables for NodeRavosa objects
		private String myData;
		private NodeRavosa myNext;
		
		//Initialize variables with constructors
		public NodeRavosa() {
			myData = "";
			myNext = null;
		}//null constructor
		
		public NodeRavosa(String newData, int newHashCode) {
			myData = newData;
			myNext = null;
		}//full constructor
		
		//Getter and setter methods for each variable are used to update values of
		//instances of NodeRavosa
		public void setData(String newData) {
			myData = newData;
		}//setData
		
		public void setNext(NodeRavosa newNextChain) {
			myNext = newNextChain;
		}//setNextChain
		
		public String getData() {
			return myData;
		}//getData
		
		public NodeRavosa getNext() {
			return myNext;
		}//getNextChain
		
}//NodeRavosa
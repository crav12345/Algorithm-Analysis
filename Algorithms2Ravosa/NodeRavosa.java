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
		private int myKey;
		private NodeRavosa myNextKey;
		private NodeRavosa myNextChain;
		
		//Initialize variables with constructors
		public NodeRavosa() {
			myData = "";
			myKey = 0;
			myNextKey = null;
			myNextChain = null;
		}//null constructor
		
		public NodeRavosa(String newData, int newKey) {
			myData = newData;
			myKey = newKey;
			myNextKey = null;
			myNextChain = null;
		}//full constructor
		
		//Getter and setter methods for each variable are used to update values of
		//instances of NodeRavosa
		public void setData(String newData) {
			myData = newData;
		}//setData
		
		public void setKey(int newKey) {
			myKey = newKey;
		}//setNext
		
		public void setNextKey (NodeRavosa newNextKey) {
			myNextKey = newNextKey;
		}//setnNextKey
		
		public void setNextChain (NodeRavosa newNextChain) {
			myNextChain = newNextChain;
		}//setNextChain
		
		public String getData() {
			return myData;
		}//getData
		
		public int getKey() {
			return myKey;
		}//getKey
		
		public NodeRavosa getNextKey () {
			return myNextKey;
		}//getNextKey
		
		public NodeRavosa getNextChain () {
			return myNextChain;
		}//getNextChain
}//NodeRavosa
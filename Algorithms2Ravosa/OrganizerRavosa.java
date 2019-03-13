//
// Christopher Ravosa
// Assignment 2
// Due March 15
//
// This program reads a list of "magic items" into an array. It then
// implements sorting, searching, and hashing to organize the "magic items" in
// the array.
//

import java.io.File;
import java.util.*;

public class OrganizerRavosa {

	public static void main(String[] args) {
		//Variables for reading file
		String fileName = "magicItems.txt";
		File magicFile = new File(fileName);
		String line = null;
		String[] itemsArray = new String[666];
		int counter = 0;
		
//---------------------END OF VARIABLES---------------------------------------
				
		//Read "magic items" into the array
		try	{
			//Create a Scanner object to read from the file
			Scanner magicItems = new Scanner(magicFile);
			//While loop to read lines until the file doesn't have any left
			while(magicItems.hasNext())	{
				//Read the next line of input and store it
				line = magicItems.nextLine();	
				//Add the stored String as a lower-case into the array
				itemsArray[counter] = line.toLowerCase();
				//Increment the counter to find length of the array
				//for later use
				counter++;
			}//while
			//Close the file Scanner
			magicItems.close();
		}//try
		//Catch statement gives a nicer error if the program crashes
		catch(Exception ex) {
			System.out.println("Oops, something went wrong!");
		}//catch
				
//----------------------END OF READING IN FILE--------------------------------				
		
		mergeSort(itemsArray);
		
		for(int i = 0; i < itemsArray.length; i++)
			System.out.println((i+1) + ". " + itemsArray[i]);
		
	}//main
	
	
	public static void selectionSort(String[] myArray) {
		int comparisons = 0;
		int length = myArray.length;
		int i = 0;
		int smallPos = 0;
		int j = 0;
		String temp = "";
		
		for (i = 0; i <= length-2; i++) {
			smallPos = i;
			for (j = i + 1; j < length; j++) {
				if (myArray[j].compareTo(myArray[smallPos]) < 0) {
					smallPos = j;
					comparisons++;
				}//if
			}//inner for
			temp = myArray[smallPos];
			myArray[smallPos] = myArray[i];
			myArray[i] = temp;
		}//outer for
		
		for (i = 0; i < 666; i++)
			System.out.println((i+1) + ". " + myArray[i]);
		
		System.out.println();
		System.out.println("Comparisons in selection sort: " + comparisons);
		
	}//selectionSort
	
	
	public static void insertionSort(String[] myArray) {
		int i = 1;
		int length = myArray.length;
		String key = "";
		int j = 0;
		int comparisons = 0;
		
		for (i = 1; i < length; i++) {
			key = myArray[i];
			j = i-1;
			while (j >= 0 && (myArray[j].compareTo(key) > 0)) {
				myArray[j + 1] = myArray[j];
				j = j - 1;
				comparisons++;
			}//while
			myArray[j + 1] = key;
		}//for
		
		for (i = 0; i < 666; i++)
			System.out.println((i+1) + ". " + myArray[i]);
		
		System.out.println();
		System.out.println("Comparisons in insertion sort: " + comparisons);
		
	}//insertionSort
	
	
	public static String[] mergeSort(String[] myArray) {
		int length = myArray.length;
		int comparisons = 0;
		
		if (length <= 1) { 
			return myArray;
		}//if
		String[] listLeft = new String[length/2];
		String[] listRight = new String[length - listLeft.length];
        System.arraycopy(myArray, 0, listLeft, 0, listLeft.length);
        System.arraycopy(myArray, listLeft.length, listRight, 0, listRight.length);
        
		mergeSort(listLeft);
		mergeSort(listRight);
		
		merge(myArray, listLeft, listRight);
		
		return myArray;
	}//mergeSort
	
	public static int merge(String[] myArray, String[] listLeft, String[] listRight) {
		int indexLeft = 0;
		int indexRight = 0;
		int indexOfMerged = 0;
		int comparisons = 0;
		
		while (indexLeft < listLeft.length && indexRight < listRight.length) {
			if(listLeft[indexLeft].compareTo(listRight[indexRight]) < 0) {
				myArray[indexOfMerged] = listLeft[indexLeft];
				indexLeft++;
				comparisons++;
			}//if
			else {
				myArray[indexOfMerged] = listRight[indexRight];
				indexRight++;
			}//else
			indexOfMerged++;
		}//while
		System.arraycopy(listLeft, indexLeft, myArray, indexOfMerged, listLeft.length - indexLeft);
        System.arraycopy(listRight, indexRight, myArray, indexOfMerged, listRight.length - indexRight);
        
        return comparisons;
	}//merge
	
	public static void quickSort() {
		
	}//quickSort
	
}//OrganizerRavosa

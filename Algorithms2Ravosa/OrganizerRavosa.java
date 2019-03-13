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
		int mergeComparisons = 0;
		int i = 0;
		String[] selectionArray = new String[666];
		String[] insertionArray = new String[666];
		String[] mergeArray = new String[666];
		String[] quickArray = new String[666];
		boolean menu = true;
		
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
		
		for(i = 0; i < itemsArray.length; i++)
			selectionArray[i] = itemsArray[i];
		
		for(i = 0; i < itemsArray.length; i++)
			insertionArray[i] = itemsArray[i];
		
		for(i = 0; i < itemsArray.length; i++)
			mergeArray[i] = itemsArray[i];
		
		for(i = 0; i < itemsArray.length; i++)
			quickArray[i] = itemsArray[i];
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("-----COMPARISONS IN EACH SORT-----");
		selectionSort(selectionArray);
		insertionSort(insertionArray);
		mergeSort(mergeArray);
		quickSort(quickArray);
		System.out.println("----------------------------------");
			
		/*
		while (menu == true) {
			System.out.println("Would you like to print the sorted array?");
			System.out.println("Type 'YES' to print, 'NO' to exit");
		
			String choice = input.nextLine();
			switch(choice) {
			case "YES":
				for(i = 0; i < selectionArray.length; i++)
					System.out.println((i + 1) + ". " + selectionArray[i]);
				break;
			case "NO":
				System.out.println("Goodbye!");
				menu = false;
				break;
			default:
				System.out.println("Invalid input!");
				break;
			}//case
		}//while
		
		input.close();
		//*/
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
		
		System.out.println("Selection Sort: " + comparisons);
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
		
		System.out.println("Insertion Sort: " + comparisons);
	}//insertionSort
		
	public static String[] mergeSort(String[] myArray) {
		int length = myArray.length;
		int comparisons = 0;
		
		if (length > 1) {
			String[] listLeft = new String[length/2];
			String[] listRight = new String[length - listLeft.length];
        	System.arraycopy(myArray, 0, listLeft, 0, listLeft.length);
        	System.arraycopy(myArray, listLeft.length, listRight, 0, listRight.length);
        
			mergeSort(listLeft);
			mergeSort(listRight);
			
			comparisons = comparisons + merge(myArray, listLeft, listRight);
		}//else
		
		if (length == 666)
			System.out.println("Merge Sort: " + comparisons);
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
			}//if
			else {
				myArray[indexOfMerged] = listRight[indexRight];
				indexRight++;
			}//else
			indexOfMerged++;
			comparisons++;
		}//while
		
		System.arraycopy(listLeft, indexLeft, myArray, indexOfMerged, listLeft.length - indexLeft);
        System.arraycopy(listRight, indexRight, myArray, indexOfMerged, listRight.length - indexRight);
        
        return comparisons;
	}//merge
	
	public static String[] quickSort(String[] myArray) {
		int n = myArray.length;
		String pivot = "";
		Random r = new Random();
		int low = 0;
		int high = myArray.length;
		int result = r.nextInt(high-low) + low;
		int i = 0;
		int counterLeft = -1;
		int counterRight = -1;
		
		if (n<=1) {
			return myArray;
		}//if
		
		pivot = myArray[result];
		
		String[] left = new String[n/2];
		String[] right = new String[n - left.length];
		
		for(i = 0; i < myArray.length; i++) {
			if (myArray[i].compareTo(pivot) < 0) {
				counterLeft++;
				left[counterLeft] = myArray[i];
			}//if
			else if(myArray[i].compareTo(pivot) > 0) {
				counterRight++;
				right[counterRight] = myArray[i];
			}//else if
		}//for
		
		quickSort(left);
		quickSort(right);
		
		System.out.println("Aye lmao");
		return myArray;
	}//quickSort
	
}//OrganizerRavosa

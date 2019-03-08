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
			
		
		//Here you should make a menu asking whether he wants to see
		//sorting, searching, or hashing methods. Based on input,
		//go to that separate menu and let him choose one to use.
		//You should terminate the program after that so the array isn't
		//still sorted.
	
		//Either that or you could make a copy of the array each time
		//you do a method that gets tossed after each function runs!!!
		//You won't need to restart the program!
				
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
	
	/*public static void mergeSort(String[] myArray) {
		int length = myArray.length;
		String[] ans;
		String[] l;
		String[] r;
		
		if (length <= 1) {
			ans = myArray;
		}//if
		
		l = mergeSort();
		r = mergeSort((myArray[(length-2)]));
	}//mergeSort*/
	
	public static void quickSort() {
		
	}//quickSort
	
}//OrganizerRavosa

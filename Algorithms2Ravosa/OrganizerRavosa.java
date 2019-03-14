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

	static final int HASH_TABLE_SIZE = 250;
	static final int LINES_IN_FILE = 666;
	static int[] hashValues = new int[666];
	
	public static void main(String[] args) {
		//Variables for reading file
		String fileName = "magicItems.txt";
		File magicFile = new File(fileName);
		String line = null;
		String[] itemsArray = new String[666];
		int counter = 0;
		int i = 0;
		String[] selectionArray = new String[666];
		String[] insertionArray = new String[666];
		String[] mergeArray = new String[666];
		String[] quickArray = new String[666];
		int hashCode = 0;

		//boolean menu = true;
		
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
		
		System.out.println("-----COMPARISONS IN EACH SORT-----");
		selectionSort(selectionArray);
		insertionSort(insertionArray);
		System.out.println("Merge Sort: " + mergeSort(mergeArray, 0, 665));
		System.out.println("Quick Sort: " + quickSort(quickArray, 0, 665));
		System.out.println("----------------------------------");
		
//---------------------------END OF SORTS--------------------------------------
		
		Random rand = new Random();
		int randomValue = 0;
		String[] randomsArray = new String[42];
		
		for (i = 0; i < randomsArray.length; i++) {
			randomValue = rand.nextInt(666);
			randomsArray[i] = quickArray[randomValue];
		}//for
		
//--------------------------END OF SEARCHES------------------------------------	
		
		for (i = 0; i < itemsArray.length; i++) {
			hashCode = makeHashCode(itemsArray[i]);
			hashValues[i] = hashCode;
		}//for
		
		analyzeHashValues(hashValues);
		
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
		
	public static int mergeSort(String[] myArray, int left, int right) {
		int comparisons = 0;
		if (left < right) {
			int mid = (left + right)/2;
			mergeSort(myArray, left, mid);
			mergeSort(myArray, mid + 1, right);
			 
			comparisons = merge(myArray, left, mid, right);
		}//if
		
		return comparisons;
	}//mergeSort
	
	public static int merge(String[] myArray, int left, int mid, int right) {
		int i;
		int j;
		int k;
		int n1 = mid - left + 1;
		int n2 = right - mid;
		int comparisons = 0;
		String[] arrayLeft = new String[n1];
		String[] arrayRight = new String[n2];
		
		for (i = 0; i < n1; i++)
			arrayLeft[i] = myArray[left + i];
		for (j = 0; j < n2; j++)
			arrayRight[j] = myArray[mid + 1 + j];
		
		i = 0;
		j = 0;
		k = left;
		
		while (i < n1 && j < n2) {
			if(arrayLeft[i].compareTo(arrayRight[j]) <= 0) {
				myArray[k] = arrayLeft[i];
				i++;
				comparisons++;
			}//if
			else {
				myArray[k] = arrayRight[j];
				j++;
				comparisons++;
			}//else
			k++;
		}//while
		
		while(i < n1) {
			myArray[k] = arrayLeft[i];
			i++;
			k++;
		}//while
		
		while (j < n2) {
			myArray[k] = arrayRight[j];
			j++;
			k++;
		}//while
		
        return comparisons;
	}//merge
	
	public static int quickSort(String[] myArray, int lowIndex, int highIndex) {
		int partitionValue = 0;
		int comparisons = 0;
		
		if (lowIndex < highIndex) {
			comparisons++;
			//partition here
			String pivot = myArray[highIndex];
			int i = (lowIndex - 1);
			for (int j = lowIndex; j < highIndex; j++) {
				if(myArray[j].compareTo(pivot) <= 0) {
					i++;
					comparisons++;
					
					String temp = myArray[i];
					myArray[i] = myArray[j];
					myArray[j] = temp;
				}//if
			}//for
			
			String temp = myArray[i + 1];
			myArray[i + 1] = myArray[highIndex];
			myArray[highIndex] = temp;
			
			partitionValue = i + 1;
			
			quickSort(myArray, lowIndex, partitionValue-1);
			quickSort(myArray, partitionValue+1, highIndex);
		}//if
		
		return comparisons;
	}//quickSort
	
//--------------------------END OF SORT METHODS--------------------------------
	
	public static void linearSearch(String[] searchArray, String[] randomValues) {
		int j =0;
		int i = 0;
		int comparisons = 0;
		int avg = 0;
		int sum = 0;
		
		for (i = 0; i < randomValues.length; i++) {
			j = 0;
			comparisons = 0;
			while (searchArray[j].compareTo(randomValues[i]) != 0) {
				j++;
				comparisons++;
			}//while
			System.out.println("Search #" + (i + 1) + " comparisons: " + comparisons);
			sum = sum + comparisons;
		}//for
		
		avg = sum/42;
		
		System.out.println("Linear search's average # of comparisons: " + avg);
	}//linearSearch

	/*
	public static boolean binarySearch(String[] myArray, int startIndex, int stopIndex, String target) {
		int midpoint = ((startIndex+stopIndex)/2);
		if (startIndex > stopIndex) {
			return false;
		}//if
		else if (myArray[midpoint].compareTo(target) == 0) {
			return true;
		}//else
		else if (target.compareTo(myArray[midpoint]) < 0)
			binarySearch(myArray, startIndex, midpoint - 1, target);
		else if (target.compareTo(myArray[midpoint]) > 0)
			binarySearch(myArray, midpoint + 1, stopIndex, target);
		
	}//binarySearch
	*/

//-------------------------END OF SEARCH METHODS-------------------------------
	
	static int makeHashCode(String target) {
		int length = target.length();
		int letterTotal = 0;
		int i = 0;
		int hashCode = 0;
		
		for (i = 0; i < length; i++) {
			char thisLetter = target.charAt(i);
			int thisValue = (int)thisLetter;
			letterTotal = letterTotal + thisValue;
		}//for
		
		hashCode = (letterTotal * 1) % HASH_TABLE_SIZE;  // % is the "mod" operator
		   
		return hashCode;
	}//makeHashCode

	static void analyzeHashValues(int[] hashValues) {
		int asteriskCount = 0;
		int[] bucketCount = new int[HASH_TABLE_SIZE];
		int totalCount = 0;
		int arrayIndex = 0;
		int i = 0;
		
		System.out.println("Hash Table Usage:");
		
		Arrays.sort(hashValues);
		
		for (i = 0; i < HASH_TABLE_SIZE; i++) {
			System.out.format("%03d ", i);
			asteriskCount = 0;
			while ( (arrayIndex < LINES_IN_FILE) && (hashValues[arrayIndex] == i) ) {
				System.out.print("*");
				asteriskCount = asteriskCount + 1;
				arrayIndex = arrayIndex + 1;
			}
			System.out.print(" ");
			System.out.println(asteriskCount);
			bucketCount[i] = asteriskCount;
			totalCount = totalCount + asteriskCount;
		}//for
		   
		System.out.print("Average load (count): ");
		float averageLoad = (float) totalCount / HASH_TABLE_SIZE;
		System.out.format("%.2f%n", averageLoad);
		   
		System.out.print("Average load (calc) : ");
		averageLoad = (float) LINES_IN_FILE / HASH_TABLE_SIZE;
		System.out.format("%.2f%n", averageLoad);
		   
		System.out.print("Standard Deviation: ");
		// TODO: Refactor this into its own method.
		         double sum = 0;
		         for (i = 0; i < HASH_TABLE_SIZE; i++) {
		            // For each value in the array...
		            // ... subtract the mean from each one ...
		            double result = bucketCount[i] - averageLoad;
		            // ... and square the result.
		            double square = result * result;
		            // Sum all of those squares.
		            sum = sum + square;
		         }
		         // Divide the sum by the number of values ...
		         double temp = sum / HASH_TABLE_SIZE;
		         // ... and take the square root of that.
		         double stdDev = Math.sqrt(temp);
		         System.out.format("%.2f%n", stdDev);
		}//analyzeHashValues

}//OrganizerRavosa
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

	//These variables are constants that are used for the hash table later
	static final int HASH_TABLE_SIZE = 250;
	static final int LINES_IN_FILE = 666;
	static final int[] hashValues = new int[666];
	static final int[] bucketCount = new int[HASH_TABLE_SIZE];
	
	//This static variable enables counting comparisons in binary search
	static int binComp = 0;
	
	public static void main(String[] args) {
		
	//---------------------------READ IN FILE----------------------------------

		//Variables for reading file
		String fileName = "magicItems.txt";
		File magicFile = new File(fileName);
		String line = null;
		String[] itemsArray = new String[666];
		int counter = 0;
				
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
		
	//------------------------END OF READING FILE------------------------------
		
		//This scanner variable allows the user to view things at his/her own
		//pace. The program will not proceed until the user presses ENTER.
		Scanner keyboard = new Scanner(System.in);
		String advance = "";
		
		//i is initialized to be used as a counter
		int i = 0;
		
		//Make arrays to hold copies of the itemsArray for each sort
		String[] selectionArray = new String[666];
		String[] insertionArray = new String[666];
		String[] mergeArray = new String[666];
		String[] quickArray = new String[666];
		
		//Fill each empty copy with the values of itemsArray
		//Each of these will be sorted by their own method
		for(i = 0; i < itemsArray.length; i++)
			selectionArray[i] = itemsArray[i];
		for(i = 0; i < itemsArray.length; i++)
			insertionArray[i] = itemsArray[i];
		for(i = 0; i < itemsArray.length; i++)
			mergeArray[i] = itemsArray[i];
		for(i = 0; i < itemsArray.length; i++)
			quickArray[i] = itemsArray[i];
		
		//Stop the program until the user is aware of the next function
		//and decides to proceed.
		System.out.println(
				"Press ENTER to start with - comparisons in sort methods!");
		advance = keyboard.nextLine();
		
		//This block will call each sorting function and prints the number of
		//comparisons done to sort each array.
		System.out.println("-----COMPARISONS IN EACH SORT-----");
		selectionSort(selectionArray);
		insertionSort(insertionArray);
		System.out.println("Merge Sort: " + mergeSort(mergeArray, 0, 665));
		System.out.println("Quick Sort: " + quickSort(quickArray, 0, 665));
		System.out.println("----------------------------------");
		
	//-----------------EACH SORT HAS BEEN USED BY THIS POINT-------------------
		
		//Stop the program until the user is aware of the next function
		//and decides to proceed.
		System.out.println(
				"Press ENTER to continue to - comparisons in linear search!");
		advance = keyboard.nextLine();
		
		//This variable allows a random number to be generated
		Random rand = new Random();
		
		//This variable will retain the most recent random number generated
		int randomValue = 0;
		
		//This array will store values of the 42 random indexes generated
		String[] randomsArray = new String[42];
		
		//Based on the random value generated, find the string at the index
		//of the random value in a sorted copy of itemsArray. Put that string
		//into the randomsArray to store values that will be searched for.
		for (i = 0; i < randomsArray.length; i++) {
			randomValue = rand.nextInt(666);
			randomsArray[i] = quickArray[randomValue];
		}//for
		
		//Linear search accepts the sorted array and the random items and
		//searches for the random values in the sorted array. Linear search
		//keeps track of comparisons and prints results on its own.
		linearSearch(quickArray, randomsArray);
		
		//Stop the program until the user is aware of the next function
		//and decides to proceed.
		System.out.println(
				"----------------------------------------------------------");
		System.out.println(
				"Press ENTER to continue to - comparisons in binary search!");
		advance = keyboard.nextLine();
		
		//Sum keeps track of the comparisons during each iteration of bnSearch
		//and sums them so that the average can be computed at the end of the
		//for loop.
		int avg = 0;
		int sum = 0;
		
		//This for loop runs through each index of the random strings array.
		//The value at each index is then used as the target for a bnSearch.
		//Unlike my linSearch, this function's comparisons are counted using
		//several external variables and the printing occurs outside of the
		//bnSearch method.
		for(i = 0; i < randomsArray.length; i++) {
			binComp = 0;
			binarySearch(quickArray, 0, 665, randomsArray[i]);
			System.out.println(
					"Search #" + (i+1) + "; comparisons for '" +
					randomsArray[i] + "': " + binComp);
			sum = sum + binComp;
		}//for
		
		avg = sum/42;
		System.out.println("Binary search's average # of comparisons: " + avg);
		System.out.println("------------------------------------------------");
		
	//----------------EACH SEARCH HAS BEEN USED BY THIS POINT------------------
		
		//Stop the program until the user is aware of the next function
		//and decides to proceed.
		System.out.println(
				"Press ENTER to continue to - comparisons in finding hashed " + 
				"values!");
		advance = keyboard.nextLine();
		
		sum = 0;
		//This loop goes through every value in the random values array
		for(i = 0; i < randomsArray.length; i++) {
			//For each value, a new hashCode is generated along with a new
			//instance of a queue.
			int comparisons = 0;
			int hashCode = makeHashCode(randomsArray[i]);
			QueueRavosa thisQueue = new QueueRavosa();
			//For each value in the sorted array, if the value of its hashCode
			//is equal to the above hashCode, enqueue it into the queue.
			for(int j = 0; j < quickArray.length; j++) {
				if (makeHashCode(quickArray[j]) == hashCode) {
					thisQueue.enqueue(quickArray[j]);
				}//if
			}//inner for
			//Iterate through the queue until the value is found.
			//Keep track of each comparison.
			NodeRavosa current = new NodeRavosa();
			current = thisQueue.getFront();
			while (current.getData() != randomsArray[i]) {
				comparisons++;
				current = current.getNext();
			}//while
			
			//compensating for get
			comparisons++;
			
			//Print comparisons for each iteration of the outer loop.
			System.out.println(
					"Search #" + (i+1) + "; comparisons for '" + 
					randomsArray[i] + "': " + comparisons);
			sum = sum + comparisons;
		}//outer for
		
		//Average the comparisons for the hash table's search and print it.
		avg = sum/42;
		System.out.println(
				"Hash table's average # of comparisons per search: " + avg);
		System.out.println("------------------------------------------------");

		//Stop the program until the user wants to continue.
		System.out.println(
				"Press ENTER to continue to - analyzing the hash values!");
		advance = keyboard.nextLine();
		
		//Print a graphic model of the hash table
		for (i = 0; i < quickArray.length; i++) {
			hashValues[i] = makeHashCode(quickArray[i]);
		}//for
		analyzeHashValues(hashValues);

		System.out.println("------------------------------------------------");

		//Print a goodbye message
		advance = "That's everything, goodbye!";
		System.out.println(advance);
		
		//Close the scanner object
		keyboard.close();
	}//main
	
//------------------------SEARCH, SORT, HASH METHODS --------------------------
	
	public static void selectionSort(String[] myArray) {
		int comparisons = 0;
		int length = myArray.length;
		int i = 0;
		int smallPos = 0;
		int j = 0;
		String temp = "";
		
		//The for loop only runs to length-2 because the array is already
		//sorted by the time i reaches index of length - 1;
		for (i = 0; i <= length-2; i++) {
			smallPos = i;
			for (j = i + 1; j < length; j++) {
				
				//If the value above smallPos is first alphabetically,
				//then it becomes the new smallPos
				if (myArray[j].compareTo(myArray[smallPos]) < 0) {
					smallPos = j;
					comparisons++;
				}//if
				
			}//inner for
			
			//Swap the values, if the order is correct then this won't change
			//anything.
			temp = myArray[smallPos];
			myArray[smallPos] = myArray[i];
			myArray[i] = temp;
			
		}//outer for
		
		//Print the number of comparisons
		System.out.println("Selection Sort: " + comparisons);
	}//selectionSort
	
	public static void insertionSort(String[] myArray) {
		int i = 1;
		int length = myArray.length;
		String key = "";
		int j = 0;
		int comparisons = 0;
		
		for (i = 1; i < length; i++) {
			//Key is used to expand the portion of the array the program is
			//looking at.
			key = myArray[i];
			
			//J is used to refer to the index beneath the value of key
			j = i - 1;
			
			//This loop runs to see if key needs to be swapped with any value
			//in an index beneath it. If the value at index j is supposed to
			//come after key alphabetically, the values will swap.
			while (j >= 0 && (myArray[j].compareTo(key) > 0)) {
				myArray[j + 1] = myArray[j];
				j = j - 1;
				comparisons++;
			}//while
			myArray[j + 1] = key;
		}//for
		
		//Print result
		System.out.println("Insertion Sort: " + comparisons);
	}//insertionSort
		
	public static int mergeSort(String[] myArray, int left, int right) {
		int comparisons = 0;
		
		//If the left and right values are not equal or crossing
		if (left < right) {
			//A midpoint is found which divides the array in two
			int mid = (left + right)/2;
			
			//Recursive call of mergeSort on the two new array segments
			mergeSort(myArray, left, mid);
			mergeSort(myArray, mid + 1, right);
			 
			//The number of comparisons can be found by adding all the
			//comparisons done in the merge function
			comparisons = merge(myArray, left, mid, right);
		}//if
		
		//Return the number of comparisons
		return comparisons;
	}//mergeSort
	
	public static int merge(String[] myArray, int left, int mid, int right) {
		//These integers will reference the first index of the sub-arrays
		//that will be merged back together
		int i;
		int j;
		int k;
		
		int n1 = mid - left + 1;
		int n2 = right - mid;
		int comparisons = 0;
		
		//Arrays to store temporary values
		String[] arrayLeft = new String[n1];
		String[] arrayRight = new String[n2];
		
		//These for loops copy the data of the passed array into the left and
		//right copies
		for (i = 0; i < n1; i++)
			arrayLeft[i] = myArray[left + i];
		for (j = 0; j < n2; j++)
			arrayRight[j] = myArray[mid + 1 + j];
		
		i = 0;
		j = 0;
		k = left;
		
		//This while loop merges the temp-value arrays back together
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
		
		//If there are any remaining elements in the arrays, i.e. if the array
		//was of an odd length, they are copied to the array
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
		
		//Send back the number of comparisons
        return comparisons;
	}//merge
	
	public static int quickSort(
			String[] myArray, int lowIndex, int highIndex) {
		int partitionValue = 0;
		int comparisons = 0;
		
		if (lowIndex < highIndex) {
			comparisons++;
			//This block is the partition in the quickSort method
			//The pivot value is the highest index because random values mess
			//up my binarySearch method and I can't seem to figure out why
			String pivot = myArray[highIndex];
			int i = (lowIndex - 1);
			
			//In this for loop, every time the index, starting with the lower
			//index passed, is less than or equal to the pivot
			//(alphabetically), the value at j flips to the other side of the
			//pivot.
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
			
			//Recursively call quickSort until the array is sorted
			quickSort(myArray, lowIndex, partitionValue-1);
			quickSort(myArray, partitionValue+1, highIndex);
		}//if
		
		//Send back comparisons
		return comparisons;
	}//quickSort
	
//--------------------------END OF SORT METHODS--------------------------------
	
	public static void linearSearch(
			String[] searchArray, String[] randomValues) {
		int j =0;
		int i = 0;
		int comparisons = 0;
		int avg = 0;
		int sum = 0;
		
		//For loop runs through every value in the random array
		for (i = 0; i < randomValues.length; i++) {
			j = 0;
			comparisons = 0;
			
			//This loop continues until the value from the sorted array is
			//equal to the value being searched for from t he random values
			//array. When the value is found, the loop ends and the number
			//of comparisons is printed.
			while (searchArray[j].compareTo(randomValues[i]) != 0) {
				j++;
				comparisons++;
			}//while
			System.out.println(
					"Search #" + (i+1) + "; comparisons for '" + 
					randomValues[i] + "': " + comparisons);
			sum = sum + comparisons;
		}//for
		
		//Calculate and print the average comparisons
		avg = sum/42;
		System.out.println("Linear search's average # of comparisons: " + avg);
	}//linearSearch

	public static void binarySearch(
			String[] myArray, int lowIndex, int highIndex, String target) {
		
		int midpoint = (lowIndex+highIndex)/2;
		if (highIndex == lowIndex || lowIndex == midpoint || 
				highIndex == midpoint) {
			binComp++;
		}//if
		
		//If the midpoint of the array is less than the target string
		if (myArray[midpoint].compareTo(target) < 0) {
			binComp++;
			//Recursively call binarySearch with the new lowValue being the
			//midpoint.
			binarySearch(myArray, midpoint, highIndex, target);
		}//if
		//If the midpoint of the array is greater than the target value
		else if (myArray[midpoint].compareTo(target) > 0) {
			binComp++;
			//Recursively call binarySearch with the new upperValue being
			//the midpoint.
			binarySearch(myArray, lowIndex, midpoint, target);
		}//else if
		//If the midpoint is equal to the target value.
		//This is the last case the bnSearch will hit.
		else if (myArray[midpoint].compareTo(target) == 0) {
			binComp++;
		}//else if
	}//binarySearch

//-------------------------END OF SEARCH METHODS-------------------------------
	
	public static int makeHashCode(String target) {
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

	public static void analyzeHashValues(int[] hashValues) {
		int asteriskCount = 0;
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
        double sum = 0;
        for (i = 0; i < HASH_TABLE_SIZE; i++) {
        	// 	For each value in the array...
        	// ... subtract the mean from each one ...
        	double result = bucketCount[i] - averageLoad;
		    // ... and square the result.
		    double square = result * result;
		    // Sum all of those squares.
		    sum = sum + square;
        }//for
        // Divide the sum by the number of values ...
		double temp = sum / HASH_TABLE_SIZE;
		// ... and take the square root of that.
		double stdDev = Math.sqrt(temp);
		System.out.format("%.2f%n", stdDev);
	}//analyzeHashValues
	
}//OrganizerRavosa
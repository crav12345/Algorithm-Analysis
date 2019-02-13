//
// Christopher Ravosa
// Assignment 1
// Due February 13
//
// This program implements stacks, queues, arrays, and linked lists to read
// items from a list of "magic items". The program then determines which of
// the items' names are palindromes and prints them.
//


//Import necessary Java utilities for reading in the file
import java.io.*;
import java.util.Scanner;

public class PalindromeReaderRavosa {

	public static void main(String[] args) {
		//Menu variables
		Scanner keyboard = new Scanner(System.in);
		Boolean menu = true;
		int userChoice = 0;
		Boolean identified = false;
		//Read file variables
		String fileName = "magicItems.txt";
		File magicFile = new File(fileName);
		String line = null;
		String[] itemsArray = new String[666];
		//Counters for comparisons and running through the array 
		int charIndex = 0;
		int matchCount = -1;
		int spaceCount = 0;
		int counter = 0;
		int palindromeCount = 0;
		
		//Declare instances of my stack and queue classes
		StackRavosa myStack = new StackRavosa();
		QueueRavosa myQueue = new QueueRavosa();
	
//---------------------END OF VARIABLES---------------------------------------
		
		//Read file into the array
		try
		{
			//Create a Scanner object to read from the file
			Scanner magicItems = new Scanner(magicFile);
			//While loop to read lines until the file doesn't have any left
			while(magicItems.hasNext())
			{
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
		catch(Exception ex)
		{
			System.out.println("Oops, something went wrong!");
		}//catch
		
//----------------------END OF READING IN FILE--------------------------------				
		
		//Loop the menu until the user quits the program using this 
		//big-ass while loop
		while (menu == true) {
			//Reset the palindrome count so that each time user runs option 2 
			//it only goes up to 12
			palindromeCount = 0;
			
			//Print out the menu for the user to know what's happening
			System.out.println("A wizard approaches!");
			System.out.println("He says:");
			System.out.println("'Many magic items have I! Several have been " +
					"enchanted with mysterious palindromic spells! ");
			System.out.println("If you can find them in my 'Satchel of " + 
					"Immense Carrying', your's they will become...");
			System.out.println("but be warned! Choose the wrong items, and" +
					" a curse will befall you! WOoOooOOooh!'");
			System.out.println();
			System.out.println("Enter '1' to: View list of all magic items");
			System.out.println("Enter '2' to: Identify the palindromes");
			System.out.println("Enter '0' to: Walk away from the wizard");
			
			//Run a function based on the user choice using the
			//keyboard Scanner
			userChoice = keyboard.nextInt();
			
			//Execute a function based on value of user selection
			switch (userChoice) {
				//If the user chooses '1':
				//Print contents of magic items list by iterating through the
				//array storing it
				case 1:
					for (counter = 0; counter < 666; counter++) {
						System.out.println((counter+1) + ". " +
								itemsArray[counter]);
					}//for
					System.out.println();
					break;//case 1	
				
				//If user chooses '2':
				//Print the palindromes; major part of program runs on 
				//this option
				case 2:
					//This for loop runs through the array with the
					//magic item strings
					for (counter = 0; counter < 666; counter++) {
						//This while loop runs through individual characters 
						//in each string of each index of the array
						while (charIndex < itemsArray[counter].length()) {
							//Using the charIndex variable, the while loop
							//inspects the current character
							//If the char is not a ' ' (space), the char is
							//pushed and enqueued
							if (itemsArray[counter].charAt(charIndex) != ' ') {
								myStack.push(itemsArray[counter].
										charAt(charIndex));
								myQueue.enqueue(itemsArray[counter].
										charAt(charIndex));
							}//if
							//If the char is a ' ' (space), then a spaceCount 
							//will be incremented
							else
								spaceCount ++;
							//At the end of the while loop, the charIndex will
							//increment onto the next char in the string
							charIndex++;
						}//while
						
						//After a string is added 1 char at a time into the
						//stack and queue, this while loop will then call
						//compareLetters() until both the queue and stack are
						//empty. Each time the characters are equal, the
						//number of matching letters is incremented
						while (myStack.isEmpty() != true) {
							if(compareLetters(myStack, myQueue) == true) {
								matchCount++;
							}//if
						}//while
						
						//This if will add the number of matching characters
						//to the number of spaces ignored. If the sum is equal
						//to the length of the string inspected, the string is
						//a palindrome and will increment the palindrome count
						if ((matchCount + spaceCount) == charIndex) {
							palindromeCount++;	
							//Print the discovered palindrome
							System.out.println(palindromeCount + ". " + 
									itemsArray[counter]);
						}//if
						
						//Reset character being inspected to index 0
						//Reset the number of matches to 0
						//Reset the number of ignored ' ' characters to 0
						charIndex = 0;
						matchCount = 0;
						spaceCount = 0;
					}//for
					
					//Move onto the next line and reprint the menu
					System.out.println();
					identified = true;
					break;//case 2
				
				//If the user enters '0', the program will terminate
				case 0:
					if (identified == true)
						System.out.println("May these items serve you well!");
					System.out.println("Goodbye!");
					menu = false;
					break;//case 0
					
				//Print a message if the user input is not an acceptable int
				default:
					System.out.println("Invalid input!");
			}//switch
		}//while
		
		//Close the keyboard Scanner
		keyboard.close();
	}//main
	
//--------------------------END OF MAIN----------------------------------------
	
	//This method compares one character of the stack and queue at a time by
	//calling the pop and dequeue methods. The returned values are compared.
	//If the values are equal, the method returns true
	public static boolean compareLetters(
		StackRavosa magicStack, QueueRavosa magicQueue) {
			boolean ans = false;
			if(magicStack.pop() == magicQueue.dequeue())
				ans = true;
			return ans;
	}//compareWords
}//PalindromeReaderRavosa
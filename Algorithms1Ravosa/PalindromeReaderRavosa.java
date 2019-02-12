//
// Christopher Ravosa
// Assignment 1
// Due February 13
//
// This program implements stacks, queues, arrays, and linked lists to read
// items from a list of "magic items". The program then determines which of
// the items' names are palindromes and prints them.
//

import java.io.*;
import java.util.Scanner;

public class PalindromeReaderRavosa {

	public static void main(String[] args) {
		//Variables
		String fileName = "magicItems.txt";
		File magicFile = new File(fileName);
		String line = null;
		Scanner keyboard = new Scanner(System.in);
		Boolean menu = true;
		int userChoice = 0;
		String[] itemsArray = new String[666];
		int counter = 0;
		StackRavosa myStack = new StackRavosa();
		QueueRavosa myQueue = new QueueRavosa();
		int charIndex = 0;
		int matchCount = -1;
		int palindromeCount = 0;
	
		//Read in file into the array
		try
		{
			//create a Scanner object to read from the file
			Scanner magicItems = new Scanner(magicFile);
			//An exception is thrown if you try to read past the end-of-file
			while(magicItems.hasNext())
			{
				//Read the next line of input
				line = magicItems.nextLine();
				itemsArray[counter] = line.toLowerCase();
				counter++;
			}//while
			magicItems.close();
		}//try
		catch(Exception ex)
		{
			System.out.println("Oops, something went wrong!");
		}//catch
		
		//Loop the file until the user quits the program
		while (menu == true) {
			System.out.println("Select an option from the menu list by " + 
					"entering the corresponding key:");
			System.out.println("1. Print list of magic items");
			System.out.println("2. List palindromes");
			System.out.println("0. Exit program");
			userChoice = keyboard.nextInt();
			
			//Execute function based on user selection
			switch (userChoice) {
				//Print contents of magic items array
				case 1:
					for (counter = 0; counter < 666; counter++) {
						System.out.println((counter+1) + ". " +itemsArray[counter]);
					}//for
					System.out.println();
					break;//case 1
					
				case 2:
					for (counter = 0; counter < 666; counter++) {
						while (charIndex < itemsArray[counter].length()) {
							if (itemsArray[counter].charAt(charIndex) != ' ') {
								myStack.push(itemsArray[counter].charAt(charIndex));
								myQueue.enqueue(itemsArray[counter].charAt(charIndex));
							}//if
							charIndex++;
						}//while
						while (myStack.isEmpty() != true) {
							if(compareLetters(myStack, myQueue) == true) {
								matchCount++;
							}//if
						}//while
						if (matchCount == charIndex) {
							palindromeCount++;
							System.out.println(palindromeCount + ". " + itemsArray[counter]);
						}//if
						charIndex = 0;
						matchCount = 0;
					}//for
					System.out.println();
					break;//case 2
				
				case 0:
					System.out.println("Goodbye!");
					menu = false;
					break;//case 0
					
				default:
					System.out.println("Invalid input!");
			}//switch
		}//while
		keyboard.close();
	}//main
	
	public static boolean compareLetters(StackRavosa magicStack, QueueRavosa magicQueue) {
		boolean ans = false;
		if(magicStack.pop() == magicQueue.dequeue())
			ans = true;
		return ans;
	}//compareWords
	
}//PalindromeReaderRavosa
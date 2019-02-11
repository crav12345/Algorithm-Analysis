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
		
		String fileName = "magicItems.txt";
		File magicFile = new File(fileName);
		String line = null;
		Scanner keyboard = new Scanner(System.in);
		Boolean menu = true;
		int userChoice = 0;
		String[] itemsArray = new String[666];
		int counter = 0;
	
		try
		{
			//create a Scanner object to read from the file
			Scanner magicItems = new Scanner(magicFile);
		
			//An exception is thrown if you try to read past the end-of-file
			while(magicItems.hasNext())
			{
				//Read the next line of input
				line = magicItems.nextLine();
				itemsArray[counter] = line;
				counter++;
			}//while
			magicItems.close();
		}//try
	
		catch(Exception ex)
		{
			System.out.println("Oops, something went wrong!");
		}//catch
		
		while (menu == true) {
		
			System.out.println("Select an option from the menu list by " + 
					"entering the corresponding key:");
			System.out.println("1. Print list of magic items");
			System.out.println("2. List palindromes with stack implementation");
			System.out.println("3. List palindromes with queue implementation");
			System.out.println("0. Exit program");
			userChoice = keyboard.nextInt();
			
			switch (userChoice) {
				case 1:
					for (counter = 0; counter < 666; counter++) {
						System.out.println((counter+1) + ". " +itemsArray[counter]);
					}//for
					System.out.println();
					break;
				case 2:
					break;
				case 3:
					break;
				case 0:
					System.out.println("Goodbye!");
					menu = false;
					break;
				default:
					System.out.println("Invalid input!");
			}//switch
		}//while
		keyboard.close();
	}//main
}//PalindromeReaderRavosa
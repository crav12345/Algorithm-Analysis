//
// Christopher Ravosa
// Assignment 3
// Due April 17
//
// This program reads a text file describing graphs and implements each as
// a matrices, adjacency lists, and linked objects. It then performs
// various traversals on the linked object graphs. It also makes a binary tree
// from 42 random values in the "magic items" text file and traverses it.
//

import java.util.*;
import java.util.Scanner;
import java.io.File;

public class GraphReaderRavosa {

	public static void main(String[] args) {
		
		String fileName = "graphInfo.txt";
		File myFile = new File(fileName);
		ArrayList<VertexRavosa> graphInfo = new ArrayList<VertexRavosa>();
		String line = null;
		int i = 0;
		int graphNumber = 0;
		
		//Read file into the array
			try {
				//Create a Scanner object to read from the file
				Scanner graphFile = new Scanner(myFile);
				
				//While loop to read lines until the file doesn't have any left
				while(graphFile.hasNext()) {
					//Read the next line of input and store it
					line = graphFile.next();
					if(line.equals("vertex")) {
						int vertex = graphFile.nextInt();
						VertexRavosa thisVertex = new VertexRavosa(vertex);
						graphInfo.add(thisVertex);							
					}//if
					else if (line.equals("edge")) {
						int temp = graphFile.nextInt();
						int startVertex = temp;
						line = graphFile.next();
						temp = graphFile.nextInt();
						int endVertex = temp;
						for(i = 0; i < graphInfo.size(); i++) {
							if (graphInfo.get(i).getVID() == startVertex) {
								graphInfo.get(i).addEdge(endVertex);
							}//if
							else if (graphInfo.get(i).getVID() == endVertex) {
								graphInfo.get(i).addEdge(startVertex);
							}//else if
						}//for
					}//else if
					else if ((line.equals("new")) && (graphInfo.isEmpty() == false)) {
						graphNumber++;
						System.out.println("Matrix " + graphNumber + ":");
						printMatrix(graphInfo);
						System.out.println();
						System.out.println();
						graphInfo.clear();
					}//else if
				}//while
				
				//Last graph in file
				graphNumber++;
				System.out.println("Matrix " + graphNumber + ":");
				printZorkMatrix(graphInfo);
				System.out.println();
				System.out.println();
				graphInfo.clear();
				
				//Close the file Scanner
				graphFile.close();
			}//try
			//Catch statement gives a nicer error if the program crashes
			catch(Exception ex) {
				System.out.println("Oops, something went wrong!");
			}//catch
	}//main
	
	public static void printMatrix(ArrayList<VertexRavosa> matrixInfo) {
		int i = 0;
		int j = 0;
		
		for (i = 1; i <= matrixInfo.size(); i++) {
			for (j = 0; j < matrixInfo.size(); j++) {
				if (matrixInfo.get(j).sharesEdge(i)) {
					System.out.print(1 + " ");
				}//if
				else
					System.out.print(0 + " ");
			}//for
			System.out.println();
		}//for
	}//printMatrix
	
	public static void printZorkMatrix(ArrayList<VertexRavosa> matrixInfo) {
		int i = 0;
		int j = 0;
		
		for (i = 0; i < matrixInfo.size(); i++) {
			for (j = 0; j < matrixInfo.size(); j++) {
				if (matrixInfo.get(j).sharesEdge(i)) {
					System.out.print(1 + " ");
				}//if
				else
					System.out.print(0 + " ");
			}//for
			System.out.println();
		}//for
	}//printMatrix
}//GraphReaderRavosa

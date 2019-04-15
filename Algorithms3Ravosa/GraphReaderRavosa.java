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
import java.io.File;

public class GraphReaderRavosa {

	public static int graphNumber = 0;
	public static int time = 0;
	public static int comparisons = 0;
	public static int sum = 0;
	public static int avg = 0;
	
	public static void main(String[] args) {
		
		String fileName = "graphInfo.txt";
		File myFile = new File(fileName);
		ArrayList<VertexRavosa> graphInfo = new ArrayList<VertexRavosa>();
		String line = null;
		int i = 0;
		
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
						VertexRavosa startVertex = new VertexRavosa(temp);
						line = graphFile.next();
						temp = graphFile.nextInt();
						VertexRavosa endVertex = new VertexRavosa(temp);
						for(i = 0; i < graphInfo.size(); i++) {
							if (graphInfo.get(i).getVID() == startVertex.getVID()) {
								graphInfo.get(i).addEdge(endVertex);
							}//if
							else if (graphInfo.get(i).getVID() == endVertex.getVID()) {
								graphInfo.get(i).addEdge(startVertex);
							}//else if
						}//for
					}//else if
					else if ((line.equals("new")) && (graphInfo.isEmpty() == false)) {
						graphNumber++;
						
						System.out.println("Matrix " + graphNumber + ":");
						printMatrix(graphInfo);
						System.out.println();
						System.out.println("Adjacency List " + graphNumber + ":");
						printAdjList(graphInfo);
						System.out.println();
						System.out.println("DFS Route " + graphNumber + ":");
						DFS(graphInfo);
						System.out.println();
						System.out.println();
						System.out.println("BFS Route " + graphNumber + ":");
						BFS(graphInfo, graphInfo.get(0));
						System.out.println();
						System.out.println();
						
						graphInfo.clear();
						
						System.out.println();
					}//else if
				}//while
				
				//Last graph in file
				graphNumber++;
				System.out.println("Matrix " + graphNumber + ":");
				printZorkMatrix(graphInfo);
				System.out.println();
				System.out.println("Adjacency List " + graphNumber + ":");
				printAdjList(graphInfo);
				System.out.println();
				System.out.println("DFS Route " + graphNumber + ":");
				DFS(graphInfo);
				System.out.println();
				System.out.println();
				System.out.println("BFS Route " + graphNumber + ":");
				System.out.println();
				BFS(graphInfo, graphInfo.get(0));
				graphInfo.clear();
				
				//Close the file Scanner
				graphFile.close();
			}//try
			//Catch statement gives a nicer error if the program crashes
			catch(Exception ex) {
				System.out.println("Oops, something went wrong: " + ex);
			}//catch
			
			
			System.out.println();
			System.out.println();
			
			//Variables for reading file
			fileName = "magicItems.txt";
			File magicFile = new File(fileName);
			line = null;
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
				System.out.println("Oops, something went wrong: " + ex);
			}//catch
			
			BinaryTreeRavosa greatDekuTree = new BinaryTreeRavosa();
			
			for (i = 0; i < itemsArray.length; i++) {
				NodeRavosa newGuy = new NodeRavosa(itemsArray[i]);
				greatDekuTree.treeInsert(newGuy);
			}//for
			
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
				randomsArray[i] = itemsArray[randomValue];
			}//for
			
			System.out.println("Searching for Random Strings in Binary Tree: ");
			for (i = 0; i < randomsArray.length; i++) {
				NodeRavosa x = greatDekuTree.getRoot();
				treeSearch(x, randomsArray[i]);
				sum = sum + comparisons;
				System.out.println("Comparisons in search #" + (i+1) + ": " + sum);
				avg += sum;
				sum = 0;
				comparisons = 0;
			}//for
			
			avg = avg / randomsArray.length;
			System.out.println("Average comparisons: " + avg);
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
	
	public static void printAdjList(ArrayList<VertexRavosa> adjListInfo) {
		int i = 0;
		int j = 0;
		boolean zork = false;
		int adjListNum = 0;
		
		if (graphNumber == 5)
			zork = true;
		
		for (i = 0; i < adjListInfo.size(); i++) {
			if (zork == true)
				adjListNum = i;
			else
				adjListNum = i + 1;
			System.out.print((adjListNum) + "| --> ");
			if (adjListInfo.get(i).getEdges().isEmpty())
				System.out.println();
			else {
				for (j = 0; j < adjListInfo.get(i).getEdges().size(); j++) {
					if (j == adjListInfo.get(i).getEdges().size() - 1)
						System.out.println(adjListInfo.get(i).getEdges().get(j).getVID());
					else
						System.out.print(adjListInfo.get(i).getEdges().get(j).getVID() + ", ");
				}//for
			}//else
		}//for
	}//printAdjList
	
	public static void DFS(ArrayList<VertexRavosa> G) {
		for (int u = 0; u < G.size(); u++) {
			G.get(u).setColor("WHITE");
			G.get(u).setPrevious(null);
		}//for
		time = 0;
		for (int u = 0; u < G.size(); u++) {
			if (G.get(u).getColor().equals("WHITE")) {
				DFSVisit(G, G.get(u));
			}//if
		}//for
	}//depthFirstSearch
	
	public static void DFSVisit(ArrayList<VertexRavosa> G, VertexRavosa u) {
		System.out.print(u.getVID() + "|");
		time++;
		u.setDiscoverTime(time);
		u.setColor("GREY");
		for (int v = 0; v < u.getEdges().size(); v++) {
			if (u.getEdges().get(v).getColor().equals("WHITE")) {
				u.getEdges().get(v).setPrevious(u);
				DFSVisit(G, u.getEdges().get(v));
			}//if
		}//for
		u.setColor("BLACK");
		time++;
		u.setFinishTime(time);
	}//DFSVisit
	
	public static void BFS (ArrayList<VertexRavosa> G, VertexRavosa s) {
		QueueRavosa grayVertices = new QueueRavosa();
		for (int u = 0; u < G.size(); u++) {
			G.get(u).setColor("WHITE");
			G.get(u).setDiscoverTime(0);
			G.get(u).setPrevious(null);
			for (int i = 0; i < G.get(u).getEdges().size(); i++) {
				G.get(u).getEdges().get(i).setColor("WHITE");
			}//for
		}//for
		s.setColor("GRAY");
		System.out.print(s.getVID() + "|");
		s.setDiscoverTime(0);
		s.setPrevious(null);
		grayVertices.enqueue(s);
		while (grayVertices.isEmpty() == false) {
			VertexRavosa u = grayVertices.dequeue();
			for (int v = 0; v < u.getEdges().size(); v++) {
				System.out.print(u.getEdges().get(v).getVID() + "|");
				if (u.getEdges().get(v).getColor().equals("WHITE")) {
					u.getEdges().get(v).setColor("GRAY");
					u.getEdges().get(v).setDiscoverTime((u.getDiscoverTime() + 1));
					u.getEdges().get(v).setPrevious(u);
					grayVertices.enqueue(u.getEdges().get(v));
				}//if
			}//for
			u.setColor("BLACK");
		}//while
	}//breadthFirstSearch
	
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

	public static NodeRavosa treeSearch(NodeRavosa x, String key) {
		NodeRavosa ans;
		if (x == null || key == x.getData()) {
			ans = x;
			comparisons++;
		}//if
		else if (key.compareTo(x.getData()) < 0) {
			ans = treeSearch(x.getLeft(), key);
			comparisons++;
		}//else if
		else {
			ans = treeSearch(x.getRight(), key);
			comparisons++;
		}//else
		return ans;
	}//treeSearch
}//GraphReaderRavosa
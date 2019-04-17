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

	//Public variables
	public static int graphNumber = 0;
	public static int comparisons = 0;
	public static int sum = 0;
	public static int avg = 0;
	
	public static void main(String[] args) {
		//Variables for reading the file
		String fileName = "graphInfo.txt";
		File myFile = new File(fileName);
		ArrayList<VertexRavosa> graphInfo = new ArrayList<VertexRavosa>();
		String line = null;
		int i = 0;
		int vertex;
		
		//Reads each individual graph into an array-list of vertices
		try {
			//Create a Scanner object to read from the file
			Scanner graphFile = new Scanner(myFile);
			
			//While loop to read lines until the file doesn't have any left
			while(graphFile.hasNext()) {
				
				//Read the next line of input and store it
				line = graphFile.next();
				
				//Perform a function based on the key words 'vertex', 
				//'edge', and 'new' with these if-else's
				if(line.equals("vertex")) {
					//Add the vertex to the graph arraylist
					vertex = graphFile.nextInt();
					VertexRavosa thisVertex = new VertexRavosa(vertex);
					graphInfo.add(thisVertex);
				}//if
				else if (line.equals("edge")) {
					int temp = graphFile.nextInt();
					VertexRavosa startVertex = new VertexRavosa(temp);
					line = graphFile.next();
					temp = graphFile.nextInt();
					VertexRavosa endVertex = new VertexRavosa(temp);
					//For every vertex in the graph information, add its
					//edges to the corresponding value in the graph info
					//list. This representation is undirected so values
					//connect both ways to each other.
					for(i = 0; i < graphInfo.size(); i++) {
						if (graphInfo.get(i).getVID() == startVertex
						.getVID()) {
							graphInfo.get(i).addEdge(endVertex);
						}//if
						else if (graphInfo.get(i).getVID() == endVertex
						.getVID()) {
							graphInfo.get(i).addEdge(startVertex);
						}//else if
					}//for
				}//else if
				//If a new graph is being made, print the results and dump
				//the current instance of the graph list. The condition
				//will ignore the first 'new'.
				else if ((line.equals("new")) && (graphInfo.isEmpty() ==
				false)) {
					
					//Graph number is used for formatting and also to see
					//if the current graph is the "Zork Graph" since it
					//requires special treatment.
					graphNumber++;
					
					System.out.println("Matrix " + graphNumber + ":");
					printMatrix(graphInfo);
					System.out.println();
					System.out.println("Adjacency List " + 
					graphNumber + ":");
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
					
					//Toss the current graph
					graphInfo.clear();
					
					//Make a space, duh
					System.out.println();
				}//else if
			}//while
			
			//The file will not encounter another 'new' statement; so,
			//the last graph in the file is handled here after the above
			//while-loop ends. The "Zork Graph" has its own methods since
			//it begins at vertex '0'.
			graphNumber++;
			System.out.println("Matrix " + graphNumber + ":");
			printZorkMatrix(graphInfo);
			System.out.println();
			System.out.println("Adjacency List " + graphNumber + ":");
			printAdjList(graphInfo);
			System.out.println();
			System.out.println("DFS Route " + graphNumber + ":");
			zorkDFS(graphInfo);
			System.out.println();
			System.out.println();
			System.out.println("BFS Route " + graphNumber + ":");
			zorkBFS(graphInfo, graphInfo.get(0));
			System.out.println();
			graphInfo.clear();
			
			//Close the file Scanner
			graphFile.close();
		}//try
		//Catch statement gives a nicer error if the program crashes
		catch(Exception ex) {
			System.out.println("Oops, something went wrong: " + ex);
		}//catch
		
		//Make space between the graphing outputs and the binary tree
		//actions which are handled below.
		System.out.println();
		System.out.println();
		
//-----------------------------DONE WITH GRAPHS--------------------------------
		
//---------------------------READ IN MAGIC ITEMS-------------------------------
		
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
		
		//Create an instance of a binary tree to fill and traverse.
		BinaryTreeRavosa greatDekuTree = new BinaryTreeRavosa();
		
		//Make a node to carry each magic item and call treeInsert to
		//sauce it right into the Great Deku Tree.
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
		//of the random value in a sorted copy of itemsArray. Put that
		//string into the randomsArray to store values that will be
		//searched for.
		for (i = 0; i < randomsArray.length; i++) {
			randomValue = rand.nextInt(666);
			randomsArray[i] = itemsArray[randomValue];
		}//for
		
		System.out.println
			("Searching for Random Strings in Binary Tree: ");
		
		//For every value in the random items array...
		for (i = 0; i < randomsArray.length; i++) {
			//Start at the Deku tree's root and call treeSearch
			NodeRavosa x = greatDekuTree.getRoot();
			treeSearch(x, randomsArray[i]);
			//Add the number of comparisons (counted in the search method)
			//to the total sum.
			sum = sum + comparisons;
			//Print each search's comparisons.
			System.out.println("Comparisons in search #" + (i+1) + ": "
			+ sum);
			//Add the sum to the average to be divided later, and reset
			//the sum and comparisons.
			avg += sum;
			sum = 0;
			comparisons = 0;
		}//for
		
		//Divide the sum by 42 and print the average.
		avg = avg / randomsArray.length;
		System.out.println("Average comparisons: " + avg);
	}//main
	
	public static void printMatrix(ArrayList<VertexRavosa> matrixInfo) {
		int i = 0;
		int j = 0;
		
		//For every item in the list describing the matrix..
		for (i = 1; i <= matrixInfo.size(); i++) {
			//...see if it shares an edge with the other items in the list.
			//If it does, print 1. If it doesn't, print 0.
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
		
		//Check if this is the "Zork Graph".
		if (graphNumber == 5)
			zork = true;
		
		//For every item in the descriptive arraylist...
		for (i = 0; i < adjListInfo.size(); i++) {
			if (zork == true)
				adjListNum = i;
			else
				adjListNum = i + 1;
			
			//Literally just print its edges in a pretty way. If it's the
			//"Zork Graph", then the index will be printed regularly. If it
			//is any other graph, print each index as the vertex ID.
			System.out.print((adjListNum) + "| --> ");
			if (adjListInfo.get(i).getEdges().isEmpty())
				System.out.println();
			else {
				for (j = 0; j < adjListInfo.get(i).getEdges().size(); j++) {
					if (j == adjListInfo.get(i).getEdges().size() - 1)
						System.out.println(adjListInfo.get(i).getEdges()
						.get(j).getVID());
					else
						System.out.print(adjListInfo.get(i).getEdges()
							.get(j).getVID() + ", ");
				}//for
			}//else
		}//for
	}//printAdjList
	
	public static void DFS(ArrayList<VertexRavosa> G) {
		//Reset the color of every vertex to white in case this method doesn't
		//get called first.
		for (int u = 0; u < G.size(); u++) {
			G.get(u).setColor("WHITE");
		}//for
		
		//For each vertex in the graph, check if its color is white. This will
		//let the program know if it has been visited yet. If not, call the
		//helper method, 'DFSVisit'.
		for (int u = 0; u < G.size(); u++) {
			if (G.get(u).getColor().equals("WHITE")) {
				DFSVisit(G, G.get(u));
			}//if
		}//for
	}//depthFirstSearch
	
	public static void DFSVisit(ArrayList<VertexRavosa> G,
		VertexRavosa currVer) {
		//Now that we've visited the vertex, mark it as gray to show that
		//we're here, but we aren't finished with it yet (maniacal laughter).
		currVer.setColor("GREY");
		
		//For each of the vertex's connections...
		for (int v = 0; v < currVer.getEdges().size(); v++) {
			//If the connection's end vertex is still unvisited, call a
			//DFSVisit on it to change that quickly.
			if (G.get(currVer.getEdges().get(v).getVID()-1)
					.getColor().equals("WHITE")) {
				DFSVisit(G, G.get((currVer.getEdges().get(v).getVID()) - 1));
			}//if
		}//for
		
		//Now we're done with the input vertex, so we color it in all the way
		//and finish by printing it.
		currVer.setColor("BLACK");
		System.out.print(currVer.getVID() + "|");
	}//DFSVisit
	
	//The Zork-specific methods are exactly the same as the originals except
	//that they account for the first vertex having an ID of '0'.
	public static void zorkDFS(ArrayList<VertexRavosa> G) {
		for (int u = 0; u < G.size(); u++) {
			G.get(u).setColor("WHITE");
		}//for
		for (int u = 0; u < G.size(); u++) {
			if (G.get(u).getColor().equals("WHITE")) {
				zorkDFSVisit(G, G.get(u));
			}//if
		}//for
	}//depthFirstSearch
	
	//The Zork-specific methods are exactly the same as the originals except
	//that they account for the first vertex having an ID of '0'.
	public static void zorkDFSVisit(ArrayList<VertexRavosa> G,
			VertexRavosa u) {
		u.setColor("GREY");
		for (int v = 0; v < u.getEdges().size(); v++) {
			if (G.get(u.getEdges().get(v).getVID()).getColor()
			.equals("WHITE")) {
				zorkDFSVisit(G, G.get(u.getEdges().get(v).getVID()));
			}//if
		}//for
		u.setColor("BLACK");
		System.out.print(u.getVID() + "|");
	}//DFSVisit
	
	public static void BFS (ArrayList<VertexRavosa> G, VertexRavosa rootNode) {
		//Instantiate a queue to tell us which vertices have been visited, but
		//have not had all necessary actions performed on them.
		QueueRavosa grayVertices = new QueueRavosa();
		
		//Reset the color of each vertex to white again in case we don't run
		//this function first.
		for (int u = 0; u < G.size(); u++) {
			G.get(u).setColor("WHITE");
		}//for
		
		//Begin with the rootNode, make it known that we visited it, and
		//enqueue it into the gray-vertex queue. Also, print it because we
		//were here.
		rootNode.setColor("GRAY");
		grayVertices.enqueue(rootNode);
		System.out.print(rootNode.getVID() + "|");
		
		//While there exists a gray vertex...
		while (!grayVertices.isEmpty()) {
			
			//...store the front of the queue in a representative vertex.
			VertexRavosa u = grayVertices.dequeue();
			
			//For each of the vertex's edges...
			for (int v = 0; v < u.getEdges().size(); v++) {
				//...if the vertex on the other side hasn't been visited,
				//make it gray, print it, and enqueue it.
				if (G.get((u.getEdges().get(v).getVID()) - 1).getColor()
				.equals("WHITE")) {
					System.out.print(u.getEdges().get(v).getVID() + "|");
					G.get((u.getEdges().get(v).getVID()) - 1).setColor("GRAY");
					grayVertices.enqueue(G.get((u.getEdges().get(v)
					.getVID()) - 1));
				}//if
			}//for
			
			//Fill in the vertex we just used to show we're done and that it
			//has been removed from the queue.
			u.setColor("BLACK");
		}//while
	}//breadthFirstSearch

	//The Zork-specific methods are exactly the same as the originals except
	//that they account for the first vertex having an ID of '0'.
	public static void zorkBFS (ArrayList<VertexRavosa> G,
		VertexRavosa rootNode) {
		QueueRavosa grayVertices = new QueueRavosa();
		for (int u = 0; u < G.size(); u++) {
			G.get(u).setColor("WHITE");
		}//for
		rootNode.setColor("GRAY");
		grayVertices.enqueue(rootNode);
		System.out.print(rootNode.getVID() + "|");
		while (!grayVertices.isEmpty()) {
			VertexRavosa u = grayVertices.dequeue();
			for (int v = 0; v < u.getEdges().size(); v++) {
				if (G.get(u.getEdges().get(v).getVID()).getColor()
				.equals("WHITE")) {
					System.out.print(u.getEdges().get(v).getVID() + "|");
					G.get(u.getEdges().get(v).getVID()).setColor("GRAY");
					grayVertices.enqueue(G.get(u.getEdges().get(v).getVID()));
				}//if
			}//for
			u.setColor("BLACK");
		}//while
	}//zorkBFS
	
	//The Zork-specific methods are exactly the same as the originals except
	//that they account for the first vertex having an ID of '0'.
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
		
		//If the root is non-existent or the target value is in the input
		//vertex, just return the input vertex.
		if (x == null || key == x.getData()) {
			ans = x;
			comparisons++;
		}//if
		//Otherwise, if the target is alphabetically less than the string in
		//the input vertex, recursively call treeSearch on the left-child of
		//the input.
		else if (key.compareTo(x.getData()) < 0) {
			ans = treeSearch(x.getLeft(), key);
			comparisons++;
		}//else if
		//Otherwise, if the target is alphabetically greater than the string
		//in the input vertex, recursively call treeSearch on the right-child
		//if the input.
		else {
			ans = treeSearch(x.getRight(), key);
			comparisons++;
		}//else
		return ans;
	}//treeSearch
}//GraphReaderRavosa
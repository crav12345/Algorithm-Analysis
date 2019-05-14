//
// Christopher Ravosa
// Assignment 4
// Due May 15
//
// This program reads two files. The first file contains instructions for
// constructing several directed, weighted graphs. The program will produce
// those graphs and implement the Bellman-Ford path finding algorithm on them.
// The second file will contain info regarding several valuable spices. The
// program will implement the fractional knapsack algorithm to determine the
// maximum value that can be taken away given those spices.
//

import java.io.File;
import java.util.*;

public class IntergalacticReaderRavosa {
	
	//Public variables
	public static int graphNumber = 0;

	public static void main(String[] args) {
		//Variables for reading the file
		String fileName = "graphInfo.txt";
		File myFile = new File(fileName);
		ArrayList<VertexRavosa> graphVertices = new ArrayList<VertexRavosa>();
		ArrayList<EdgeRavosa> graphEdges = new ArrayList<EdgeRavosa>();
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
					graphVertices.add(thisVertex);
				}//if
				else if (line.equals("edge")) {
					int startVID = graphFile.nextInt();
					VertexRavosa startVertex = new VertexRavosa(startVID);
					line = graphFile.next();
					int endVID = graphFile.nextInt();
					VertexRavosa endVertex = new VertexRavosa(endVID);
					line = graphFile.next();
					int weight = Integer.parseInt(line);
					EdgeRavosa thisEdge = new EdgeRavosa(startVertex, endVertex, weight);
					graphEdges.add(thisEdge);
					
					//For every vertex in the graph information, add its
					//edges to the corresponding value in the graph info
					//list. This representation is directed so values
					//connect one way to each other.
					for(i = 0; i < graphVertices.size(); i++) {
						if (graphVertices.get(i).getVID() == startVertex
						.getVID()) {
							graphVertices.get(i).addOutgoingEdge
							(endVertex, weight);
						}//if
						else if (graphVertices.get(i).getVID() == endVertex
						.getVID()) {
							graphVertices.get(i).addIncomingEdge
							(startVertex, weight);
						}//else if
					}//for
				}//else if
				//If a new graph is being made, print the results and dump
				//the current instance of the graph list. The condition
				//will ignore the first 'new'.
				else if ((line.equals("new")) && (graphVertices.isEmpty() ==
				false)) {
					
					//Graph number is used for formatting.
					graphNumber++;
					System.out.println("Adjacency List " + 
					graphNumber + ":");
					printAdjList(graphVertices);
					System.out.println();
					System.out.println("Path " + graphNumber + ":");
					bellmanFord(graphVertices, graphEdges, graphVertices.get(0));
					//Toss the current graph
					graphVertices.clear();
					graphEdges.clear();
					
					//Make a space
					System.out.println();
				}//else if
			}//while
			
			//The file will not encounter another 'new' statement; so,
			//the last graph in the file is handled here after the above
			//while-loop ends.
			graphNumber++;
			System.out.println("Adjacency List " + 
			graphNumber + ":");
			printAdjList(graphVertices);
			System.out.println();
			System.out.println("Path " + graphNumber + ":");
			bellmanFord(graphVertices, graphEdges, graphVertices.get(0));
			//Toss the current graph
			graphVertices.clear();
			graphEdges.clear();
			
			//Make a space
			System.out.println();
			
			//Close the file Scanner
			graphFile.close();
		}//try
		//Catch statement gives a nicer error if the program crashes
		catch(Exception ex) {
			System.out.println("Oops, something went wrong: " + ex);
		}//catch
		
		//Make space after the program is finished using the graphs
		System.out.println();
		System.out.println();
	}//Main
	
	public static void printAdjList(ArrayList<VertexRavosa> adjListInfo) {
		int i = 0;
		int j = 0;
		int adjListNum = 0;
		
		//For every item in the descriptive arraylist...
		for (i = 0; i < adjListInfo.size(); i++) {
				adjListNum = i + 1;
			
			//Literally just print its edges in a pretty way.
			System.out.print((adjListNum) + "| --> ");
			if (adjListInfo.get(i).getOutgoingEdges().isEmpty())
				System.out.println();
			else {
				for (j = 0; j < adjListInfo.get(i).getOutgoingEdges().size();
					j++) {
					if (j == adjListInfo.get(i).getOutgoingEdges().size() - 1)
						System.out.println("Vertex " + adjListInfo.get(i)
						.getOutgoingEdges().get(j).getVID() + " Weight " + 
						adjListInfo.get(i).getOutgoingWeights().get(j));
					else
						System.out.print("Vertex " + adjListInfo.get(i)
						.getOutgoingEdges().get(j).getVID() + " Weight " + 
						adjListInfo.get(i).getOutgoingWeights().get(j) + ", ");
				}//for
			}//else
		}//for
	}//printAdjList
		
	//Don't take in weight because you keep track of it in the graph
	public static boolean bellmanFord(ArrayList<VertexRavosa> graphVertices, ArrayList<EdgeRavosa> graphEdges, VertexRavosa source) {
		boolean ans = true;
		initializeSingleSource(graphVertices, source);
		for (int i = 1; i < graphVertices.size(); i++) {
			for (int j = 0; j < graphEdges.size(); j++) {
				relax(graphEdges.get(j));
			}//for
		}//for
		for (int j = 0; j < graphEdges.size(); j++) {
			if (graphEdges.get(j).getEndVertex().getBound() > (graphEdges.get(j).getStartVertex().getBound() + graphEdges.get(j).getWeight())) {
				ans = false;
				System.out.println("Graph contains negative weight cycle.");
			}//if
		}//for
		System.out.println("You didn't crash, yayyyy!");
		return ans;
	}//bellmanFord
	
	public static void initializeSingleSource(ArrayList<VertexRavosa> graphVertices, VertexRavosa source) {
		for (int i = 0; i < graphVertices.size(); i++) {
			graphVertices.get(i).setPrevious(null);
			graphVertices.get(i).setBound(Integer.MAX_VALUE);
		}//for
		source.setBound(0);
	}//initializeSingleSource
	
	public static void relax(EdgeRavosa anxiousEdge) {
		VertexRavosa u = anxiousEdge.getStartVertex();
		VertexRavosa v = anxiousEdge.getEndVertex();
		int weight = anxiousEdge.getWeight();
		if (v.getBound() > (u.getBound() + weight)) {
			v.setBound(u.getBound() + weight);
			v.setPrevious(u);
		}//if
	}//relax
}//IntergalacticReaderRavosa

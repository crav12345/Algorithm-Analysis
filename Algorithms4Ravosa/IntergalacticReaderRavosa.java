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
	
	public static int formatter = 0;
	
	public static void main(String[] args) {
		//Variables to read file
		String fileName = "graphInfo.txt";
		File myFile = new File(fileName);
		String line = null;
		int vertex = 0;
		int source = 0;
		int dest = 0;
		int w = 0;
		int graphNumber = 0;
		ArrayList<Integer> vertices = new ArrayList<Integer>();
		ArrayList<EdgeRavosa> edges = new ArrayList<EdgeRavosa>();
		
		//Reads each individual graph into an array-list of vertices
		try {
			//Create a Scanner object to read from the file
			Scanner graphFile = new Scanner(myFile);
			
			//While loop to read lines until the file doesn't have any left
			while(graphFile.hasNext()) {
				
				line = graphFile.next();
				
				if (line.equals("vertex")) {
					vertex = graphFile.nextInt();
					vertices.add(vertex);
				}//if "vertex"
				else if (line.equals("edge")) {
					source = graphFile.nextInt();
					line = graphFile.next();
					dest = graphFile.nextInt();
					line = graphFile.next();
					w = Integer.parseInt(line);
					EdgeRavosa newEdge = new EdgeRavosa(source, dest, w);
					edges.add(newEdge);
				}//else if "edge"
				else if (line.equals("new") && vertices.isEmpty() == false) {
					graphNumber++;
					System.out.println("Graph " + graphNumber + ": ");
					GraphRavosa thisGraph = new GraphRavosa(edges, vertices);
					bellmanFord(thisGraph, 0);
					vertices.clear();
					edges.clear();
					System.out.println();
				}//else if "new"
			}//while
			
			//Last graph is handled here!
			graphNumber++;
			System.out.println("Graph " + graphNumber + ": ");
			GraphRavosa thisGraph = new GraphRavosa(edges, vertices);
			bellmanFord(thisGraph, 0);
			vertices.clear();
			edges.clear();
			graphFile.close();
			
		}//try
		catch(Exception ex) {
			System.out.println("Oops, something went wrong: " + ex);
		}//catch
	}//main
	
	
	public static void bellmanFord(GraphRavosa graph, int source) {
		int vertices = graph.getVertices().size();
		int edges = graph.getEdges().size();
		int distance[] = new int[vertices];
		int previous[] = new int[vertices];
		
		//Initialize distances from all source to all vertices to infinite
		for (int i=0; i<vertices; i++)
			distance[i] = Integer.MAX_VALUE;
		distance[source] = 0;
		
		//Relax all edges (# of vertices - 1) times
		for (int i=1; i<vertices; i++) {
			for (int j=0; j<edges; j++) {
				int u = graph.getEdges().get(j).getStartVertex();
				int v = graph.getEdges().get(j).getEndVertex();
				int weight = graph.getEdges().get(j).getWeight();
				if (distance[u-1]!=Integer.MAX_VALUE &&
					distance[u-1]+weight<distance[v-1]) {
					distance[v-1]=distance[u-1]+weight;
					
					//Set vertex v's previous as vertex u
					previous[v-1] = u-1;
					
				}//if
			}//for every edge
		}//for (# of vertices -1)
		
		//Check for a negative cycle
		for (int j=0; j<edges; j++) {
			int u = graph.getEdges().get(j).getStartVertex();
			int v = graph.getEdges().get(j).getEndVertex();
			int weight = graph.getEdges().get(j).getWeight();
			if (distance[u-1]!=Integer.MAX_VALUE &&
				distance[u-1]+weight<distance[v-1])
				System.out.println("Graph contains negative weight cycle!");
		}//for all edges
		
		for (int i=1; i<vertices; i++) {
			System.out.print("-- " + 1 + " --> " + (i+1) + " cost is " + distance[i] + "; path: 1 --> ");
			formatter = i+1;
			printPath(previous, i);
			System.out.println();
		}//for # of vertices
		
	}//bellmanFord	
	
	public static void printPath(int parents[], int vertex) {
		if (vertex==0)
			return;
		
		printPath(parents, parents[vertex]);
		if (vertex+1 != formatter)
			System.out.print(vertex+1 + " --> ");
		else
			System.out.print(vertex+1);
	}//printPath
	
}//IntergalacticReaderRavosa

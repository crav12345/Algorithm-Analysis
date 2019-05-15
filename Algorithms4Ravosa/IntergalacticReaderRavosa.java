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
	
	//This public variable is used to determine when an arrow is placed when
	//we print the path in Bellman-Ford.
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
		
		//This variable is used for indicating which graph is being evaluated.
		int graphNumber = 0;
		
		//Vertices are stored in a list of integers. Edges are stored in a
		//list of EdgeRavosa objects. These are used later to create an
		//instance of GraphRavosa.
		ArrayList<Integer> vertices = new ArrayList<Integer>();
		ArrayList<EdgeRavosa> edges = new ArrayList<EdgeRavosa>();
		
		//Reads each individual graph into an array-list of vertices
		try {
			//Create a Scanner object to read from the file
			Scanner graphFile = new Scanner(myFile);
			
			//While loop to read lines until the file doesn't have any left
			while(graphFile.hasNext()) {
				
				line = graphFile.next();
				
				//If a vertex is being added, get the VID and save it into the
				//vertex array-list.
				if (line.equals("vertex")) {
					vertex = graphFile.nextInt();
					vertices.add(vertex);
				}//if "vertex"
				
				//If an edge is being added, the first value 'source' will
				//represent the vertex where the edge starts, the value 'dest'
				//will represent the end vertex, and 'w' will represent the
				//weight value of the edge. These values are used to create an
				//instance of EdgeRavosa which is added to the edge array-list.
				else if (line.equals("edge")) {
					source = graphFile.nextInt();
					line = graphFile.next();
					dest = graphFile.nextInt();
					line = graphFile.next();
					w = Integer.parseInt(line);
					EdgeRavosa newEdge = new EdgeRavosa(source, dest, w);
					edges.add(newEdge);
				}//else if "edge"
				
				//Here, 'new' indicates that this graph has all the values it
				//needs and we will be moving on to the next graph. That means
				//we need to run our methods on it before we throw it away.
				//The instance of GraphRavosa is constructed, Bellman-Ford is
				//called on the graph, and the graph is cleared to restart.
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
			
			//Last graph is handled here! The same actions as are in the 'new'
			//condition occur here.
			graphNumber++;
			System.out.println("Graph " + graphNumber + ": ");
			GraphRavosa thisGraph = new GraphRavosa(edges, vertices);
			bellmanFord(thisGraph, 0);
			vertices.clear();
			edges.clear();
			
			//The file is closed since we no longer need to analyze the graphs
			graphFile.close();
			
		}//try
		//The catch will let us know if the program crashes and will print the
		//error that occurred.
		catch(Exception ex) {
			System.out.println("Oops, something went wrong: " + ex);
		}//catch
		
		//-------------------------DONE WITH GRAPHS----------------------------
		
		
		//-----------------------THE SPICE MUST FLOW---------------------------
		
		//Spices go here
		
	}//main
	
	
	//This Bellman-Ford accepts an instance of my graph class and the source
	//vertex we will be navigating from represented as an integer.
	public static void bellmanFord(GraphRavosa graph, int source) {
		//These variables store the number of vertices and edges contained in
		//the graph.
		int vertices = graph.getVertices().size();
		int edges = graph.getEdges().size();
		
		//The distance array will keep track of the total distance between the
		//source vertex and the vertex at the destination. The previous array
		//stores integers representing vertices. This will record the path to
		//be printed later.
		int distance[] = new int[vertices];
		int previous[] = new int[vertices];
		
		//InitializeSingleSource sets the distances from source to all
		//vertices to integer's maximum value.
		initializeSingleSource(graph, source, distance);
		
		//These for-loops call relax on all edges (# of vertices - 1) times
		for (int i=1; i<vertices; i++) {
			for (int j=0; j<edges; j++) {
				//Here, 'u' represents the current edge's start vertex, while
				//'v' represents its end. Weight is exactly what you'd expect.
				int u = graph.getEdges().get(j).getStartVertex();
				int v = graph.getEdges().get(j).getEndVertex();
				int weight = graph.getEdges().get(j).getWeight();
				
				//The relax function traverses the graph from the source to
				//each vertex. As it rediscovers each vertex, it updates the
				//cost it took to get there. After this is done |V|-1 times,
				//the shortest possible path should be available.
				relax(u, v, weight, distance, previous);
			}//for every edge
		}//for (# of vertices -1)
		
		//If after relaxing edges |V|-1 times, we still discover an edge
		//whose distance will decrease if you go to it, it means we have a
		//negative weight cycle. This means that the cost of getting to that
		//edge is negative infinity.
		for (int j=0; j<edges; j++) {
			int u = graph.getEdges().get(j).getStartVertex();
			int v = graph.getEdges().get(j).getEndVertex();
			int weight = graph.getEdges().get(j).getWeight();
			if (distance[u-1]!=Integer.MAX_VALUE &&
				distance[u-1]+weight<distance[v-1])
				System.out.println("Graph contains negative weight cycle!");
		}//for all edges
		
		
		//Here, we simply print the distances and the paths to each vertex in
		//pretty way. For every vertex but the source (source being the first
		//vertex here)...
		for (int i=1; i<vertices; i++) {
			//...We print the destination vertex, the cost to get there, and...
			System.out.print("-- " + 1 + " --> " + (i+1) + " cost is " +
					distance[i] + "; path: 1 --> ");
			//...the path. Formatter is used to determine where we put an
			//arrow. PrintPath is a recursive function which will print the
			//vertices represented in the previous[] array. It keeps track of
			//the VID of the destination vertex.
			formatter = i+1;
			printPath(previous, i);
			System.out.println();
		}//for # of vertices
	}//bellmanFord	
	
	public static void initializeSingleSource(GraphRavosa g, int s, int[] d) {
		//For every vertex in the graph
		for (int i=0; i<g.getVertices().size(); i++)
			//The distance array's representation of the evaluated vertex is
			//set to infinite.
			d[i] = Integer.MAX_VALUE;
		//The source vertex is set to zero since it's zero units from itself.
		d[s] = 0;
	}//initializeSingleSource
	
	public static void relax(int start, int end, int w, int[] d, int[] p){
		//If the vertex at j's distance from the start + the weight of the
		//edge about to be crossed is less than the distance from the source
		//of the vertex at the end of that edge, update the end vertex's
		//distance.
		if (d[start-1]!=Integer.MAX_VALUE &&
			d[start-1]+w<d[end-1]) {
				
			//Set the new distance of the ahead vertex to j's distance +
			//the weight of the edge between them.
			d[end-1]=d[start-1]+w;
			//Set vertex v's previous as vertex u to maintain a record of
			//the path.
			p[end-1] = start-1;
				
		}//if
	}//relax
	
	public static void printPath(int parents[], int vertex) {
		if (vertex==0)
			return;
		//Recursive call...
		printPath(parents, parents[vertex]);
		//If the vertex is not the destination vertex, we will print it with
		//an arrow. If it IS the destination vertex, we will print only it.
		if (vertex+1 != formatter)
			System.out.print(vertex+1 + " --> ");
		else
			System.out.print(vertex+1);
	}//printPath
	
}//IntergalacticReaderRavosa

/////////////////////////////////////////////////////////////////////////////////
// Title:            Find all even length paths starting from vertex u in DAG
// Files:            evenLengthPaths.java, Graph.java, PQSort.java, state.java,
//                   Vertex.java
//
// Author:           Yu Shen (Chloe)
// Email:            yus067@ucsd.edu
// Description: Given a directed acyclic graph G = (V, E) and a vertex u, design an
// algorithm that outputs all vertices S ⊆ V such that for all v ∈ S, there is an
// even length simple path from u to v in G. (A simple path is a path will all
// distinct vertices.)
/////////////////////////////////////////////////////////////////////////////////
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
public class evenLengthPaths {
	 
	public static void main(String[] args) throws FileNotFoundException{
		String inputFileName = args[0];
		String outputFileName = args[1];
		File inFile = new File(inputFileName);
		File outFile = new File(outputFileName);
		Scanner in = new Scanner(inFile);
		PrintStream out = new PrintStream(outFile);
		String numOfV = in.nextLine();
		int numOfv = Integer.parseInt(numOfV);//Number of vertices
		Graph graph = new Graph(numOfv);
		String startVertex;//Vertex u
    
		while (in.hasNext()) {
			String newLine = in.nextLine();
			String edge[] = newLine.split("->");
			Vertex src = new Vertex(edge[0]);
			// if src has neighbors
			if (edge.length > 1) {
                //Build the graph
				for (int i = 1; i < edge.length; i++) {
					Vertex dest = new Vertex(edge[i]);
					graph.addEdge(src, dest);
				}
			} else {
				if (!in.hasNext()) {
					// if src is the last line, find "vertex u",
					// run topologicalSort starting from u
					graph.topologicalSort(src);
                    
                    // get all even length paths starting from source vertex u
					List<String> result = graph.evenPathChecker(src);
                    
                    //Print output
					out.print(String.valueOf(result.size()));
					out.print("\n");
					int counter = 1;
					for(String s:result){
						if(counter == result.size()) out.print(s);
						else out.print(s+",");
						counter++;
					}
				}
				else {
					// if src has no neighbor
					graph.addEdge(src, null);
				}
			}
		
		}
		// print u
	
	}
	
	
	
	
}

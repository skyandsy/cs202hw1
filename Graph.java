/* This Graph class is for creating a graph by adding edges among vertices,
 * creating topological sorting order, and get all even length paths from u.
 * Author: Chloe
 */
import java.util.*;

public class Graph {
	private int numOfv;
	private List<Vertex> test;
	private Map<String, List<Vertex>> edges;
	private int time;
	private int layer;
    private Map<String, state> stateMap;

    /* 
     * The constructor for graph
     * @param number of vertices
     */
	public Graph(int num) {
		this.numOfv = num;
		this.edges = new HashMap<String, List<Vertex>>();
		this.stateMap = new HashMap<String, state>();
		this.time = 0;
		this.layer = 0;
	}

    /*
     * Set number of vertices
     * @param number of vertices
     */
	public void setNumofVertices(int num) {
		this.numOfv = num;
	}

    /*
     * Override the compara in priority queue
     *
     */
	static class PQsort implements Comparator<Vertex> {

		public int compare(Vertex one, Vertex two) {
			return two.getDFSEndTime() - one.getDFSEndTime();
		}
	}
    /*
     * Add an edge from source vertex to destination vertex
     * @param src, source vertex
     * @param dest, destination vertex
     */
	public void addEdge(Vertex src, Vertex dest) {
		if (!this.edges.containsKey(src.getName())) {
			List<Vertex> neighbors = new ArrayList<Vertex>();
			this.edges.put(src.getName(), neighbors);
		}
		List<Vertex> neighbors = this.edges.get(src.getName());
		if (dest != null)
			neighbors.add(dest);
	}

    /*
     * Get the children of one source vertex
     * @param src, source vertex
     */
	public List<Vertex> getChildren(Vertex src) {
		List<Vertex> children = this.edges.get(src.getName());
		if (!edges.containsKey(src.getName()))
			System.out.println("not in hashmap");
		return children;
	}
    /*
     * Get the topological sorting order of all 
     * vertices on this graph by running DFS
     * @param start, source vertex
     */
	public void topologicalSort(Vertex start) {
		if (start == null || start.isVisited())
			return;
		start.setVisit();
		start.setDFSStartTime(this.time++);
		for (Vertex v : this.getChildren(start)) {
			if (!v.isVisited()) {
				topologicalSort(v);
			}
		}
		start.setDFSEndTime(this.time++);
	}

/*	public void getTopoSort(Vertex src) {
		// TODO Auto-generated method stub
		if (src != null) {
			src.setPrintVisited();
			// System.out.println(src.getName()+"  "+src.getDFSEndTime());
			// System.out.println(src.getName()+
			// " "+this.getChildren(src).size());
			for (Vertex v : this.getChildren(src)) {
				if (!v.isPrintVisited()) {
					// System.out.println(v.getName()+"  "+v.getDFSEndTime());
					getTopoSort(v);
				}

			}

		}

	}*/

    /*
     * Find all even length paths from vertex ve running BFS
     * @param ve, source vertex
     */
	public List<String> evenPathChecker(Vertex ve) {
		String result;
		List<String> setResult = new ArrayList<String>();

		Queue<Vertex> queue1 = new LinkedList<Vertex>();
		Queue<Vertex> queue2 = new LinkedList<Vertex>();
		Queue<Vertex> qTrueOrFalse = new LinkedList<Vertex>();
		int layer = 0;
		ve.setBfsVisit();
		setResult.add(ve.getName());
		queue1.add(ve);
		stateMap.put(ve.getName(), state.TRUE);
		while (!queue1.isEmpty() || !queue2.isEmpty()) {
			while (!queue1.isEmpty()) {
				Vertex cur = queue1.remove();
					// put all children in prioity queue
					PQsort pqs = new PQsort();
					int numOfChildren = this.getChildren(cur).size();
					if (numOfChildren != 0) {
						PriorityQueue<Vertex> pqueue = new PriorityQueue<Vertex>(this.getChildren(cur).size(), pqs);
						for (Vertex c : this.getChildren(cur)) {
								pqueue.add(c);
						}
						// for all children in priority queue,
						for (Vertex i : pqueue) {
                            if(!queue2.contains(i)) queue2.add(i);
							
							if (!stateMap.containsKey(i.getName())){
								if((layer+1) % 2 == 0) {
								stateMap.put(i.getName(),state.TRUE);
								if(!setResult.contains(i.getName())){
									setResult.add(i.getName());
                                    }
								}
								else{
								stateMap.put(i.getName(), state.FALSE);
								}
							} else if (stateMap.get(i.getName()).equals(state.TRUE)
								&& (layer+1) % 2 == 1) {
								// run dfs;
								stateMap.put(i.getName(), state.TRUEORFALSE);
								if(!setResult.contains(i.getName())){ setResult.add(i.getName());
								}
							} else if (stateMap.get(i.getName()).equals(state.FALSE)
								&& (layer+1) % 2 == 0) {
								// run dfs;
								stateMap.put(i.getName(), state.TRUEORFALSE);
								if(!setResult.contains(i.getName())) {setResult.add(i.getName());
								}
							}else if(stateMap.get(i.getName()).equals(state.TRUEORFALSE)){
								for (Vertex c : this.getChildren(cur)) {	
									stateMap.put(c.getName(), state.TRUEORFALSE);
									if(!setResult.contains(c.getName())){
										setResult.add(c.getName());
									}
								}
							}
						}
					}
				
				
				}
			layer++;
			while (!queue2.isEmpty()) {
				Vertex o = queue2.remove();	
				if(!queue1.contains(o))
					queue1.add(o);
			}
			
		}
		
		return setResult;
	}

	
}

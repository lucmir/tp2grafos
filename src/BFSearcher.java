import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


/**
 * BFSearcher
 * 
 * Implements IGraphSearcher using with Breath-first
 * search based method.
 * 
 * @author lucmir
 */
public class BFSearcher implements IGraphSearcher {
    
	/**
	 * Search implementation using Breath-first search algorithm.
	 * 
	 * BFS is an uninformed search method that aims to expand and examine all
	 * nodes of a graph or combination of sequences by systematically searching
	 * through every solution.
	 * 
	 * The BFS begins at a root node and inspect all the neighboring nodes. Then
	 * for each of those neighbor nodes in turn, it inspects their neighbor
	 * nodes which were unvisited, and so on.
	 */
	@Override
	public ArrayList<ArrayList<Edge>> search(Graph graph, Integer startNode) {
		ArrayList<ArrayList<Edge>> minPaths = new ArrayList<ArrayList<Edge>>();
		for(int i=0; i<graph.getNumVertexs(); i++) {
			minPaths.add(new ArrayList<Edge>()); 
    	}
		
		Boolean visited[] = new Boolean[graph.getNumVertexs()];
		for(int i=0; i<graph.getNumVertexs(); i++) {
			visited[i] = false;
		}
		
		Queue<Integer> queue = new LinkedList<Integer>();

		// enqueue the root node
		visited[startNode] = true;
		queue.add(startNode);
		
		while(!queue.isEmpty()) {
			// dequeue a node and examine it
			Integer node = queue.poll();
			
			// if the element sought is found in this node, quit the search and
			// return a result. Otherwise enqueue any successors (the direct
			// child nodes) that have not yet been discovered.
			ArrayList<Integer> adjList = graph.getAdjList(node);
			for(Integer oppositeNode : adjList) {
				if(!visited[oppositeNode]) {
					minPaths.get(oppositeNode).addAll(minPaths.get(node));
					minPaths.get(oppositeNode).add(new Edge(node, oppositeNode));
					visited[oppositeNode] = true;
					queue.add(oppositeNode);
				}
			}
		}

		// return paths when queue is empty
		return minPaths;
	}

}
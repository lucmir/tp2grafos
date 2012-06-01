import java.util.ArrayList;
import java.util.Arrays;


/**
 * FloydWarshallSearcher
 * 
 * Implements IGraphSearcher using with Floyd–Warshall based method.
 * 
 * @author lucmir
 */
public class FloydWarshallSearcher implements IGraphSearcher {

	/**
	 * Search implementation using Floyd–Warshall algorithm.
	 * 
	 * Floyd–Warshall is a graph analysis algorithm for finding shortest paths
	 * in a weighted graph (with positive or negative edge weights).
	 * 
	 * The Floyd–Warshall algorithm compares all possible paths through the
	 * graph between each pair of vertices.
	 */
	@Override
	public ArrayList<ArrayList<Edge>> search(Graph graph, Integer startNode) {
		Integer path[][] = new Integer[graph.getNumVertexs()][graph.getNumVertexs()];
		Integer next[][] = new Integer[graph.getNumVertexs()][graph.getNumVertexs()];
		
		// init weight with 1 if edge exists else infinity
		for (int i = 0; i < graph.getNumVertexs(); i++) {
			Arrays.fill(path[i], Integer.MAX_VALUE);
			Arrays.fill(next[i], null);
			ArrayList<Integer> adjList = graph.getAdjList(i);
			for(Integer node : adjList) {
				path[i][node] = 1;
			}
		}
		
		// compute paths
		for (int k = 0; k < graph.getNumVertexs(); k++) {
			for (int i = 0; i <  graph.getNumVertexs(); i++) {
				for (int j = 0; j <  graph.getNumVertexs(); j++) {
					if(path[i][k] != Integer.MAX_VALUE && path[k][j] != Integer.MAX_VALUE) {
						Integer sum = (path[i][k]+path[k][j]);
						if( sum < path[i][j] ) {
							next[i][j] = k;
							path[i][j] = sum;
						}
					}
				}
			}
		}
		
		// get paths
		ArrayList<ArrayList<Edge>> minPaths = new ArrayList<ArrayList<Edge>>();
		for (int i = 0; i <  graph.getNumVertexs(); i++) {
			ArrayList<Edge> minPathsFromI = new ArrayList<Edge>();
			minPaths.add(minPathsFromI); 
			
			for (int j = 0; j <  graph.getNumVertexs(); j++) {
				ArrayList<Integer> pathNodeList = new ArrayList<Integer>(); 
				getMinPath(i, j, path, next, pathNodeList);
				for(int n=0; n<pathNodeList.size()-1; n++) {
					minPathsFromI.add(new Edge(pathNodeList.get(n), pathNodeList.get(n+1)));
				}
			}
			
		}
		
		return minPaths;
	}
	
	/**
	 * Get minimum path
	 * 
	 * Retrieve path recursively.
	 */
	private void getMinPath(Integer i, Integer j, Integer path[][], Integer next[][], ArrayList<Integer> minPaths) {
		if(path[i][j].equals(Integer.MAX_VALUE)) {
			return;
		}
		
		Integer intermediate = next[i][j];
		if(intermediate == null) {
			return;
		}
		else {
			getMinPath(i, intermediate, path, next, minPaths);
			minPaths.add(intermediate);
			getMinPath(intermediate, j, path, next, minPaths);
		}
	}

}

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class BFSearcher implements IGraphSearcher {
    
	@Override
	public ArrayList<ArrayList<Edge>> search(Graph graph, Integer startNode) {
		Boolean visited[] = new Boolean[graph.getNumVertexs()];
		ArrayList<ArrayList<Edge>> minPaths = new ArrayList<ArrayList<Edge>>();
		for(int i=0; i<graph.getNumVertexs(); i++) {
			minPaths.add(new ArrayList<Edge>()); 
    	}
		
		for(int i=0; i<graph.getNumVertexs(); i++) {
			visited[i] = false;
		}
		visited[startNode] = true;
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(startNode);
		
		while(!queue.isEmpty()) {
			Integer node = queue.poll();
			
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
		return minPaths;
	}

}
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class BFSearcher implements IGraphSearcher {
    
	/*
		1  procedure BFS(G,v):
		2      create a queue Q
		3      enqueue v onto Q
		4      mark v
		5      while Q is not empty:
		6          t ← Q.dequeue()
		7          if t is what we are looking for:
		8              return t
		9          for all edges e in G.incidentEdges(t) do
		10             o ← G.opposite(t,e)
		11             if o is not marked:
		12                  mark o
		13                  enqueue o onto Q
	*/
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
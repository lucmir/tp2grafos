import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Map.Entry;

/**
 * Community Searcher class
 * 
 * Class with functions for community searching. 
 * 
 * @author lucmir
 */
public class CommunitySearcher {

	IGraphSearcher searcher;
	
	
	public CommunitySearcher(IGraphSearcher searcher) {
		this.searcher = searcher;
	}
	
	
	/**
	 * Get Communities
	 * 
	 * @return number of communities
	 */
	private Integer getCommunities(Graph graph, Map<Integer, Integer> nodeCommunityMap) {
		Boolean visited[] = new Boolean[graph.getNumVertexs()];
		Integer startNode = 0;
		for(int i=0; i<graph.getNumVertexs(); i++) {
			visited[i] = false;
			
			// choose start node
			if(graph.getAdjList(i).size() > 0) {
				startNode = i;
			}
		}
		
		visited[startNode] = true;
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(startNode);
		
		Integer communityCount = 0;
		
		while(true) {
			if(queue.isEmpty()) {
				communityCount++;
				
				// check if all nodes were visited
				Boolean allVisited = true;
				for(int i=0; i<visited.length; i++) {
					if(!visited[i]) {
						allVisited = false;
						queue.add(i);
						visited[i] = true;
						break;
					}
				}
				
				if(allVisited) {
					break;
				}
			}
			
			Integer node = queue.poll();
			
			nodeCommunityMap.put(node, communityCount);
			
			ArrayList<Integer> adjList = graph.getAdjList(node);
			for(Integer oppositeNode : adjList) {
				if(!visited[oppositeNode]) {
					visited[oppositeNode] = true;
					queue.add(oppositeNode);
				}
			}
		}
		
		return communityCount;
	}
	
	/**
	 * Find Communities
	 * 
	 * @param	number of communities
	 * @param	graph
	 *  
	 * @return	node community map
	 */
	public Map<Integer, Integer> findCommunities(Graph graph, Integer numCommunities) {
		numCommunities = (numCommunities > 0) ? 
				( (numCommunities <= graph.getNumVertexs()) ? 
						numCommunities : 
						graph.getNumVertexs()
				) : 1;
				
		Map<Integer, Integer> nodeCommunityMap = new HashMap<Integer, Integer>();
		for(Integer node=0; node<graph.getNumVertexs(); node++) {
			nodeCommunityMap.put(node, 1);
		}
		
		while(true) {
			
			// get communities and check stop condition
			Integer communitiesCount = this.getCommunities(graph, nodeCommunityMap);
			if(communitiesCount == numCommunities) {
				return nodeCommunityMap;
			}
			
			Map<Edge, Integer> edgeBetweennessMap = searcher.getBetweeness(graph);
			
			// get the edge with the highest betweenness
			Integer highestBetweeness = 0;
			Edge highestBetweenessEdge = null;
			for(Entry<Edge, Integer> entry : edgeBetweennessMap.entrySet()) {
				if(entry.getValue() > highestBetweeness) {
					highestBetweenessEdge = entry.getKey();
					highestBetweeness = entry.getValue();
				}
			}
			
			// remove the edge with the highest betweenness
			graph.removeEdge(highestBetweenessEdge.getVertexA(), highestBetweenessEdge.getVertexB());
			graph.removeEdge(highestBetweenessEdge.getVertexB(), highestBetweenessEdge.getVertexA());
		
		}
		
	}

}

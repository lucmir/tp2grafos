import java.util.ArrayList;


/**
 * Class Graph
 * 
 * Graph representation.
 */
public class Graph {
    
    private ArrayList<ArrayList<Integer>> adjList;

    
    public Graph(Integer numVertex) {
    	adjList = new ArrayList<ArrayList<Integer>>();
    	for(int i=0; i<numVertex; i++) {
    		adjList.add(new ArrayList<Integer>()); 
    	}
    }

    public void addEdge(Integer i, Integer j) {
    	adjList.get(i).add(j);
    	if(i < j) {
    		addEdge(j, i);
    	}
    }
    
    public ArrayList<Integer> getAdjList(Integer i) {
    	if(i < adjList.size()) {
    		return adjList.get(i);
    	}
    	return null;
    }
    
	public void removeEdge(Integer i, Integer j) {
		ArrayList<Integer> adjNodes = this.getAdjList(i);
		if (adjNodes != null) {
			int index = adjNodes.indexOf(j);
			if (index > -1) {
				adjNodes.remove(index);
			}
		}
	}
    
    public Integer getNumVertexs() {
    	return this.adjList.size();
    }

	@Override
	public String toString() {
		return "Graph [adjList=" + adjList + "]";
	}
    
}
import java.util.ArrayList;

/**
 * IGraphSearcher interface
 * 
 * @author lucmir
 */
public interface IGraphSearcher {
	
	public ArrayList<ArrayList<Edge>> search(Graph graph, Integer startNode);

}

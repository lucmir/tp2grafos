import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * IGraphSearcher interface
 * 
 * @author lucmir
 */
public interface IGraphSearcher {
	
	public Map<Edge, Integer> getBetweeness(Graph graph);
	
}

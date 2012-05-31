import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.prefs.BackingStoreException;


public class Main {

	private static final String HELP_MSG = "Parâmetros inválidos.\n\n\tParâmetros corretos:\n\t\t"
			+ "<arquivo_arestas> <arquivo_estilos> <numClusters> <numVertices>";
	
	
	public static void main(String[] args) throws IOException {
		// parse parameters. format:
		// <edges_file> <categories_file> <numClusters> <numVertexs>
		Integer numVertexs = 34;
		Graph graph = readGraph("/home/lucmir/development/workspace/tp2grafos/inputs/karate.dat", numVertexs);
		
		IGraphSearcher searcher = new BFSearcher();
		CommunitySearcher communitySearcher = new CommunitySearcher(searcher);
		Map<Integer, Integer> nodeCommunityMap = communitySearcher.findCommunities(graph, -1);

		System.out.println(nodeCommunityMap);
	}
	
	/**
	 * Read Graph
	 * 
	 * @param filename with lines in the format:
	 *            node_id_1, node_id_2, distance(node_id_1, node_id_2)
	 * @return Graph object
	 * @throws IOException
	 */
	private static Graph readGraph(String filename, Integer numVertexs) throws IOException {
		Graph graph = new Graph(numVertexs);
		
		FileReader input = new FileReader(filename);
		BufferedReader bufRead = new BufferedReader(input);
		
		String line = bufRead.readLine();
		while(line != null) {
			String[] columns = line.split(" ");
			
			Integer i = Integer.valueOf(columns[0]);
			Integer j = Integer.valueOf(columns[1]);
			graph.addEdge(i, j);

			line = bufRead.readLine();
		}
		
		bufRead.close();
		
		return graph;
	}
	
}

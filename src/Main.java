import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;


public class Main {

	private static final String HELP_MSG = "Parâmetros inválidos.\n\n\tParâmetros corretos:\n\t\t"
			+ "<arquivo_arestas> <num_comunidades> <num_vertices> <algoritmo: b|f>";
	
	
	public static void main(String[] args) throws IOException {
		String edgesFile = null;
		Integer numComunities = 0, numVertexs = 0;
		String algorithm = null;
		
		// read parameters
		if (args.length > 3) {
		    try {
		    	edgesFile = args[0];
		    	numComunities = Integer.parseInt(args[1]);
		    	numVertexs = Integer.parseInt(args[2]);
		    	algorithm = args[3];
		    } catch (NumberFormatException e) {
		        System.out.println(HELP_MSG);
		        System.exit(0);
		    }
		}
		else {
			System.out.println(HELP_MSG);
			System.exit(0);
		}
		
		// build graph
		Graph graph = readGraph(edgesFile, numVertexs);
		
		// set algorithm
		IGraphSearcher searcher = null;
		if(algorithm.startsWith("b")) {
			searcher = new BFSearcher();
		}
		else if(algorithm.startsWith("f")) {
			searcher = new FloydWarshallSearcher();
		}
		else {
		    	System.out.println(HELP_MSG);
				System.exit(0);
		}
		
		// find communities
		CommunitySearcher communitySearcher = new CommunitySearcher(searcher);
		Map<Integer, Integer> nodeCommunityMap = communitySearcher.findCommunities(graph, numComunities);

		// print communities of nodes
		for(Entry<Integer, Integer> entry : nodeCommunityMap.entrySet()) {
			System.out.println(entry.getKey() + " " + (entry.getValue() + 1));
		}
		
	}
	
	/**
	 * Read Graph
	 * 
	 * @param filename with lines in the format:
	 *            node_id_1, node_id_2, distance(node_id_1, node_id_2)
	 * @param number of nodes
	 * 
	 * @return Graph object
	 * 
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

import searchalgorithm.Algorithms;
import searchalgorithm.Node;
import undirectedgraph.Graph;

public class Main {

	public static void main(String[] args) {
	
		Graph graph = new Graph(); 
        graph.defineGraphRomenia(); 
        graph.showLinks(); 
        graph.showSets(); 
        Node n; 
       /* n = graph.searchSolution("Timisoara", "Neamt", Algorithms.BreadthFirstSearch);
        graph.showSolution(n);
        graph.reset();
        n = graph.searchSolution("Timisoara", "Neamt", Algorithms.DepthFirstSearch);
        graph.showSolution(n);   
        graph.reset();
        n = graph.searchSolution("Timisoara", "Neamt", Algorithms.UniformCostSearch);
        graph.showSolution(n);   
        graph.reset();
        n = graph.searchSolution("Timisoara", "Neamt", Algorithms.GreedySearch);
        graph.showSolution(n);   
        graph.reset();
        n = graph.searchSolution("Timisoara", "Neamt", Algorithms.AStarSearch);
        graph.showSolution(n);
        
    */
        
        
      /*n = graph.searchSolutionWithRegion("Arad", "Bucharest", "Dobrogea", Algorithms.DepthFirstSearch);
      graph.showSolution(n);
      
      */
        String[] regions = {"Dobrogea", "Banat","Muntenia"};
        n = graph.searchSolutionWithRegionSet("Arad", "Bucharest", regions, Algorithms.AStarSearch);
        graph.showSolution(n);
        
	}

}

package undirectedgraph;

import java.util.*;
import searchalgorithm.*;
import searchproblem.SearchProblem;
import searchproblem.State;

public class Graph {
	private HashMap<String, Vertex> vertices;
	private HashMap<Integer, Edge> edges;
	private ArrayList<VertexSet> vSets;

	private long expansions;
	private long generated;
	private long repeated;
	private double time;

	public Graph() {
		this.vertices = new HashMap<String, Vertex>();
		this.edges = new HashMap<Integer, Edge>();
		this.vSets = new ArrayList<VertexSet>();
		this.expansions = 0;
		this.generated = 0;
		this.repeated = 0;
		this.time = 0;
	}

	public void reset() {
		this.expansions = 0;
		this.generated = 0;
		this.repeated = 0;
		this.time = 0;

	}

	public void addVertice(String label, double lat, double lng) {
		Vertex v = new Vertex(label);
		this.vertices.put(label, v);
		v.setCoordinates(lat, lng);
	}

	public Vertex getVertice(String label) {
		return this.vertices.get(label);
	}

	public void addVerticeSet(String label) {
		VertexSet vSet = new VertexSet(label);
		this.vSets.add(vSet);
	}

	public VertexSet getVerticeSet(String setLabel) {
		for (VertexSet vSet : vSets) {
			if (vSet.getLabel() == setLabel)
				return vSet;
		}
		return null;
	}

	public void addVerticeToSet(String labelSet, String labelVertex) {
		Vertex v = this.vertices.get(labelVertex);
		for (VertexSet vSet : vSets) {
			if (vSet.getLabel() == labelSet) {
				vSet.addVertice(v);
				break;
			}
		}
	}

	public boolean addEdge(Vertex one, Vertex two, double weight) {
		if (one.equals(two))
			return false;
		Edge e = new Edge(one, two, weight);
		if (edges.containsKey(e.hashcode()))
			return false;
		if (one.containsNeighbor(e) || two.containsNeighbor(e))
			return false;
		edges.put(e.hashcode(), e);
		one.addNeighbor(e);
		two.addNeighbor(e);
		return true;
	}

	public boolean addEdge(String oneLabel, String twoLabel, double weight) {
		Vertex one = getVertice(oneLabel);
		Vertex two = getVertice(twoLabel);
		return addEdge(one, two, weight);
	}

	public boolean addEdge(String oneLabel, String twoLabel) {
		Vertex one = getVertice(oneLabel);
		Vertex two = getVertice(twoLabel);
		return addEdge(one, two, one.straightLineDistance(two));
	}

	public void defineGraphRomenia() {
		// Define cities:
		addVertice("Arad", 46.18333, 21.31667);
		addVertice("Bucharest", 44.43225, 26.10626);
		addVertice("Craiova", 44.33018, 23.79488);
		addVertice("Dobreta", 44.63692, 22.65973);
		addVertice("Eforie", 44.05842, 28.63361);
		addVertice("Fagaras", 45.84164, 24.97310);
		addVertice("Giurgiu", 43.90371, 25.96993);
		addVertice("Hirsova", 44.68935, 27.94566);
		addVertice("Iasi", 47.15845, 27.60144);
		addVertice("Lugoj", 45.69099, 21.90346);
		addVertice("Mehadia", 44.90411, 22.36452);
		addVertice("Neamt", 46.97587, 26.38188);
		addVertice("Oradea", 47.04650, 21.91894);
		addVertice("Pitesti", 44.85648, 24.86918);
		addVertice("R. Vilcea", 45.09968, 24.36932);
		addVertice("Sibiu", 45.79833, 24.12558);
		addVertice("Timisoara", 45.74887, 21.20868);
		addVertice("Urziceni", 44.71653, 26.64112);
		addVertice("Vaslui", 46.64069, 27.72765);
		addVertice("Zerind", 46.62251, 21.51742);
		// Define routes:
		addEdge("Arad", "Sibiu");
		addEdge("Arad", "Timisoara");
		addEdge("Arad", "Zerind");
		addEdge("Bucharest", "Fagaras");
		addEdge("Bucharest", "Giurgiu");
		addEdge("Bucharest", "Pitesti");
		addEdge("Bucharest", "Urziceni");
		addEdge("Craiova", "Dobreta");
		addEdge("Craiova", "Pitesti");
		addEdge("Craiova", "R. Vilcea");
		addEdge("Dobreta", "Mehadia");
		addEdge("Eforie", "Hirsova");
		addEdge("Fagaras", "Sibiu");
		addEdge("Hirsova", "Urziceni");
		addEdge("Iasi", "Neamt");
		addEdge("Iasi", "Vaslui");
		addEdge("Lugoj", "Mehadia");
		addEdge("Lugoj", "Timisoara");
		addEdge("Oradea", "Sibiu");
		addEdge("Oradea", "Zerind");
		addEdge("Pitesti", "R. Vilcea");
		addEdge("R. Vilcea", "Sibiu");
		addEdge("Urziceni", "Vaslui");
		// Define regions:
		addVerticeSet("Banat");
		addVerticeToSet("Banat", "Lugoj");
		addVerticeToSet("Banat", "Mehadia");
		addVerticeToSet("Banat", "Timisoara");
		addVerticeSet("Crisana");
		addVerticeToSet("Crisana", "Arad");
		addVerticeToSet("Crisana", "Oradea");
		addVerticeToSet("Crisana", "Zerind");
		addVerticeSet("Dobrogea");
		addVerticeToSet("Dobrogea", "Eforie");
		addVerticeToSet("Dobrogea", "Hirsova");
		addVerticeSet("Moldova");
		addVerticeToSet("Moldova", "Iasi");
		addVerticeToSet("Moldova", "Neamt");
		addVerticeToSet("Moldova", "Vaslui");
		addVerticeSet("Muntenia");
		addVerticeToSet("Muntenia", "Bucharest");
		addVerticeToSet("Muntenia", "Giurgiu");
		addVerticeToSet("Muntenia", "Pitesti");
		addVerticeToSet("Muntenia", "Urziceni");
		addVerticeSet("Oltenia");
		addVerticeToSet("Oltenia", "Craiova");
		addVerticeToSet("Oltenia", "Dobreta");
		addVerticeToSet("Oltenia", "R. Vilcea");
		addVerticeSet("Transilvania");
		addVerticeToSet("Transilvania", "Fagaras");
		addVerticeToSet("Transilvania", "Sibiu");
	}

	public void showLinks() {
		System.out.println("********************* LINKS *********************");
		for (Vertex current : vertices.values()) {
			System.out.print(current + ": ");
			for (Edge e : current.getNeighbors()) {
				System.out.print(e.getNeighbor(current) + " (" + e.getWeight() + "); ");
			}
			System.out.println();
		}
		System.out.println("*************************************************");
	}

	public void showSets() {
		System.out.println("********************* SETS *********************");
		for (VertexSet vSet : vSets) {
			System.out.println(vSet);
		}
		System.out.println("*************************************************");
	}

	public Node searchSolution(String initLabel, String goalLabel, Algorithms algID) {
		State init = new State(this.getVertice(initLabel));
		State goal = new State(this.getVertice(goalLabel));
		SearchProblem prob = new SearchProblem(init, goal);
		SearchAlgorithm alg = null;
		switch (algID) {
		case BreadthFirstSearch:
			alg = new BreadthFirstSearch(prob);
			break;
		case DepthFirstSearch:
			alg = new DepthFirstSearch(prob);
			break;
		case UniformCostSearch:
			alg = new UniformCostSearch(prob);
			break;
		case GreedySearch:
			alg = new GreedySearch(prob);
			break;
		case AStarSearch:
			alg = new AStarSearch(prob);
			break;
		default:
			System.out.println("ERROR: algorithm not implemented!");
		}
		Node n = alg.searchSolution();
		Map<String, Number> m = alg.getMetrics();
		this.expansions += (long) m.get("Node Expansions");
		this.generated += (long) m.get("Nodes Generated");
		this.repeated += (long) m.get("State repetitions");
		this.time += (double) m.get("Runtime (ms)");
		return n;
	}

	public Node searchSolutionWithRegion(String initLabel, String goalLabel, String region, Algorithms algID) {

		Graph graph2 = new Graph();

		Vertex tempV = this.getVertice(initLabel);
		graph2.addVertice(tempV.getLabel(), tempV.getLatitude(), tempV.getLongitude());

		tempV = this.getVertice(goalLabel);
		graph2.addVertice(tempV.getLabel(), tempV.getLatitude(), tempV.getLongitude());

		VertexSet vset = this.getVerticeSet(region);
		HashSet<Vertex> citiesInRegion = vset.getVertices();

		for (Vertex city : citiesInRegion) {
			graph2.addVertice(city.getLabel(), city.getLatitude(), city.getLongitude());
			graph2.addEdge(initLabel, city.getLabel(),
					this.searchSolution(initLabel, city.getLabel(), algID).getPathCost());
			graph2.addEdge(city.getLabel(), goalLabel, this.searchSolution(initLabel, goalLabel, algID).getPathCost());

		}

		return graph2.searchSolution(initLabel, goalLabel, algID);
	}

	public Node searchSolutionWithRegionSet(String initLabel, String goalLabel, String[] regions, Algorithms algID) {
		Graph graph2 = new Graph();
		Vertex overtex[] = new Vertex[20];
		int oldCounter = 0;
		int counter = 0;

		overtex[counter++] = this.getVertice(initLabel);
		graph2.addVertice(overtex[0].getLabel(), overtex[0].getLatitude(), overtex[0].getLongitude());
		for (int i = 0; i < regions.length; i++) {
			oldCounter = counter;
			counter = 0;
			VertexSet vset = this.getVerticeSet(regions[i]);
			HashSet<Vertex> citiesInRegion = vset.getVertices();

			for (Vertex city : citiesInRegion) {
				graph2.addVertice(city.getLabel(), city.getLatitude(), city.getLongitude());
				for (int a = 0; a < oldCounter; a++) {
					graph2.addEdge(overtex[a].getLabel(), city.getLabel(),
							this.searchSolution(overtex[a].getLabel(), city.getLabel(), algID).getPathCost());
				}
			}

			for (Vertex city2 : citiesInRegion) {
				overtex[counter++] = city2;
			}

		}

		Vertex lastVertex = getVertice(goalLabel);
		graph2.addVertice(lastVertex.getLabel(), lastVertex.getLatitude(), lastVertex.getLongitude());

		for (int y = 0; y < counter; y++) {
			graph2.addEdge(overtex[y].getLabel(), lastVertex.getLabel(),
					searchSolution(overtex[y].getLabel(), lastVertex.getLabel(), Algorithms.AStarSearch).getPathCost());
		}

		return graph2.searchSolution(initLabel, goalLabel, algID);

	}

	public void showSolution(Node n) {
		System.out.println("******************* SOLUTION ********************");
		System.out.println("Node Expansions: " + this.expansions);
		System.out.println("Nodes Generated: " + this.generated);
		System.out.println("State Repetitions: " + this.repeated);
		System.out.printf("Runtime (ms): %6.3f \n", this.time);
		Node ni = null;
		List<Object> solution = n.getPath();
		double dist = 0;
		for (int i = 0; i < solution.size() - 1; i++) {
			System.out.printf("| %-9s | %4.0f | ", solution.get(i), dist);
			ni = searchSolution(solution.get(i).toString(), solution.get(i + 1).toString(), Algorithms.AStarSearch);
			System.out.print(ni.getPath());
			System.out.println(" -> " + (int) ni.getPathCost());
			dist += ni.getPathCost();
		}
		System.out.printf("| %-9s | %4.0f | \n", solution.get(solution.size() - 1), dist);
		System.out.println("*************************************************");
	}

}

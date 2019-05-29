package fr.um3.grapheproject.utilitygraphe;

import java.util.ArrayList;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultUndirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import prog.algo.scraper.Scraper;

public class OutilsGraphe {
	
	
	static ArrayList<String> BigSources = Scraper.getSources();
	static ArrayList<String> BigData = Scraper.getRoads2(BigSources);
	static ArrayList<String> BigVilles = Scraper.setVilles(BigData);
	static ArrayList<Node> BigSommetVilles = Scraper.setCityObject(BigVilles);
	
	public static Graph <Node,DefaultWeightedEdge> BigGraphe = getData();
	
	static ArrayList<String> SmallSources = Scraper.getSmallSources();
	static ArrayList<String> SmallData = Scraper.getRoads2(SmallSources);
	static ArrayList<String> SmallVilles = Scraper.setVilles(SmallData);
	static ArrayList<Node> SmallSommetVilles = Scraper.setCityObject(SmallVilles);
	
	static DefaultUndirectedWeightedGraph<Node,DefaultWeightedEdge> UndirectedGraph = createUndirectedGraph(getData());
	
	static Graph <Node,DefaultWeightedEdge> SmallGraphe = getData();
	
	public static Graph<Node,DefaultWeightedEdge> getData() {
		Graph<Node,DefaultWeightedEdge> BigGraphe = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		Scraper.addToGraph(BigGraphe,BigSommetVilles);
		ArrayList<DefaultWeightedEdge> distances = Scraper.setDistances(BigGraphe, BigData, BigSommetVilles);
		return BigGraphe;
	}
	
	public static SimpleDirectedWeightedGraph<Node, DefaultWeightedEdge> getSimpleDirectedWeightedGraphData1(){
		SimpleDirectedWeightedGraph<Node,DefaultWeightedEdge> sdg = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		Scraper.addToGraph(sdg,BigSommetVilles);
		ArrayList<DefaultWeightedEdge> distances = Scraper.setDistances(sdg, BigData, BigSommetVilles);
		return sdg;
	}
	
	public static Graph<Node,DefaultWeightedEdge> getDirectedData() {
		Graph<Node,DefaultWeightedEdge> graphe = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		ArrayList<String> sources = Scraper.getSources();
		ArrayList<String> data = Scraper.getRoads2(sources);
		ArrayList<String> villes = Scraper.setVilles(data);
		ArrayList<Node> sommetVilles = Scraper.setCityObject(villes);
		Scraper.addToGraph(graphe,sommetVilles);
		ArrayList<DefaultWeightedEdge> distances = Scraper.setDistances(graphe, data, sommetVilles);
		return graphe;
	}
		
	public static Graph<Node,DefaultWeightedEdge> getSmallDirectedData() {
		Graph<Node,DefaultWeightedEdge> SmallGraphe = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		Scraper.addToGraph(SmallGraphe,SmallSommetVilles);
		ArrayList<DefaultWeightedEdge> distances = Scraper.setDistances(SmallGraphe, SmallData, SmallSommetVilles);
		return SmallGraphe;
	}	

	public static void createEdge(Graph<Node, DefaultWeightedEdge> g, Node n1, Node n2, float weight) {
		DefaultWeightedEdge edge = new DefaultWeightedEdge();
		g.addEdge(n1, n2, edge);
		g.setEdgeWeight(edge, weight);
	}
	
	public static DefaultUndirectedWeightedGraph createUndirectedGraph(Graph<Node, DefaultWeightedEdge> g) {
		DefaultUndirectedWeightedGraph<Node, DefaultWeightedEdge> Res = new DefaultUndirectedWeightedGraph<>(
				DefaultWeightedEdge.class);
		for (Node n : g.vertexSet()) {
			Res.addVertex(n);
		}
		for (Node n : g.vertexSet()) {
			for (DefaultWeightedEdge e : g.outgoingEdgesOf(n)) {
				Res.addEdge(g.getEdgeSource(e), g.getEdgeTarget(e), e);
			}
		}
		System.out.println("testUndirectedGraphOk");
		return Res;
	}

	
	public static ArrayList<String> getBigSources() {
		return BigSources;
	}

	public static ArrayList<String> getBigData() {
		return BigData;
	}

	public static ArrayList<String> getBigVilles() {
		return BigVilles;
	}

	public static ArrayList<Node> getBigSommetVilles() {
		return BigSommetVilles;
	}

	public static Graph<Node, DefaultWeightedEdge> getBigGraphe() {
		return BigGraphe;
	}

	public static ArrayList<String> getSmallSources() {
		return SmallSources;
	}

	public static ArrayList<String> getSmallData() {
		return SmallData;
	}

	public static ArrayList<String> getSmallVilles() {
		return SmallVilles;
	}

	public static ArrayList<Node> getSmallSommetVilles() {
		return SmallSommetVilles;
	}

	public static Graph<Node, DefaultWeightedEdge> getSmallGraphe() {
		return SmallGraphe;
	}
	
}

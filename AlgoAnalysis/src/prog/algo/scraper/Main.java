package prog.algo.scraper;

import java.util.ArrayList;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import fr.um3.grapheproject.utilitygraphe.Node;

public class Main {
	
	static Graph<Node,DefaultWeightedEdge> graphe = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);

	public static void main(String[] args) {

		ArrayList<String> sources = Scraper.getSources();
		ArrayList<String> data = Scraper.getRoads2(sources);
		ArrayList<String> villes = Scraper.setVilles(data);
		ArrayList<Node> sommetVilles = Scraper.setCityObject(villes);
		Scraper.addToGraph(graphe,sommetVilles);
		ArrayList<DefaultWeightedEdge> distances = Scraper.setDistances(graphe, data, sommetVilles);
		Scraper.setDistances(graphe, data, sommetVilles);
	
	/* Pour enlever l'affichange dans la console => commentez les lignes 190, 62 et 72 de la classe Scraper */
	}
}

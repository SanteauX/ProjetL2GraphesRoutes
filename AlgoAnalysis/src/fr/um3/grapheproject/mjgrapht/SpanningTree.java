package fr.um3.grapheproject.mjgrapht;


import java.util.ArrayList;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.SpanningTreeAlgorithm;
import org.jgrapht.alg.spanning.BoruvkaMinimumSpanningTree;
import org.jgrapht.graph.DefaultWeightedEdge;

import fr.um3.grapheproject.utilitygraphe.Node;
import fr.um3.grapheproject.utilitygraphe.OutilsGraphe;
import prog.algo.scraper.Scraper;

public class SpanningTree {

	static Graph<Node,DefaultWeightedEdge> graphe = OutilsGraphe.getDirectedData();
	
	public class Cycles{

	}
	
	public static void main(String[] args) {
		
		ArrayList<String> sources = Scraper.getSources();
		ArrayList<String> data = Scraper.getRoads2(sources);
		ArrayList<String> villes = Scraper.setVilles(data);
		ArrayList<Node> sommetVilles = Scraper.setCityObject(villes);
		Scraper.addToGraph(graphe,sommetVilles);
		ArrayList<DefaultWeightedEdge> distances = Scraper.setDistances(graphe, data, sommetVilles);
		 
		// je cherche ici l'arbre de poids (distance) minimum dans le graphe du projet.
		BoruvkaMinimumSpanningTree<Node,DefaultWeightedEdge>graphPoidMinimum = new BoruvkaMinimumSpanningTree<Node,DefaultWeightedEdge>(graphe);
		SpanningTreeAlgorithm.SpanningTree<DefaultWeightedEdge> arbrePoidsMin = graphPoidMinimum.getSpanningTree();
		System.out.println(arbrePoidsMin); 
	}

}

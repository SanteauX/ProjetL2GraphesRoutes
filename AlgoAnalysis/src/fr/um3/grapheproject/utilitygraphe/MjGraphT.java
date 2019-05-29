package fr.um3.grapheproject.utilitygraphe;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.alg.cycle.HawickJamesSimpleCycles;
import org.jgrapht.graph.DefaultWeightedEdge;

import prog.algo.scraper.Scraper;

public class MjGraphT {
	
	static void main(String[] args) {

	// Classe d'application de méthodes de jGraphT existantes
	
	Graph<Node,DefaultWeightedEdge> graphe = OutilsGraphe.getDirectedData();
	ArrayList<String> sources = Scraper.getSources();
	ArrayList<String> data = Scraper.getRoads2(sources);
	ArrayList<String> villes = Scraper.setVilles(data);
	ArrayList<Node> sommetVilles = Scraper.setCityObject(villes);
	ArrayList<DefaultWeightedEdge> distances = Scraper.setDistances(graphe, data, sommetVilles);

	Node source = sommetVilles.get(34);
	Node sink = sommetVilles.get(45);

	//System.out.println("------ Algorithme de Dijkstra de jGraphT ------");
	//Graph <Node,DefaultWeightedEdge> fromAtoB = findPathBetween(graphe, source, sink);
	
	HawickJamesSimpleCycles<Node,DefaultWeightedEdge>grapheCycleSimple = new HawickJamesSimpleCycles<Node, DefaultWeightedEdge>(graphe);
	List<List<Node>>listeCycleSimple = grapheCycleSimple.findSimpleCycles();
	System.out.println(listeCycleSimple); 
	// on listeCycleSimple est vide donc notre graphe ne contient pas de cycle simple.
	// pour bien verifier que la liste est vide, je vais compter le nombre de cycle present dans le graphe.
	
	double nombreCycleSimple = grapheCycleSimple.countSimpleCycles();
	System.out.println(nombreCycleSimple);
	/** on a bien 0 cycle simple dans notre graphe.
	*je cherche maintenant des cycles pas simple dans mon graphe
	**/
	CycleDetector<Node,DefaultWeightedEdge>CycleComplexe = new CycleDetector<Node,DefaultWeightedEdge>(graphe);
	//System.out.println(CycleComplexe); // again i know i cant just print this type like that
	Set<Node>setCycle = CycleComplexe.findCycles();
	System.out.println(setCycle);
	// apparament pas de cycle
	
	if( CycleComplexe.detectCycles() == false){
		System.out.println("il n'y a pas de cycle dans le graphe du projet");
		}
	}
	
}

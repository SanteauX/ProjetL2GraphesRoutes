package prog.algo.scraper;

import java.util.ArrayList;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import fr.um3.grapheproject.utilitygraphe.Node;

public class TestScraper {
	
	public static void showSources(ArrayList<String> sources) {
		System.out.println("Sources:\n");
		for(int i = 0; i < sources.size(); i++) {
			System.out.println(sources.get(i));
		}
	}

	public static void showRawData(ArrayList<String> data) {
		System.out.println("RawData: "+data.toString()+"\n");
		for(int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i));
		}
	}

	public static void showVilles(ArrayList<String> villes) {
		System.out.println("Liste des villes: "+villes.toString()+"\n");
		for(int i = 0; i < villes.size(); i++) {
			System.out.println(villes.get(i)+"\n");
		}
	}
	
	public static void showSommetVilles(ArrayList<Node> sommetVilles) {
		System.out.println("Affichage des Villes-Sommets:");
		for(int i = 0; i < sommetVilles.size(); i++) {
			System.out.println("Sommets n°"+sommetVilles.get(i).getId()+"\nVille: "+sommetVilles.get(i).getVille()+"\n");
		}
	}
	
	public static void showDistances(ArrayList<DefaultWeightedEdge> distances) {
		System.out.println("Affichage des Distances-Arrêtes:");
		for(int i = 0; i < distances.size(); i++) {
			System.out.println("Arête n°"+i+"\n"+distances.get(i).toString()+"\n");
		}
	}
	
	public static void main(String[] args) {
		
		Graph<Node,DefaultWeightedEdge> graphe = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		ArrayList<String> sources = Scraper.getSources();
		ArrayList<String> data = Scraper.getRoads2(sources);
		ArrayList<String> villes = Scraper.setVilles(data);
		ArrayList<Node> sommetVilles = Scraper.setCityObject(villes);
		Scraper.addToGraph(graphe,sommetVilles);
		Scraper.setDistances2(graphe, data, sommetVilles);
		ArrayList<DefaultWeightedEdge> distances = Scraper.setDistances(graphe, data, sommetVilles);
		
		System.out.println("Affichage des données récupérées par le Scraper"+"\n");	
		showSources(sources);
		showRawData(data);
		showVilles(villes);
		showSommetVilles(sommetVilles);
		showDistances(distances);

	
	/* Pour enlever l'affichange dans la console => commentez les lignes 190, 62 et 72 de la classe Scraper */
	}
}

package fr.um3.grapheproject.mjgrapht;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.tour.TwoApproxMetricTSP;
import org.jgrapht.graph.DefaultUndirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import fr.um3.grapheproject.utilitygraphe.Node;
import fr.um3.grapheproject.utilitygraphe.OutilsGraphe;

public class SalesMan {
	static Graph<Node,DefaultWeightedEdge> graphe = OutilsGraphe.getSmallDirectedData();
	static DefaultUndirectedWeightedGraph<Node, DefaultWeightedEdge> grapheNonOriente = OutilsGraphe.createUndirectedGraph(graphe);

	public static void main(String[] args) {
		TwoApproxMetricTSP<Node,DefaultWeightedEdge>grapheSMA = new TwoApproxMetricTSP<Node,DefaultWeightedEdge>();
		GraphPath<Node, DefaultWeightedEdge>test = grapheSMA.getTour(grapheNonOriente);
		System.out.println(test);
		//Set<DefaultWeightedEdge> cheminsGraphe = grapheNonOriente.getAllEdges(Node sourceVertex,Node targetVertex);
		//System.out.println(grapheNonOriente);
		double distanceAParcourir = test.getWeight();
		System.out.println("la distance totale a parcourir est: "+ distanceAParcourir+" km");
	}

}

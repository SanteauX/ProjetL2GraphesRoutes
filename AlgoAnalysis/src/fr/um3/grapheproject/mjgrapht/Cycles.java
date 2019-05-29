package fr.um3.grapheproject.mjgrapht;

import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.alg.cycle.HawickJamesSimpleCycles;
import org.jgrapht.graph.DefaultWeightedEdge;

import fr.um3.grapheproject.utilitygraphe.Node;
import fr.um3.grapheproject.utilitygraphe.OutilsGraphe;


public class Cycles {

	public static void main(String[] args) {

			Graph<Node,DefaultWeightedEdge> graphe = OutilsGraphe.getDirectedData();
			
			HawickJamesSimpleCycles<Node,DefaultWeightedEdge> grapheCycleSimple =
					new HawickJamesSimpleCycles<Node, DefaultWeightedEdge>(OutilsGraphe.getBigGraphe());
			List<List<Node>>listeCycleSimple = grapheCycleSimple.findSimpleCycles();
			System.out.println(listeCycleSimple); 
			// on listeCycleSimple est vide donc notre graphe ne contient pas de cycle simple.
			// pour bien verifier que la liste est vide, je vais compter le nombre de cycle present dans le graphe.
			
			double nombreCycleSimple = grapheCycleSimple.countSimpleCycles();
			System.out.println(nombreCycleSimple);
			/** on a bien 0 cycle simple dans notre graphe.
			*je cherche maintenant des cycles pas simple dans mon graphe
			*/
			CycleDetector<Node,DefaultWeightedEdge>CycleComplexe =
					new CycleDetector<Node,DefaultWeightedEdge>(OutilsGraphe.getBigGraphe());
			//System.out.println(CycleComplexe); // again i know i cant just print this type like that
			Set<Node>setCycle = CycleComplexe.findCycles();
			System.out.println(setCycle);
			// apparament pas de cycle
			
			if(!CycleComplexe.detectCycles()){
				System.out.println("il n'y a pas de cycle dans le graphe du projet");
			}
	}
}

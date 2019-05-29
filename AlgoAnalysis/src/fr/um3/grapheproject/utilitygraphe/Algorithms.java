package fr.um3.grapheproject.utilitygraphe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.alg.cycle.PatonCycleBase;
import org.jgrapht.graph.DefaultUndirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class Algorithms {
	
	public static void ShortestPaths(SimpleDirectedWeightedGraph<Node, DefaultWeightedEdge> g, int id, int ida) { /*Cod� par Maximilien Servajean*/
		List<Node> S = new ArrayList<Node>(); 
		Node p = null;
		for (Node n : g.vertexSet()) {
			
			if (n.getId() == id) {
				p = n;
				S.add(p);     		
				p.setPotentiel(0); 		
				p.toggleVisnbVilled();
			} else {
				n.setPotentiel(Double.MAX_VALUE); 
			}
		}
		if (p == null)return;
		System.out.println(OutilsGraphe.BigSommetVilles.get(id-1));
		System.out.println("Le degre de p est "+g.degreeOf(p));
		System.out.println("Son degré sortant est "+g.outDegreeOf(p));	/*On affiche le degr� global du source ainsi que son sortant et entrant */
		System.out.println("Son degré entrant est "+g.inDegreeOf(p)); 
		
		while (g.vertexSet().size() != S.size()) {
			double potentielActuel = p.getPotentiel(); 
			for (DefaultWeightedEdge edge : g.outgoingEdgesOf(p)) { 
				Node x = g.getEdgeTarget(edge); 
				if (!x.isVisnbVilled()) { 
					double weight = g.getEdgeWeight(edge); 
					if (x.getPotentiel() > potentielActuel + weight) { 
						x.setPotentiel(potentielActuel + weight);
						x.setPredecesseur(p);
					}
				}
			}

			Node x = null;
			double smallestPotentiel = 0;
			for (Node n : g.vertexSet()) {
				if (!n.isVisnbVilled() && (x == null || n.getPotentiel() < smallestPotentiel)) { 									//la premiere iteration � x le premier noeud qu'on trouve 
					x = n;																											/*On recherche le sommet parmi les sommets non visit�s avec le plus petit potentiel*/
					smallestPotentiel = n.getPotentiel();
				}
			}
			S.add(x);
			x.toggleVisnbVilled(); 																					/*une fois trouv� on l'ajoute aux sommets visit�s et on met le pointeur dessus*/
			p = x;
			
		}
		for (Node n : g.vertexSet()) {
			if(n.getPotentiel() < 1000000) {
				System.out.println("\nId: "+OutilsGraphe.getBigSommetVilles().get(n.getId()-1).getId()+
						"\nVille: "+OutilsGraphe.getBigSommetVilles().get(n.getId()-1).getVille()+"\nPotentiel: "
						+n.getPotentiel()+"\n"); /*on affiche tous les potentiels*/
				
			}else if(n.getPotentiel() > 1000000) {
				System.out.println("\nId: "+OutilsGraphe.getBigSommetVilles().get(n.getId()-1).getId()
						+"\nVille: "+OutilsGraphe.getBigSommetVilles().get(n.getId()-1).getVille()+
						"\nPotentiel: +∞\nOn ne peut accéder à "+OutilsGraphe.BigSommetVilles.get(id).getVille()
			+" depuis "+OutilsGraphe.getBigSommetVilles().get(n.getId()-1).getVille()); /*on affiche tous les potentiels*/
			}
		}
		
		Node idarrive = null;
		
		for(Node n : g.vertexSet()) {
			if(n.getId() == ida)
				idarrive = n;
		}
		
		if(idarrive == null || idarrive.getPotentiel() == Double.MAX_VALUE) {
			System.out.println("Le sommet d'arrivée n'existe pas ou est inateignable");
		}else {
			List<Node> Chemin = new ArrayList<>();
			Node arrive = idarrive;
			while(arrive.getPredecesseur().getId() != id) {
				Chemin.add(arrive);
				arrive = arrive.getPredecesseur();
			}
			Chemin.add(arrive.getPredecesseur());
			System.out.print("Voici ton CHEMIN SUIS LE ! \n");
			for(Node n : Chemin) {
				System.out.println(n.getId());
			}	
		}		
	}


	public static void KruskalAlgo(DefaultUndirectedWeightedGraph<Node, DefaultWeightedEdge> g) { 												//ATTENTION le graphe doit �tre simple et non orient� et au minimum connexe.
		
		Graph<Node,DefaultWeightedEdge> util = new DefaultUndirectedWeightedGraph<>(DefaultWeightedEdge.class);					//graphe non orient�
		List<Double> Weights = new ArrayList<Double>(); 																	//Liste des longueurs des ar�tes
		List<DefaultWeightedEdge> ForTri = new ArrayList<DefaultWeightedEdge>();
		List<DefaultWeightedEdge> Res = new ArrayList<DefaultWeightedEdge>();
		
		
		for(Node n : g.vertexSet()) {
			util.addVertex(n);
		}
		
		for(DefaultWeightedEdge e: g.edgeSet()) {											 // on parcourt les ar�tes du graphe
			Weights.add(g.getEdgeWeight(e)); 											// on ajoute les longueurs des ar�tes dans la liste des longueurs
			ForTri.add(e);
		}
		
		Collections.sort(Weights); 																					// on les tris
		
		for(int i = 0; i < Weights.size();i++) {															// gr�ce � la m�thode searchEdge il faudra juste les ajout�s dans l'ordre � la liste
			boolean flag = false;
			int k = 0;
			while(k < ForTri.size() && flag == false) {
				if(g.getEdgeWeight(ForTri.get(k)) == Weights.get(i)) {
					flag = true;
					Res.add(ForTri.get(k));
					ForTri.remove(k);
				}else
					k++;
			}
		}
		
		PatonCycleBase<Node,DefaultWeightedEdge> cycleDetector = new PatonCycleBase<Node, DefaultWeightedEdge>(util);  														//un detecteur de cycle 
		int j = 0;
		while(j < Res.size()) {
			util.addEdge(g.getEdgeSource(Res.get(j)), g.getEdgeTarget(Res.get(j)),Res.get(j));
			if(cycleDetector.getCycleBasis().getWeight() != 0) {                             																			//on ajoute au fur et � mesure les ar�tes en ordre croissant dans le graphe et on verifie 
				util.removeEdge(Res.get(j));
				Res.remove(j);
																																						//par �tape la pr�sence d'un cycle, � la fin le graphe util sera un arbre de poids minimum cad connexe et sans cycle
			}else
				j++;
			
		}
		
		double poidsTotal = 0;
	   for(int k = 0;k < Res.size();k++) {
		   	int vdp = g.getEdgeSource(Res.get(k)).getId();
		   	int vda = g.getEdgeTarget(Res.get(k)).getId();
		   	if(vda > 63)
		   		System.out.println("vda: "+vda);
		   	if(vdp > 63)
		   		System.out.println("vdp: "+vdp);
			System.out.println("\nArête n°"+k+":\nId du sommet de Départ: "+g.getEdgeSource(Res.get(k)).getId()+"\nId du sommet d'arrivée: "+g.getEdgeTarget(Res.get(k)).getId()); // on affiche les arêtes
			System.out.println("Ville du sommet de départ: "+OutilsGraphe.getBigVilles().get(vdp-1)+"\nVille du sommet d'arrivée: "+OutilsGraphe.getBigVilles().get(vda-1));
	   poidsTotal+= g.getEdgeWeight(g.getEdge(OutilsGraphe.getBigSommetVilles().get(vdp-1), OutilsGraphe.getBigSommetVilles().get(vda-1)));
	   }
		System.out.println("Poids Total: "+ poidsTotal);
	}
	
}

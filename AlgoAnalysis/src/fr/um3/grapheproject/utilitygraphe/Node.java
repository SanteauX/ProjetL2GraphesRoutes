package fr.um3.grapheproject.utilitygraphe;

import java.io.Serializable;
import java.util.ArrayList;

import org.jgrapht.graph.DefaultWeightedEdge;

public class Node implements Comparable<Node>, Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private double potentiel = Integer.MAX_VALUE;
	private Node predecesseur;
	private boolean visnbVilled = false;
	private String ville;
	private static int nbVille = 0;
	private ArrayList<DefaultWeightedEdge> Chemins = new ArrayList<DefaultWeightedEdge>();
	
	public Node() {
		setId();
	}
	
	public Node(String ville) {
		setId();
		setVille(ville);
	}
	
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	public String getVille() {
		return this.ville;
	}
	
	public double getPotentiel() {
		return potentiel;
	}

	public void setPotentiel(double potentiel) {
		this.potentiel = potentiel;
	}

	private void setId() {
		this.id = nbVille + 1;
		nbVille++;
	}	
	
	public int getId() {
		return id;
	}

	public Node getPredecesseur() {
		return predecesseur;
	}

	public void setPredecesseur(Node predecesseur) {
		this.predecesseur = predecesseur;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(this.id, o.id);
	}

	
	public int hashCode() {
		return Integer.valueOf(getId()).hashCode();
	}

	public void toggleVisnbVilled() {
		visnbVilled = true;
	}

	public boolean isVisnbVilled() {
		// TODO Auto-generated method stub
		return visnbVilled;
	}

	public String toString() {
		String result = "\nVille: "+getVille()+"\n";
		result+="Id du Noeud: "+getId()+"\n";
		return result;
	}
}

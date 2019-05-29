package prog.algo.scraper;


import java.util.ArrayList;
import java.util.Collections;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import fr.um3.grapheproject.utilitygraphe.Node;

public class Scraper {

	public static ArrayList<String> getSources() {
		ArrayList<String> sources = new ArrayList<String>();
		String url0 = "https://www.bonnesroutes.com/mileage-chart/c2202162-france/?fbclid=IwAR2_FJ6QaTWkdnb0VXAwCBqKYhQuKh1EyBuxZ9hgSepnKwK01E2keiDGsYc";
		String url1 = "https://www.bonnesroutes.com/mileage-chart/r3792877-auvergne-rh%25c3%25b4ne-alpes/";
		String url2 = "https://www.bonnesroutes.com/mileage-chart/r3792876-grand-est/";
		String url3 = "https://www.bonnesroutes.com/mileage-chart/r8649-ile-de-france/";
		String url4 = "https://www.bonnesroutes.com/mileage-chart/r3792883-occitanie/";
		String url5 = "https://www.bonnesroutes.com/mileage-chart/r102740-bretagne/";
		String url6 = "https://www.bonnesroutes.com/mileage-chart/r3792876-grand-est/";
		String url7 = "https://www.bonnesroutes.com/mileage-chart/r4217435-hauts-de-france/";
		sources.add(url0);
		sources.add(url1);
		sources.add(url2);
		sources.add(url3);
		sources.add(url4);
		sources.add(url5);
		sources.add(url6);
		sources.add(url7);
		return sources;
	}
	
	public static ArrayList<String> getSmallSources(){
		ArrayList<String> sources = new ArrayList<String>();
		String url0 = "https://www.bonnesroutes.com/mileage-chart/c2202162-france/?fbclid=IwAR2_FJ6QaTWkdnb0VXAwCBqKYhQuKh1EyBuxZ9hgSepnKwK01E2keiDGsYc";
		sources.add(url0);
		return sources;
	}
		
	public static ArrayList<String> getRoads2(ArrayList<String> urls){
		ArrayList<String> txt = new ArrayList<String>();
		for(int i = 0; i < urls.size(); i++) {
			try {
				Document document = Jsoup.connect(urls.get(i)).get();
				for(Element row : document.select("div.t_row")) {
					String element = row.select("div.t_row").text();
					txt.add(element);
				}
			}catch(Exception exception) {
				exception.printStackTrace();
			}
		}
		purge1(txt);
		return txt;
	}
	
	public static ArrayList<DefaultWeightedEdge> setDistances(Graph<Node,DefaultWeightedEdge> graphe, ArrayList<String> data, ArrayList<Node> sommetVilles) {
		ArrayList<DefaultWeightedEdge> distances = new ArrayList<DefaultWeightedEdge>();
		for(int i = 0; i < data.size(); i++) {
			//System.out.println("Chemin n°"+(i+1));
			setEdge(graphe, sommetVilles, data.get(i), distances);
		}
		return distances;
	}
	
	public static ArrayList<DefaultWeightedEdge> setDistances2(Graph<Node,DefaultWeightedEdge> graphe, ArrayList<String> data, ArrayList<Node> sommetVilles) {
		ArrayList<DefaultWeightedEdge> distances = new ArrayList<DefaultWeightedEdge>();
		for(int i = 0; i < data.size(); i++) {
			setEdge2(graphe, sommetVilles, data.get(i), distances);
		}
		return distances;
	}
	
	public static void setEdge(Graph<Node,DefaultWeightedEdge> graphe, ArrayList<Node> sommetVilles, String line, ArrayList<DefaultWeightedEdge> distances) {
		String villeDepart = setVilleDebut(line);
		String villeFin = setVilleFin(line);
		double value = findValue(line);

		Node sommetDepart = findNode(sommetVilles, villeDepart);
		Node sommetFin = findNode(sommetVilles, villeFin);
		//System.out.println("Sommet de départ: "+sommetDepart+"\nSommet de fin: "+sommetFin+"\nDistance: "+value+"km\n\n");

		graphe.addEdge(sommetDepart,sommetFin);
		graphe.setEdgeWeight(graphe.getEdge(sommetDepart, sommetFin), value);
		distances.add(graphe.getEdge(sommetDepart, sommetFin));
	}
	
	public static void setEdge2(Graph<Node,DefaultWeightedEdge> graphe, ArrayList<Node> sommetVilles, String line, ArrayList<DefaultWeightedEdge> distances) {
		String villeDepart = setVilleDebut(line);
		String villeFin = setVilleFin(line);
		double value = findValue(line);
		Node sommetDepart = findNode(sommetVilles, villeDepart);
		Node sommetFin = findNode(sommetVilles, villeFin);
		//System.out.println("Sommet de départ: "+sommetDepart+"\nSommet de fin: "+sommetFin+"\nDistance: "+value+"km\n\n");
		graphe.addEdge(sommetDepart,sommetFin);
		graphe.setEdgeWeight(graphe.getEdge(sommetDepart, sommetFin), value);
		distances.add(graphe.getEdge(sommetDepart, sommetFin));
		graphe.addEdge(sommetFin, sommetDepart);
		graphe.setEdgeWeight(graphe.getEdge(sommetFin, sommetDepart), value);
		distances.add(graphe.getEdge(sommetFin, sommetDepart));
	}

	public static Node findNode(ArrayList<Node> sommetVilles, String line) {
		Node noeud = null;
		for(int i = 0; i < sommetVilles.size(); i++) {
			if(sommetVilles.get(i).getVille().contains(line)) {
				return sommetVilles.get(i);
			}
		}
		if(noeud==null)
			System.out.println("NULL ATTENTION !!!!!!");
		return noeud;
	}
	
	public static void addToGraph(Graph<Node,DefaultWeightedEdge> graphe, ArrayList<Node>sommetVilles) {
		for(int i = 0; i < sommetVilles.size(); i++) {
			graphe.addVertex(sommetVilles.get(i));
		}
	}

	public static String setVilleFin(String line) {
		//Debubg
		//System.out.println("setvilleFin("+line+")");
		String[] txt = line.split("");
		String ville = "";
		int blank = 0;
		int i = 0;

			while(blank < 1) {
				//System.out.println("i: "+i+"\n"+txt[i]);
				if(txt[i].equals(" "))
					blank++;
				i++;
			}
			while(blank < 2) {
				//System.out.println("i: "+i+"\n"+txt[i]);
				if(txt[i].equals(" "))
					blank++;
				else
					ville+=txt[i];
				i++;
		}
		//System.out.println("=> "+ville);
		return ville;	
	}
	
	public static double findValue(String line) {
		String value = "";
		String text = "";
		String[] txt = line.split("");
		int blank = 0;
		for(int i = 0; i < line.length(); i++) {
			if(blank < 2) {
				if(txt[i].equals(" ")) {
					blank++;
				}else if(!txt[i].equals(" ")) {
					text+= txt[i];
				}
			}else if((blank > 1)&&(blank < 3)) {
				if(txt[i].equals(" ")) {
					blank++;
				}else if(!txt[i].equals(" ")) {
					value+= txt[i];
				}
			}
		}
		return Double.valueOf(value);
	}
	
	public static ArrayList<String> setVilles(ArrayList<String> data) {
		// Récupère les données brutes et retourne une liste avec les villes
		ArrayList<String> villes = new ArrayList<String>();
		for(int i = 0; i < data.size(); i++) {
			String line = data.get(i);
			String ville1 = setVilleDebut(line);
			String ville2 = setVilleFin(line);
				villes.add(setVilleDebut(line));
				villes.add(setVilleFin(line));
		}
		Collections.sort(villes);
		return purge3(villes);
	}

	public static String setVilleDebut(String line) {
		// Est utilisé dans la fonction précédente pour détecter les villes dans la chaine
		// Debug:
		// System.out.println("setvilleDebut("+line+")");
		String ville = "";
		String[] txt = line.split("");		
		boolean loop = true;
		int i = 0;
		while(loop) {
			if(txt[i] != " ") {
				ville+=txt[i];
				i++;
			if(txt[i].equals(" ")) {
				loop = false;
				}
			}
		}
		// System.out.println("=> "+ville);
		return ville;
	}

	public static ArrayList<Node> setCityObject(ArrayList<String> villes) {
		// Prend la liste des villes (chaine de caractères) et retourne une liste d'objets)
		ArrayList<Node> sommetVilles = new ArrayList<Node>();
		String verif = villes.get(0);
		Node ville = new Node(verif);
		sommetVilles.add(ville);
		for(int i = 0; i < villes.size(); i++) {
			if(!villes.get(i).equals(verif)) {
				sommetVilles.add(new Node(villes.get(i)));
				verif = villes.get(i);
			}
			//System.out.println("Ville n°"+(i+1)+"\nNom: "+sommetVilles.get(i).getVille()+"\nId: "+sommetVilles.get(i).getId()+"\n\n");
		}
		return sommetVilles;
	}

	public static void purge1(ArrayList<String> data) {
		// enlève les lignes comportant le mot clé Distance
		for(int i = 0; i < data.size(); i++) {
			if((data.get(i).contains("Distance"))||(data.get(i).contains("Distance"))) {
				data.remove(i);
			}
		}
	}
	
	public static ArrayList<String> purge3(ArrayList<String> villes){
		// Enlève les doublons
		ArrayList<String> nvilles = new ArrayList<String>();
		nvilles.add(villes.get(1));
		for(int i = 1; i < villes.size(); i++) {
			if(!villes.get(i).equals(villes.get(i - 1)))
				nvilles.add(villes.get(i));
			}
		return nvilles;
	}

	public static int countBlank(ArrayList<String> data) {
		int blank;
		int daym = 0;
		for(int i = 0; i < data.size(); i++) {
			blank = 0;
			String[] txt = data.get(i).split("");
			for(int j = 0; j < txt.length; j++) {
				if(txt[j].equals(" "))
					blank++;
			if(blank > 3) {
				daym++;
				//
				System.out.println("Attention: Blank: "+data.get(i));
				}
			}
		}
		return daym;
	}

	
}
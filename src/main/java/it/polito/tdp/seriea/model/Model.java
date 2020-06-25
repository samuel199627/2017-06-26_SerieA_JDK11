package it.polito.tdp.seriea.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.seriea.db.SerieADAO;

public class Model {
	
	SimpleWeightedGraph<Team, DefaultEdge> grafo=null;
	SerieADAO dao;
	Map<String,Team> squadreComplete;
	List<Season> stagioni;
	
	
	public String creaGrafo() {
		String ritornare;
		grafo= new SimpleWeightedGraph<Team, DefaultEdge>(DefaultEdge.class);
		dao=new SerieADAO();
		stagioni=new ArrayList<>();
		stagioni=dao.listSeasons();
		//squadreComplete=new ArrayList<>();
		List<Adiacenza> adiacenze=new ArrayList<>();
		adiacenze=dao.listAdiacenze();
		for(Adiacenza a: adiacenze) {
			//se i vertici non ci sono li mettiamo nel grafo
			if(!grafo.containsVertex(a.getT1())) {
				grafo.addVertex(a.getT1());
			}
			if(!grafo.containsVertex(a.getT2())) {
				grafo.addVertex(a.getT2());
			}
			
			//il grafo e' non orientato e quindi se l'arco e' gia' presente  devo aggiornare il peso
			if(grafo.getEdge(a.getT1(), a.getT2())==null) {
				Graphs.addEdgeWithVertices(grafo, a.getT1(), a.getT2(), a.getPeso());
			}
			else {
				//devo aggiornare il peso
				int pesoVecchio=(int) grafo.getEdgeWeight(grafo.getEdge( a.getT1(),  a.getT2()));
				int pesoNuovo=pesoVecchio+a.getPeso();
				//int pesoNuovo=3;
				grafo.removeEdge(grafo.getEdge(a.getT1(), a.getT2()));
				Graphs.addEdgeWithVertices(grafo, a.getT1(), a.getT2(), pesoNuovo);
				//pesoNuovo=(int) grafo.getEdgeWeight(grafo.getEdge( a.getT1(),  a.getT2()));
				//System.out.println("PESO VECCHIO: "+pesoVecchio+" PESO AGGIUNTO: "+a.getPeso()+" PESO NUOVO: "+pesoNuovo);
			}
			
		}
		
		ritornare="GRAFO CREATO CON "+grafo.vertexSet().size()+" vertici e con "+grafo.edgeSet().size()+" archi.\n";
		
		return ritornare;
	}
	
	public List<Team> vertici(){
		List<Team> ritornare=new ArrayList<>();
		squadreComplete=new HashMap<>();
		for(Team t: grafo.vertexSet()) {
			squadreComplete.put(t.getTeam(), t);
			ritornare.add(t);
		}
		return ritornare;
	}
	
	public String calcolaConnessioni(Team team) {
		String ritornare="LE CONNESSIONI PER LA SQUADRA SELEZIONATA SONO: \n\n";
		
		List<Team> vicini=new ArrayList<>();
		vicini=Graphs.neighborListOf(grafo, team);
		List<TeamPartite> viciniPartite=new ArrayList<>();
		for(Team t: vicini) {
			int partite=(int) grafo.getEdgeWeight(grafo.getEdge(team, t));
			viciniPartite.add(new TeamPartite(t,partite));
		}
		viciniPartite.sort(null);
		
		for(TeamPartite t: viciniPartite) {
			ritornare=ritornare+t.getTeam()+" partite "+t.getPartite()+"\n";
		}
		
		return ritornare;
	}
	
	public List<Season> ritornaStagioni(){
		return stagioni;
	}
	
	List<Match> partiteStagione;
	
	public String simula(Season s) {
		String ritornare="SIMULAZIONE: \n\n";
		Simulatore sim=new Simulatore(this);
		dao=new SerieADAO();
		partiteStagione=new ArrayList<>();
		//azzero la classifica e riporto i tifosi al numero originale
		for(Team t: squadreComplete.values()) {
			t.setClassifica(0);
			t.setTifosi(1000);
		}
		partiteStagione=dao.listPartiteSeason(s, squadreComplete);
		sim.init();
		sim.run();
		
		Map<String, Team> squadreCompleteStagione=new HashMap<>();
		for(Match p: partiteStagione) {
			if(!squadreCompleteStagione.containsKey(p.getHomeTeam().getTeam())) {
				squadreCompleteStagione.put(p.getHomeTeam().getTeam(), p.getHomeTeam());
			}
			if(!squadreCompleteStagione.containsKey(p.getAwayTeam().getTeam())) {
				squadreCompleteStagione.put(p.getAwayTeam().getTeam(), p.getAwayTeam());
			}
		}
		
		for(Team t: squadreCompleteStagione.values()) {
			ritornare=ritornare+t.getTeam()+" punti classifica "+t.getClassifica()+" tifosi "+t.getTifosi()+"\n";
		}
		
		
		return ritornare;
	}

}

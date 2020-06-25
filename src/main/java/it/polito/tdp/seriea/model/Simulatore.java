package it.polito.tdp.seriea.model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.seriea.model.Evento.Tipo;

public class Simulatore {
	
	Model m;
	List<Match> partite;
	double fattore=0.1;
	
	PriorityQueue<Evento> coda;
	public Simulatore(Model model) {
		m=model;
	}
	
	
	public void init() {
		partite=new ArrayList<>();
		partite=m.partiteStagione;
		coda=new PriorityQueue<Evento>();
		
		for(Match p: partite) {
			coda.add(new Evento(p.getDate(),Tipo.PARTITA,p));
		}
		
	}
	
	public void run() {
		while(!coda.isEmpty()) {
			Evento e=coda.poll();
			ProcessEvent(e);
		}
		
	}
	
	public void ProcessEvent(Evento e) {
		Match p=e.getPartita();
		Team home=p.getHomeTeam();
		Team away=p.getAwayTeam();
		int goalCasa=p.getFthg();
		int goalTrasferta=p.getFtag();
		int tifosiCasa=home.getTifosi();
		int tifosiTrasferta=away.getTifosi();
				
		if(home.getTifosi()==away.getTifosi()) {
			if(goalCasa==goalTrasferta) {
				//non si sposta nessuno
				home.setClassifica(home.getClassifica()+1);
				away.setClassifica(away.getClassifica()+1);
			}
			else if(goalCasa>goalTrasferta) {
				home.setClassifica(home.getClassifica()+3);
				int spostamento=0;
				spostamento= (int) ( (goalCasa-goalTrasferta)*fattore*tifosiTrasferta);
				away.setTifosi(tifosiTrasferta-spostamento);
				home.setTifosi(tifosiCasa+spostamento);
			}
			else {
				away.setClassifica(away.getClassifica()+3);
				int spostamento=0;
				spostamento= (int) ( (goalTrasferta-goalCasa)*fattore*tifosiCasa);
				away.setTifosi(tifosiTrasferta+spostamento);
				home.setTifosi(tifosiCasa-spostamento);
			}
		}
		else if(home.getTifosi()>away.getTifosi()) {
			double prob=1.0-tifosiTrasferta/tifosiCasa;
			double rand=Math.random();
			if(rand<prob) {
				if(goalTrasferta>0) {
					goalTrasferta=goalTrasferta-1;
				}
				
			}
			
			if(goalCasa==goalTrasferta) {
				//non si sposta nessuno
				home.setClassifica(home.getClassifica()+1);
				away.setClassifica(away.getClassifica()+1);
			}
			else if(goalCasa>goalTrasferta) {
				home.setClassifica(home.getClassifica()+3);
				int spostamento=0;
				spostamento= (int) ( (goalCasa-goalTrasferta)*fattore*tifosiTrasferta);
				away.setTifosi(tifosiTrasferta-spostamento);
				home.setTifosi(tifosiCasa+spostamento);
			}
			else {
				away.setClassifica(away.getClassifica()+3);
				int spostamento=0;
				spostamento= (int) ( (goalTrasferta-goalCasa)*fattore*tifosiCasa);
				away.setTifosi(tifosiTrasferta+spostamento);
				home.setTifosi(tifosiCasa-spostamento);
			}
			
		}
		else {
			double prob=1.0-tifosiCasa/tifosiTrasferta;
			double rand=Math.random();
			if(rand<prob) {
				if(goalCasa>0) {
					goalCasa=goalCasa-1;
				}
				
			}
			
			if(goalCasa==goalTrasferta) {
				//non si sposta nessuno
				home.setClassifica(home.getClassifica()+1);
				away.setClassifica(away.getClassifica()+1);
			}
			else if(goalCasa>goalTrasferta) {
				home.setClassifica(home.getClassifica()+3);
				int spostamento=0;
				spostamento= (int) ( (goalCasa-goalTrasferta)*fattore*tifosiTrasferta);
				away.setTifosi(tifosiTrasferta-spostamento);
				home.setTifosi(tifosiCasa+spostamento);
			}
			else {
				away.setClassifica(away.getClassifica()+3);
				int spostamento=0;
				spostamento= (int) ( (goalTrasferta-goalCasa)*fattore*tifosiCasa);
				away.setTifosi(tifosiTrasferta+spostamento);
				home.setTifosi(tifosiCasa-spostamento);
			}
		}
		
	}
	
	
	

}

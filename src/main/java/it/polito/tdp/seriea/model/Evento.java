package it.polito.tdp.seriea.model;

import java.time.LocalDate;

public class Evento implements Comparable<Evento>{
	
	public enum Tipo{
		PARTITA
	}
	
	private LocalDate time;
	private Tipo tipo;
	private Match partita;
	
	public Evento(LocalDate time, Tipo tipo, Match partita) {
		super();
		this.time = time;
		this.tipo = tipo;
		this.partita = partita;
	}

	public LocalDate getTime() {
		return time;
	}

	public void setTime(LocalDate time) {
		this.time = time;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Match getPartita() {
		return partita;
	}

	public void setPartita(Match partita) {
		this.partita = partita;
	}

	@Override
	public int compareTo(Evento o) {
		// TODO Auto-generated method stub
		return this.getTime().compareTo(o.getTime());
	}
	
	
	

}

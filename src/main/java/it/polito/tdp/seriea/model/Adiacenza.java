package it.polito.tdp.seriea.model;

public class Adiacenza {
	
	Team t1;
	Team t2;
	int peso;
	
	public Adiacenza(Team t1, Team t2, int peso) {
		super();
		this.t1 = t1;
		this.t2 = t2;
		this.peso = peso;
	}

	public Team getT1() {
		return t1;
	}

	public void setT1(Team t1) {
		this.t1 = t1;
	}

	public Team getT2() {
		return t2;
	}

	public void setT2(Team t2) {
		this.t2 = t2;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	
	

}

package it.polito.tdp.seriea.model;

public class TeamPartite implements Comparable<TeamPartite>{

	Team team;
	int partite;
	
	public TeamPartite(Team team, int partite) {
		super();
		this.team = team;
		this.partite = partite;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public int getPartite() {
		return partite;
	}

	public void setPartite(int partite) {
		this.partite = partite;
	}

	@Override
	public int compareTo(TeamPartite o) {
		// TODO Auto-generated method stub
		return o.getPartite()-this.getPartite();
	}
	
	
	
}

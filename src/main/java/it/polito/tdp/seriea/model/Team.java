package it.polito.tdp.seriea.model;

public class Team {

	private String team;
	private int classifica;
	private int tifosi;

	public Team(String team) {
		super();
		this.team = team;
		tifosi=1000;
		classifica=0;
	}
	
	

	public int getClassifica() {
		return classifica;
	}



	public void setClassifica(int classifica) {
		this.classifica = classifica;
	}



	public int getTifosi() {
		return tifosi;
	}



	public void setTifosi(int tifosi) {
		this.tifosi = tifosi;
	}



	/**
	 * @return the team
	 */
	public String getTeam() {
		return team;
	}

	/**
	 * @param team
	 * the team to set
	 */
	public void setTeam(String team) {
		this.team = team;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return team;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		return true;
	}

}

package it.polito.tdp.seriea.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.seriea.model.Adiacenza;
import it.polito.tdp.seriea.model.Match;
import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.Team;

public class SerieADAO {

	public List<Season> listSeasons() {
		String sql = "SELECT season, description FROM seasons";
		List<Season> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Season(res.getInt("season"), res.getString("description")));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Team> listTeams() {
		String sql = "SELECT team FROM teams";
		List<Team> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Team(res.getString("team")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 	select match_id, date, homeTeam, awayTeam, fthg, ftag,ftr
		from matches
		where season=2003
		order by date
	 */
	
	public List<Match> listPartiteSeason(Season s, Map<String, Team> team) {
		String sql = "select match_id, date, homeTeam, awayTeam, fthg, ftag,ftr " + 
				"		from matches " + 
				"		where season=? " + 
				"		order by date ";
		List<Match> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, s.getSeason());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Match(res.getInt("match_id"),s,res.getDate("date").toLocalDate(),team.get(res.getString("homeTeam")),team.get(res.getString("awayTeam")),res.getInt("fthg"),res.getInt("ftag"),res.getString("ftr")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/*
	 	select HomeTeam, awayTeam, Count(*) as partite
from matches
group by HomeTeam, awayTeam
	 */
	public List<Adiacenza> listAdiacenze() {
		String sql = "select HomeTeam, awayTeam, Count(*) as partite " + 
				"from matches " + 
				"group by HomeTeam, awayTeam ";
		List<Adiacenza> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Adiacenza(new Team(res.getString("HomeTeam")),new Team(res.getString("awayTeam")),res.getInt("partite")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}


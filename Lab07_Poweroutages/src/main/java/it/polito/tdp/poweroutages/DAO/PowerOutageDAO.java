package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutages;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<PowerOutages> getPowerOutagesList() {
		
		String sql= "SELECT id, nerc_id, customers_affected, date_event_began, date_event_finished " + 
				"FROM poweroutages ";
		List<PowerOutages> lista=new ArrayList<PowerOutages>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				lista.add(new PowerOutages(res.getInt("id"), res.getInt("nerc_id"), res.getInt("customers_affected"), res.getTimestamp("date_event_began"), res.getTimestamp("date_event_finished")));
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return lista;
	}
	
	public List<PowerOutages> getPowerOutagesListNerc(int nercId) {
		
		String sql= "SELECT id, nerc_id, customers_affected, date_event_began, date_event_finished " + 
				"FROM poweroutages WHERE nerc_id= ? ";
		List<PowerOutages> lista=new ArrayList<PowerOutages>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, nercId);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				lista.add(new PowerOutages(res.getInt("id"), res.getInt("nerc_id"), res.getInt("customers_affected"), res.getTimestamp("date_event_began"), res.getTimestamp("date_event_finished")));
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return lista;
	}
	
	
	

}

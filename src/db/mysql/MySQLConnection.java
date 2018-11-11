package db.mysql;

import java.sql.*;
import java.util.List;

import db.DBConnection;
import entity.AdItem;

public class MySQLConnection implements DBConnection{

	private Connection conn;

	public MySQLConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(MySQLDBUtil.URL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<AdItem> searchAdItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getBudget(int advertiser_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateBudget(int advertiser_id, double budget) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBid(int ad_id, double bid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long createAdvertiser(String advertiser_name, double budget) {
		if (conn == null) {
			System.err.println("DB connection failed!");
		}
		
		//INSERT INTO advertiser (name,budget) VALUES ('apple',120);
		try {	
			String sql = "INSERT INTO advertiser (name,budget) VALUES ((?), (?))";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, advertiser_name);
			stmt.setDouble(2, budget);
			System.out.println(stmt.toString());
			
	        int affectedRows = stmt.executeUpdate();
	        
	        if (affectedRows == 0) {
	            throw new SQLException("Creating advertiser failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	return generatedKeys.getLong(1);
	            }
	            else {
	                throw new SQLException("Creating advertiser failed, no ID obtained.");
	            }
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("insert advertiser done");
		return -1;
	}



	@Override
	public long createAd(double bid, String image_url, int advertiser_id, double ad_score) {
		// TODO Auto-generated method stub
		return 0;
	}

}

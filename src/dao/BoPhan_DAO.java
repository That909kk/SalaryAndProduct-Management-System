package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.BangChamCongNV;
import entity.BoPhan;
import entity.NhanVien;

public class BoPhan_DAO {
	private static BoPhan_DAO instance = new BoPhan_DAO();
	
	public static BoPhan_DAO getInstance() {
		return instance;
	}
	
	public ArrayList<BoPhan> getdsBoPhan(){
		ArrayList<BoPhan> dsBP = new ArrayList<BoPhan>();
		ConnectDB.getInstance();
		Statement state = null;
		try {
			Connection con = ConnectDB.getConnection();
			String Sql = "SELECT * FROM dbo.BoPhan";
			state = con.createStatement();
			ResultSet rs = state.executeQuery(Sql);
			while(rs.next()) {
				BoPhan bp = new BoPhan();
				bp.setMaBoPhan(rs.getString(1));
				bp.setTenBoPhan(rs.getString(2));
				bp.setsDTBoPhan(rs.getString(3));
				
				dsBP.add(bp);
				
				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsBP;
	}

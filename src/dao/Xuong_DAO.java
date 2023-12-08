package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Xuong;

public class Xuong_DAO {
	private ArrayList<Xuong> listX;
	
	public Xuong_DAO() {
		listX = new ArrayList<Xuong>();
	}
	
	public ArrayList<Xuong> getDSXuong() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "Select * from Xuong";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				String maXuong = rs.getString(1);
				String tenXuong = rs.getString(2);
				String diaChi = rs.getString(3);
				
				Xuong x = new Xuong(maXuong, tenXuong, diaChi);
				listX.add(x);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listX;
	}
	
	public Xuong getMotXuong(String maXuong) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		Xuong x = null
				;
		String sql = "Select * from Xuong where maXuong = ?";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maXuong);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				String maX = rs.getString(1);
				String tenXuong = rs.getString(2);
				String diaChi = rs.getString(3);
				
				x = new Xuong(maX, tenXuong, diaChi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return x;
	}
}

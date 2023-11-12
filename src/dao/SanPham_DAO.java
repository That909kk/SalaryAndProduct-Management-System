package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.HopDong;
import entity.SanPham;

public class SanPham_DAO {
	private ArrayList<SanPham> listSP;
	
	public SanPham_DAO() {
		listSP = new ArrayList<SanPham>();
	}
	
	public int getSize() {
		return listSP.size();
	}
	
	public ArrayList<SanPham> getDSSanPham() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "select * from SanPham";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				String maSP = rs.getString(1);
				String tenSP = rs.getString(2);
				int soLuong = rs.getInt(3);
				int soLuongCongDoan = rs.getInt(4);
				HopDong hd = new HopDong(rs.getString(5));
				
				SanPham sp = new SanPham(maSP, tenSP, soLuong, soLuongCongDoan, hd);
				listSP.add(sp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listSP;
	}
	
	public boolean insertSanPham(SanPham sp) {
		int n = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "insert into SanPham values (?, ?, ?, ?, ?)";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, sp.getMaSP());
			stmt.setString(2, sp.getTenSP());
			stmt.setInt(3, sp.getSoLuong());
			stmt.setInt(4, sp.getSoLuongCongDoan());
			stmt.setString(5, sp.getHopDong().getMaHopDong());
			
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}
}

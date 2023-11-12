package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.HopDong;

public class HopDong_DAO {
	private ArrayList<HopDong> listHD;
	
	public HopDong_DAO() {
		listHD = new ArrayList<HopDong>();
	}
	/**
	 * Hàm lấy số lượng phân tử trong mảng
	 * @return
	 */
	public int getSize() {
		return listHD.size();
	}
	/**
	 * hàm lấy danh sách hợp đồng
	 * @return ArrayList<HopDong>
	 */
	public ArrayList<HopDong> getDSHopDong() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "select * from HopDong";
		
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maHD = rs.getString(1);
				String tenDoiTac = rs.getString(2);
				Date ngayKy = rs.getDate(3);
				Date ngayThanhLi = rs.getDate(3);
				
				HopDong hd = new HopDong(maHD, tenDoiTac, ngayKy.toLocalDate(), ngayThanhLi.toLocalDate());
				listHD.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listHD;
	}
	
	public boolean insertHopDong(HopDong hd) {
		int n = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "insert into HopDong values (?, ?, ?, ?)";
		
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, hd.getMaHopDong());
			stmt.setString(2, hd.getTenDoiTac());
			stmt.setDate(3, Date.valueOf(hd.getNgayKy()));
			stmt.setDate(4, Date.valueOf(hd.getNgayThanhLyHopDong()));
			
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
	
	public boolean updateHopDong(HopDong hdCu, HopDong hdMoi) {
		int n = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "update HopDong set tenDoiTac = ?, ngayKi = ?, ngayThanhLiHopDong = ? where maHopDong = ?";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, hdMoi.getTenDoiTac());
			stmt.setDate(2, Date.valueOf(hdMoi.getNgayKy()));
			stmt.setDate(3, Date.valueOf(hdMoi.getNgayThanhLyHopDong()));
			stmt.setString(4, hdCu.getMaHopDong());
			
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
	
	public boolean deleteHopDong(String maHD) {
		int n = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "delete HopDong where maHopDong = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maHD);
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

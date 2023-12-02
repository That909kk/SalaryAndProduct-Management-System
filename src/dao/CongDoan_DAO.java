package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.CongDoan;
import entity.SanPham;

public class CongDoan_DAO {
	private ArrayList<CongDoan> listCD;
	
	public CongDoan_DAO() {
		listCD = new ArrayList<CongDoan>();
	}
	

	public ArrayList<CongDoan> getDSCongDoan() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "select * from CongDoan order by ngayBatDau DESC";
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				String maCD = rs.getString(1);
				String tenCD = rs.getString(2);
				int soLuong = rs.getInt(3);
				int soLuongCN = rs.getInt(4);
				boolean trangThai = rs.getBoolean(5);
				double giaTien = rs.getDouble(6);
				LocalDate ngayBatDau = rs.getDate(7).toLocalDate();
				LocalDate ngayKetThucDuKien = rs.getDate(8).toLocalDate();
				String maCDTienQuyet = rs.getString(9);
				SanPham sp = new SanPham(rs.getString(10));
				
				CongDoan cd = new CongDoan(maCD, tenCD, soLuong, soLuongCN, giaTien, ngayBatDau, ngayKetThucDuKien,
						trangThai, maCDTienQuyet, sp);
				listCD.add(cd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listCD;
	}
	
	public ArrayList<CongDoan> getDSCongDoanTheoMaSP(String maSP) {
		ArrayList<CongDoan> listCDTheoMaSP = new ArrayList<CongDoan>();
		
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "select * from CongDoan where maSP = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maSP);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				String maCD = rs.getString(1);
				String tenCD = rs.getString(2);
				int soLuongCN = rs.getInt(3);
				int soLuong = rs.getInt(4);
				boolean trangThai = rs.getBoolean(5);
				double giaTien = rs.getDouble(6);
				LocalDate ngayBatDau = rs.getDate(7).toLocalDate();
				LocalDate ngayKetThucDuKien = rs.getDate(8).toLocalDate();
				String maCDTienQuyet = rs.getString(9);
				SanPham sp = new SanPham(rs.getString(10));
				
				CongDoan cd = new CongDoan(maCD, tenCD, soLuong, soLuongCN, giaTien, ngayBatDau, ngayKetThucDuKien, trangThai,
						maCDTienQuyet, sp);
				listCDTheoMaSP.add(cd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listCDTheoMaSP;
	}
	
	public boolean insertCongDoan(CongDoan cd) {
		int n = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "insert into CongDoan values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, cd.getMaCongDoan());
			stmt.setString(2, cd.getTenCongDoan());
			stmt.setInt(3, cd.getSoLuongCongNhanDuKien());
			stmt.setInt(4, cd.getSoLuongSanPham());
			stmt.setBoolean(5, cd.isTrangThai());
			stmt.setDouble(6, cd.getGiaTien());
			stmt.setDate(7, Date.valueOf(cd.getNgayBatDau()));
			stmt.setDate(8, Date.valueOf(cd.getNgayKetThucDuKien()));
			stmt.setString(9, cd.getCongDoanTienQuyet());
			stmt.setString(10, cd.getSanPham().getMaSP());
			
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
	
	public boolean deleteCongDoan(String maCD) {
		int n = 0;

		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "delete CongDoan where maCongDoan = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maCD);
			
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
	
	public CongDoan getMotCongDoanTheoMaCD(String ma) {
		CongDoan cd = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "Select * from CongDoan where maCongDoan = ?";
		
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ma);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String tenCD = rs.getString(2);
				int soLuongCN = rs.getInt(3);
				int soLuongSP = rs.getInt(4);
				boolean trangThai = rs.getBoolean(5);
				double giaTien = rs.getDouble(6);
				LocalDate ngayBatDau = rs.getDate(7).toLocalDate();
				LocalDate ngayKetThuc = rs.getDate(8).toLocalDate();
				String cdTienQuyet = rs.getString(9);
				SanPham sp = new SanPham(rs.getNString(10));
				
				cd = new CongDoan(ma, tenCD, soLuongSP, soLuongCN, giaTien, ngayBatDau, ngayKetThuc, trangThai, cdTienQuyet, sp);
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
		return cd;
	}
	
}

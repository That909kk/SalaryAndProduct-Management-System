package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.BoPhan;
import entity.NhanVien;

public class NhanVien_DAO {
	private static NhanVien_DAO instance = new NhanVien_DAO();
	
	public static NhanVien_DAO getInstance() {
		return instance;
	}
	
	public ArrayList<NhanVien> getListNV(){
		ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		Statement stmt = null;
		try {
			Connection con  = ConnectDB.getConnection();
			String sql = "SELECT * FROM dbo.NhanVien";
			stmt = con.createStatement();
			ResultSet rs  = stmt.executeQuery(sql);
			while(rs.next()) {
				String maNV = rs.getString(1);
				byte[] anhDaiDien = rs.getBytes(2);
				String hoNV = rs.getString(3);
				String tenNV = rs.getString(4);
				boolean gioiTinh = rs.getBoolean(5);
				String sdt = rs.getString(6);
				String diaChi = rs.getString(7);
				String cccd = rs.getString(8);
				LocalDate ngaySinh = rs.getDate(9).toLocalDate();
				LocalDate ngayBatDauLamViec = rs.getDate(10).toLocalDate();
				int caLam = rs.getInt(11);
				double luongCB = rs.getDouble(12);
				int thangBacLuong = rs.getInt(13);
				double heSoLuong = rs.getDouble(14);
				double phuCap = rs.getDouble(15);
				BoPhan bp = new BoPhan(rs.getString(16));
				
				NhanVien nv = new NhanVien(maNV, anhDaiDien, hoNV, tenNV, gioiTinh, 
						sdt, diaChi, cccd, ngaySinh, ngayBatDauLamViec, caLam, 
						luongCB, thangBacLuong, heSoLuong, phuCap, bp);
				dsNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNV;
	}
	
	public NhanVien getMotNVTuMaNV(String maNVien){
		NhanVien nv = null;
		ConnectDB.getInstance();
		PreparedStatement stmt = null;
		try {
			Connection con  = ConnectDB.getConnection();
			String sql = "SELECT * FROM dbo.NhanVien where maNV = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maNVien);
			ResultSet rs  = stmt.executeQuery();
			while(rs.next()) {
				String maNV = rs.getString(1);
				byte[] anhDaiDien = rs.getBytes(2);
				String hoNV = rs.getString(3);
				String tenNV = rs.getString(4);
				boolean gioiTinh = rs.getBoolean(5);
				String sdt = rs.getString(6);
				String diaChi = rs.getString(7);
				String cccd = rs.getString(8);
				LocalDate ngaySinh = rs.getDate(9).toLocalDate();
				LocalDate ngayBatDauLamViec = rs.getDate(10).toLocalDate();
				int caLam = rs.getInt(11);
				double luongCB = rs.getDouble(12);
				int thangBacLuong = rs.getInt(13);
				double heSoLuong = rs.getDouble(14);
				double phuCap = rs.getDouble(15);
				BoPhan bp = new BoPhan(rs.getString(16));
				
				nv = new NhanVien(maNV, anhDaiDien, hoNV, tenNV, gioiTinh, 
						sdt, diaChi, cccd, ngaySinh, ngayBatDauLamViec, caLam, 
						luongCB, thangBacLuong, heSoLuong, phuCap, bp);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nv;
	}
}

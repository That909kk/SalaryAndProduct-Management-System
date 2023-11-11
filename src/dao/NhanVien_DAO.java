package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
				NhanVien nv = new NhanVien();
				nv.setMaNV(rs.getString(1));
				nv.setAnhDaiDien(rs.getBytes(2));
				nv.setTen(rs.getString(3));
				nv.setHo(rs.getString(4));
				nv.setGioiTinh(rs.getBoolean(5));
				nv.setSoDienThoai(rs.getString(6));
				nv.setDiaChi(rs.getString(7));
				nv.setcCCD(rs.getString(8));
				nv.setNgaySinh(rs.getDate(9));
				nv.setNgayBatDauLamViec(rs.getDate(10));
				nv.setCaLamViec(rs.getInt(11));
				nv.setLuongCoBan(rs.getDouble(12));
				nv.setThangBacLuong(rs.getInt(13));
				nv.setHeSoLuong(rs.getDouble(14));
				BoPhan bp = new BoPhan(rs.getString(15));
				nv.setBoPhan(bp);
				nv.setPhuCap(rs.getDouble(16));
				
				dsNV.add(nv);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNV;
		
		
	}

}

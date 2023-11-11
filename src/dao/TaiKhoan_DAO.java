package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import connectDB.ConnectDB;
import entity.BoPhan;
import entity.NhanVien;
import entity.TaiKhoan;

public class TaiKhoan_DAO {
private ArrayList<TaiKhoan> listTK;
	
	public TaiKhoan_DAO() {
		listTK = new ArrayList<TaiKhoan>();
	}
	
	public ArrayList<TaiKhoan> getListTK() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "Select * from TaiKhoan";
		
		try {
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maTK = rs.getString(1);
				String taiKhoan = rs.getString(2);
				String matKhau = rs.getString(3);
				NhanVien nv = new NhanVien(rs.getString(4));
				java.sql.Date ngay = rs.getDate(5);
				
				LocalDate ngayDNCuoi = ngay.toLocalDate();
				
				TaiKhoan tk = new TaiKhoan(maTK, taiKhoan, matKhau, nv, ngayDNCuoi);
				
				listTK.add(tk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listTK;
	}
	
	public int getSize() {
		return listTK.size();
	}
	
	public boolean insert(TaiKhoan tk) {
		int n = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "insert into TaiKhoan values (?, ?, ?, ?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, tk.getMaTK());
			stmt.setString(2, tk.getTaiKhoan());
			stmt.setString(3, tk.getMatKhau());
			stmt.setString(4, "null");
			
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
	
	public TaiKhoan soSanhPWD(String tk, String mk) {
		TaiKhoan temp = null;
		String pwdHash = "";
		
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(mk.getBytes());
			byte[] digest = md.digest();
			StringBuilder sb = new StringBuilder();
			for (byte b: digest) {
				sb.append(String.format("%02x", b));
			}
			
			pwdHash = sb.toString().toUpperCase().substring(0, 16);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		
		PreparedStatement stmt = null;
		
		try {
			String sql = "Select * from TaiKhoan where taiKhoan = ? and matKhau = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, tk);
			stmt.setString(2, pwdHash);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				String maTK = rs.getString(1);
				String taiKhoan = rs.getString(2);
				String matKhau = rs.getString(3);
				NhanVien maNV = new NhanVien(rs.getString(4));
				java.sql.Date ngay = rs.getDate(5);
				
				LocalDate ngayDNCuoi = ngay.toLocalDate();
				
				temp = new TaiKhoan(maTK, taiKhoan, matKhau, maNV, ngayDNCuoi);
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
		
		return temp;
	}
	
	public String getBoPhanCuaNV(TaiKhoan tk) {
		String maBP = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "Select nv.maBP from TaiKhoan tk join NhanVien nv on tk.maNV = nv.maNV where maTK = ?";
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, tk.getMaTK());
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				maBP = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return maBP;
	}
	
	public void updateNgayCNCuoi(LocalDate date, TaiKhoan tk) {
		int n = 0;
		java.sql.Date sqlDate = java.sql.Date.valueOf(date);
		
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "update TaiKhoan set ngayDNCuoi = ? where maTK = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setDate(1, sqlDate);
			stmt.setString(2, tk.getMaTK());
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
	}
}

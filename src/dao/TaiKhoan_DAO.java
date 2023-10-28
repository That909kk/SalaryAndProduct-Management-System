package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.TaiKhoan;

public class TaiKhoan_DAO {
private ArrayList<TaiKhoan> listTK;
	
	public TaiKhoan_DAO() {
		listTK = new ArrayList<TaiKhoan>();
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
		
		System.out.println(pwdHash);
		
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
				
				temp = new TaiKhoan(maTK, taiKhoan, matKhau, maNV);
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
}

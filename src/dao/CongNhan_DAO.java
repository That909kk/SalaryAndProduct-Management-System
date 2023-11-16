package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.BoPhan;
import entity.CongNhan;
import entity.NhanVien;
import entity.Xuong;

public class CongNhan_DAO {
	private ArrayList<CongNhan> listCN;
	
	public CongNhan_DAO() {
		listCN = new ArrayList<CongNhan>();
	}
	
	public ArrayList<CongNhan> getDSCongNhan() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from CongNhan";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				String maCN =rs.getString("maCN");
				CongNhan cn = new CongNhan(maCN);
				cn.setAnhDaiDien(rs.getBytes("anhDaiDien"));
				cn.setTen(rs.getString("ten"));
				cn.setHo(rs.getString("ho"));
				cn.setGioiTinh(rs.getBoolean("gioiTinh"));
				cn.setSoDienThoai(rs.getString("soDienThoai"));
				cn.setDiaChi(rs.getString("diaChi"));
				cn.setcCCD(rs.getString("cCCD"));
				cn.setNgaySinh(rs.getDate("ngaySinh").toLocalDate());
				cn.setNgayBatDauLamViec(rs.getDate("ngayBatDauLamViec").toLocalDate());
				cn.setCaLamViec(rs.getInt("caLamViec"));
				cn.setLuongCoBan(rs.getDouble("luongCoBan"));
				cn.setPhuCap(rs.getDouble("phuCap"));
				cn.setChuyenMon(rs.getString("chuyenMon"));
				Xuong x = new Xuong(rs.getString("maXuong"));
				cn.setXuong(x);
				
				listCN.add(cn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCN;
	}
	
	public ArrayList<CongNhan> getDSCongNhanTheoXuong(String xuong) {
		ArrayList<CongNhan> listCNTheoXuong = new ArrayList<CongNhan>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from CongNhan cn join Xuong x "
				+ "on cn.maXuong = x.maXuong "
				+ "where x.tenXuong like ? "
				+ "order by maCN";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, xuong);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maCN =rs.getString("maCN");
				CongNhan cn = new CongNhan(maCN);
				cn.setAnhDaiDien(rs.getBytes("anhDaiDien"));
				cn.setTen(rs.getString("ten"));
				cn.setHo(rs.getString("ho"));
				cn.setGioiTinh(rs.getBoolean("gioiTinh"));
				cn.setSoDienThoai(rs.getString("soDienThoai"));
				cn.setDiaChi(rs.getString("diaChi"));
				cn.setcCCD(rs.getString("cCCD"));
				cn.setNgaySinh(rs.getDate("ngaySinh").toLocalDate());
				cn.setNgayBatDauLamViec(rs.getDate("ngayBatDauLamViec").toLocalDate());
				cn.setCaLamViec(rs.getInt("caLamViec"));
				cn.setLuongCoBan(rs.getDouble("luongCoBan"));
				cn.setPhuCap(rs.getDouble("phuCap"));
				cn.setChuyenMon(rs.getString("chuyenMon"));
				Xuong x = new Xuong(rs.getString("maXuong"));
				cn.setXuong(x);
				
				listCNTheoXuong.add(cn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
		}
		return listCNTheoXuong;
	}
	
	public CongNhan getCongNhanTheoMaCN(String ma) {
		CongNhan cn = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "select * from CongNhan where maCN = ?";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ma);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maCN = rs.getString("maCN");
				cn = new CongNhan(maCN);
				cn.setAnhDaiDien(rs.getBytes("anhDaiDien"));
				cn.setTen(rs.getString("ten"));
				cn.setHo(rs.getString("ho"));
				cn.setGioiTinh(rs.getBoolean("gioiTinh"));
				cn.setSoDienThoai(rs.getString("soDienThoai"));
				cn.setDiaChi(rs.getString("diaChi"));
				cn.setcCCD(rs.getString("cCCD"));
				cn.setNgaySinh(rs.getDate("ngaySinh").toLocalDate());
				cn.setNgayBatDauLamViec(rs.getDate("ngayBatDauLamViec").toLocalDate());
				cn.setCaLamViec(rs.getInt("caLamViec"));
				cn.setLuongCoBan(rs.getDouble("luongCoBan"));
				cn.setPhuCap(rs.getDouble("phuCap"));
				cn.setChuyenMon(rs.getString("chuyenMon"));
				Xuong x = new Xuong(rs.getString("maXuong"));
				cn.setXuong(x);
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
		
		return cn;
	}
	
	public ArrayList<CongNhan> getDSCongNhanTheoXuongVaChuaDuocPhanCong(String xuong) {
		ArrayList<CongNhan> listCNTheoXuong = new ArrayList<CongNhan>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select cn.* from CongNhan cn join Xuong x "
				+ "on cn.maXuong = x.maXuong left join BangPhanCongCN bpccn "
				+ "on cn.maCN = bpccn.maCN "
				+ "where bpccn.maPCCN is null and x.tenXuong like ?";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, xuong);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maCN =rs.getString("maCN");
				CongNhan cn = new CongNhan(maCN);
				cn.setAnhDaiDien(rs.getBytes("anhDaiDien"));
				cn.setTen(rs.getString("ten"));
				cn.setHo(rs.getString("ho"));
				cn.setGioiTinh(rs.getBoolean("gioiTinh"));
				cn.setSoDienThoai(rs.getString("soDienThoai"));
				cn.setDiaChi(rs.getString("diaChi"));
				cn.setcCCD(rs.getString("cCCD"));
				cn.setNgaySinh(rs.getDate("ngaySinh").toLocalDate());
				cn.setNgayBatDauLamViec(rs.getDate("ngayBatDauLamViec").toLocalDate());
				cn.setCaLamViec(rs.getInt("caLamViec"));
				cn.setLuongCoBan(rs.getDouble("luongCoBan"));
				cn.setPhuCap(rs.getDouble("phuCap"));
				cn.setChuyenMon(rs.getString("chuyenMon"));
				Xuong x = new Xuong(rs.getString("maXuong"));
				cn.setXuong(x);
				
				listCNTheoXuong.add(cn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
		}
		return listCNTheoXuong;
	}
}

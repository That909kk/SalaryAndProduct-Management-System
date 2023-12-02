package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.BangPhanCongCN;
import entity.CongDoan;
import entity.CongNhan;
import entity.BangPhanCongCN;
import entity.Xuong;

public class BangPhanCongCN_DAO {
	private ArrayList<BangPhanCongCN> listPCCD;
	
	public BangPhanCongCN_DAO() {
		listPCCD = new ArrayList<BangPhanCongCN>();
	}
	
	public ArrayList<BangPhanCongCN> getDSPhanCongCongDoanTheoMaCD(String ma) {
		ArrayList<BangPhanCongCN> listPCCDTheoMaCD = new ArrayList<BangPhanCongCN>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "Select * from BangPhanCongCN where maCD = ?";
		
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ma);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maPCCN = rs.getString(1);
				boolean trangThai = rs.getBoolean(2);
				LocalDate ngayPhanCong = rs.getDate(3).toLocalDate();
				int soLuongSP = rs.getInt(4);
				CongNhan cn = new CongNhan(rs.getString(5));
				CongDoan cd = new CongDoan(rs.getString(6));
				
				BangPhanCongCN bangPCCN = new BangPhanCongCN(maPCCN, trangThai, ngayPhanCong, soLuongSP, cn, cd);
				listPCCDTheoMaCD.add(bangPCCN);
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
		return listPCCDTheoMaCD;
	}
	
	public boolean insertPhanCongCongNhan(BangPhanCongCN bpccn) {
		int n = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "insert into BangPhanCongCN values (?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, bpccn.getMaPC());
			stmt.setBoolean(2, bpccn.isTrangThai());
			stmt.setDate(3, Date.valueOf(bpccn.getNgayPhanCong()));
			stmt.setInt(4, bpccn.getSoLuongSanPham());
			stmt.setString(5, bpccn.getCongNhan().getMaCN());
			stmt.setString(6, bpccn.getCongDoan().getMaCongDoan());
			
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
	
	public ArrayList<BangPhanCongCN> getDSCongNhanTheoXuongVaDuocPhanCong(String ma) {
		ArrayList<BangPhanCongCN> listCNDuocPhanCong = new ArrayList<BangPhanCongCN>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select bpccn.* from CongNhan cn join Xuong x "
				+ "on cn.maXuong = x.maXuong left join BangPhanCongCN bpccn "
				+ "on cn.maCN = bpccn.maCN "
				+ "where bpccn.maPCCN is not null and maCD = ?";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ma);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maPCCN = rs.getString(1);
				boolean trangThai = rs.getBoolean(2);
				LocalDate ngayPhanCong = rs.getDate(3).toLocalDate();
				int soLuongSP = rs.getInt(4);
				CongNhan cn = new CongNhan(rs.getString(5));
				CongDoan cd = new CongDoan(rs.getString(6));
				
				BangPhanCongCN bpccn = new BangPhanCongCN(maPCCN, trangThai, ngayPhanCong, soLuongSP, cn, cd);
				listCNDuocPhanCong.add(bpccn);
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
		return listCNDuocPhanCong;
	}
	
	public ArrayList<BangPhanCongCN> getDSCongNhanDuocPhanCong() {
		ArrayList<BangPhanCongCN> listCNTheoXuong = new ArrayList<BangPhanCongCN>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select bpccn.* from CongNhan cn join Xuong x "
				+ "on cn.maXuong = x.maXuong left join BangPhanCongCN bpccn "
				+ "on cn.maCN = bpccn.maCN "
				+ "where bpccn.maPCCN is not null";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maPCCN = rs.getString(1);
				boolean trangThai = rs.getBoolean(2);
				LocalDate ngayPhanCong = rs.getDate(3).toLocalDate();
				int soLuongSP = rs.getInt(4);
				CongNhan cn = new CongNhan(rs.getString(5));
				CongDoan cd = new CongDoan(rs.getString(6));
				
				BangPhanCongCN bpccn = new BangPhanCongCN(maPCCN, trangThai, ngayPhanCong, soLuongSP, cn, cd);
				listCNTheoXuong.add(bpccn);
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
	
	public boolean deleteALLPCCuaCongDoan(String ma) {
		int n = 0;
		
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "delete from BangPhanCongCN where maCD = ?";
		
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ma);
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

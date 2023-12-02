package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.BangChamCongNV;
import entity.NhanVien;

public class BangChamCongNV_DAO {
	private static BangChamCongNV_DAO instance = new BangChamCongNV_DAO();

	public static BangChamCongNV_DAO getInstance() {
		return instance;
	}
	
	public ArrayList<BangChamCongNV> getBangCC(){
		ArrayList<BangChamCongNV> bangCC = new ArrayList<BangChamCongNV>();
		ConnectDB.getInstance();
		Statement state = null;
		try {
			Connection con = ConnectDB.getConnection();
			String Sql = "SELECT * FROM dbo.ChamCongNhanVien";
			state = con.createStatement();
			ResultSet rs = state.executeQuery(Sql);
			while(rs.next()) {
				BangChamCongNV ccNV = new BangChamCongNV();
				ccNV.setNgayCham(rs.getDate(2).toLocalDate());
				ccNV.setSoGioTangCa(rs.getInt(3));
				ccNV.setCaLam(rs.getInt(4));
				ccNV.setGhiChu(rs.getString(5));
				ccNV.setCoPhep(rs.getBoolean(6));
				ccNV.setVangMat(rs.getBoolean(7));
				NhanVien nv = new NhanVien(rs.getString(8));
				ccNV.setNv(nv);
				
				bangCC.add(ccNV);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bangCC;
		
	}
	//MinhThat
	public ArrayList<LocalDate> layTatCaThangvaNamkhacNhau(){
		ArrayList<LocalDate> listThangvaNam = new ArrayList<LocalDate>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT DISTINCT MONTH(ngayCham), YEAR(ngayCham) FROM dbo.BangChamCongNhanVien";
		PreparedStatement stmt = null;
		int n =0;
		try {
			stmt= con.prepareStatement(sql);
			ResultSet rs  = stmt.executeQuery();
			
			while(rs.next()) {
				LocalDate thangvaNam = LocalDate.of(rs.getInt(2), rs.getInt(1), 1);
				listThangvaNam.add(thangvaNam);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listThangvaNam;
		
	}
	public ArrayList<BangChamCongNV> dsBangChamCongNhanVienTheoTungThang(int thang, int nam){
		ArrayList<BangChamCongNV> listBangChamCongNhanVien = new ArrayList<BangChamCongNV>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT * FROM dbo.BangChamCongNhanVien where MONTH(ngayCham) = ? and YEAR(ngayCham) = ?";
		PreparedStatement stmt = null;
		int n =0;
		try {
			stmt= con.prepareStatement(sql);
			stmt.setInt(1, thang);
			stmt.setInt(2, nam);
			ResultSet rs  = stmt.executeQuery();
			
			while(rs.next()) {
				BangChamCongNV ccNV = new BangChamCongNV(rs.getString("maChamCong"));
				ccNV.setNgayCham(rs.getDate("ngayCham").toLocalDate());
				ccNV.setSoGioTangCa(rs.getInt("soGioTangCa"));
				ccNV.setCaLam(rs.getInt("caLam"));
				ccNV.setGhiChu(rs.getString("ghiChu"));
				ccNV.setCoPhep(rs.getBoolean("coPhep"));
				ccNV.setCoMat(rs.getBoolean("coMat"));
				ccNV.setVangMat(rs.getBoolean("vangMat"));
				NhanVien nv = new NhanVien(rs.getString("maNV"));
				ccNV.setNv(nv);
				
				listBangChamCongNhanVien.add(ccNV);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listBangChamCongNhanVien;
		
	}
	public ArrayList<BangChamCongNV> dsBangLuongtheothangnambophan( int thang, int nam, String bophan){
		ArrayList<BangChamCongNV> listBangChamCongNhanVien = new ArrayList<BangChamCongNV>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT * FROM dbo.BangChamCongNhanVien where MONTH(ngayCham) = ? and YEAR(ngayCham) = ? and maNV in (select maNV from dbo.NhanVien where maBoPhan = ?)";
		PreparedStatement stmt = null;
		int n =0;
		try {
			stmt= con.prepareStatement(sql);
			stmt.setInt(1, thang);
			stmt.setInt(2, nam);
			stmt.setString(3, bophan);
			ResultSet rs  = stmt.executeQuery();
			
			while(rs.next()) {
				BangChamCongNV ccNV = new BangChamCongNV(rs.getString("maChamCong"));
				ccNV.setNgayCham(rs.getDate("ngayCham").toLocalDate());
				ccNV.setSoGioTangCa(rs.getInt("soGioTangCa"));
				ccNV.setCaLam(rs.getInt("caLam"));
				ccNV.setGhiChu(rs.getString("ghiChu"));
				ccNV.setCoPhep(rs.getBoolean("coPhep"));
				ccNV.setCoMat(rs.getBoolean("coMat"));
				ccNV.setVangMat(rs.getBoolean("vangMat"));
				NhanVien nv = new NhanVien(rs.getString("maNV"));
				ccNV.setNv(nv);
				
				listBangChamCongNhanVien.add(ccNV);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listBangChamCongNhanVien;
		
	}
	public ArrayList<String> listTatCaBoPhan(ArrayList<BangChamCongNV> listBangChamCongNhanVien){
		ArrayList<String> listBoPhan = new ArrayList<String>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT DISTINCT bp.maBoPhan from BoPhan bp join NhanVien nv on bp.maBoPhan=nv.maBP join BangChamCongNhanVien ccnv on nv.maNV=ccnv.maNV";
		PreparedStatement stmt = null;
		int n =0;
		try {
			stmt= con.prepareStatement(sql);
			ResultSet rs  = stmt.executeQuery();
			
			while(rs.next()) {
				String maBoPhan = rs.getString(1);
				listBoPhan.add(maBoPhan);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listBoPhan;
		
	}
	public int getSoNgayDiLamCua1NV(String maNV, int thang, int nam) {
		int soNgayDiLam = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT COUNT(*) FROM dbo.BangChamCongNhanVien where maNV = ? and MONTH(ngayCham) = ? and YEAR(ngayCham) = ? and coMat = 1";
		PreparedStatement stmt = null;
		int n =0;
		try {
			stmt= con.prepareStatement(sql);
			stmt.setString(1, maNV);
			stmt.setInt(2, thang);
			stmt.setInt(3, nam);
			ResultSet rs  = stmt.executeQuery();
			
			while(rs.next()) {
				soNgayDiLam = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return soNgayDiLam;
		
	}
	public double getTongSoGioTangCaCua1NV(String maNV, int thang, int nam) {
		double tongSoGioTangCa = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT SUM(soGioTangCa) FROM dbo.BangChamCongNhanVien where maNV = ? and MONTH(ngayCham) = ? and YEAR(ngayCham) = ?";
		PreparedStatement stmt = null;
		int n =0;
		try {
			stmt= con.prepareStatement(sql);
			stmt.setString(1, maNV);
			stmt.setInt(2, thang);
			stmt.setInt(3, nam);
			ResultSet rs  = stmt.executeQuery();
			
			while(rs.next()) {
				tongSoGioTangCa = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tongSoGioTangCa;
		
	}
	public double getSoBangChamCongCua1NV(String maNV, int thang, int nam) {
		int soBangChamCong = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT COUNT(*) FROM dbo.BangChamCongNhanVien where maNV = ? and MONTH(ngayCham) = ? and YEAR(ngayCham) = ?";
		PreparedStatement stmt = null;
		int n =0;
		try {
			stmt= con.prepareStatement(sql);
			stmt.setString(1, maNV);
			stmt.setInt(2, thang);
			stmt.setInt(3, nam);
			ResultSet rs  = stmt.executeQuery();
			
			while(rs.next()) {
				soBangChamCong = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return soBangChamCong;
		
	}
}

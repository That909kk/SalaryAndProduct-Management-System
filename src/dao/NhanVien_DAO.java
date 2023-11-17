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
				
				String maNV =rs.getString("maNV");
				NhanVien nv = new NhanVien(maNV);
				nv.setAnhDaiDien(rs.getBytes("anhDaiDien"));
				nv.setTen(rs.getString("ten"));
				nv.setHo(rs.getString("ho"));
				nv.setGioiTinh(rs.getBoolean("gioiTinh"));
				nv.setSoDienThoai(rs.getString("soDienThoai"));
				nv.setDiaChi(rs.getString("diaChi"));
				nv.setcCCD(rs.getString("cCCD"));
				nv.setNgaySinh(rs.getDate("ngaySinh").toLocalDate());
				nv.setNgayBatDauLamViec(rs.getDate("ngayBatDauLamViec").toLocalDate());
				nv.setCaLamViec(rs.getInt("caLamViec"));
				nv.setLuongCoBan(rs.getDouble("luongCoBan"));
				nv.setThangBacLuong(rs.getInt("thangBacLuong"));
				nv.setHeSoLuong(rs.getDouble("heSoLuong"));
				BoPhan bp = new BoPhan(rs.getString("maBP"));
				nv.setBoPhan(bp);
				nv.setPhuCap(rs.getDouble("phuCap"));
				
				dsNV.add(nv);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNV;
		
		
	}
	
	public boolean insertNV(NhanVien nv) {
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            String sql = "insert into dbo.NhanVien (maNV, anhDaiDien, ten, ho, gioiTinh, soDienThoai, diaChi, cCCD, ngaySinh, ngayBatDauLamViec, caLamViec, luongCoBan, thangBacLuong, heSoLuong, maBP, phuCap) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, nv.getMaNV());
            stmt.setBytes(2, nv.getAnhDaiDien());
            stmt.setString(3, nv.getTen());
            stmt.setString(4, nv.getHo());
            stmt.setBoolean(5, nv.isGioiTinh());
            stmt.setString(6, nv.getSoDienThoai());
            stmt.setString(7, nv.getDiaChi());
            stmt.setString(8, nv.getcCCD());
            stmt.setDate(9, Date.valueOf(nv.getNgaySinh()));
            stmt.setDate(10, Date.valueOf(nv.getNgayBatDauLamViec()));
            stmt.setInt(11, nv.getCaLamViec());
            stmt.setDouble(12, nv.getLuongCoBan());
            stmt.setInt(13, nv.getThangBacLuong());
            stmt.setDouble(14, nv.getHeSoLuong());
            stmt.setString(15, nv.getBoPhan().getMaBoPhan());
            stmt.setDouble(16, nv.getPhuCap());
            
            
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
	
	
	public ArrayList<NhanVien> getListNVtheoBP(String maBP){
		ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT * FROM dbo.NhanVien where maBP = ?";
		PreparedStatement stmt = null;
        int n = 0;
		try {
			stmt= con.prepareStatement(sql);
			stmt.setString(1, maBP);
			ResultSet rs  = stmt.executeQuery();
			while(rs.next()) {
				String maNV =rs.getString("maNV");
				NhanVien nv = new NhanVien(maNV);
				nv.setAnhDaiDien(rs.getBytes("anhDaiDien"));
				nv.setTen(rs.getString("ten"));
				nv.setHo(rs.getString("ho"));
				nv.setGioiTinh(rs.getBoolean("gioiTinh"));
				nv.setSoDienThoai(rs.getString("soDienThoai"));
				nv.setDiaChi(rs.getString("diaChi"));
				nv.setcCCD(rs.getString("cCCD"));
				nv.setNgaySinh(rs.getDate("ngaySinh").toLocalDate());
				nv.setNgayBatDauLamViec(rs.getDate("ngayBatDauLamViec").toLocalDate());
				nv.setCaLamViec(rs.getInt("caLamViec"));
				nv.setLuongCoBan(rs.getDouble("luongCoBan"));
				nv.setThangBacLuong(rs.getInt("thangBacLuong"));
				nv.setHeSoLuong(rs.getDouble("heSoLuong"));
				BoPhan bp = new BoPhan(rs.getString("maBP"));
				nv.setBoPhan(bp);
				nv.setPhuCap(rs.getDouble("phuCap"));
				
				dsNV.add(nv);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNV;
	}
	
	public ArrayList<NhanVien> getListNVtheoNamVaoLam(int nam){
		ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT * FROM dbo.NhanVien where Year(ngayBatDauLamViec) = ?";
		PreparedStatement stmt = null;
        int n = 0;
		try {
			stmt= con.prepareStatement(sql);
			stmt.setInt(1, nam);
			ResultSet rs  = stmt.executeQuery();
			while(rs.next()) {
				String maNV =rs.getString("maNV");
				NhanVien nv = new NhanVien(maNV);
				nv.setAnhDaiDien(rs.getBytes("anhDaiDien"));
				nv.setTen(rs.getString("ten"));
				nv.setHo(rs.getString("ho"));
				nv.setGioiTinh(rs.getBoolean("gioiTinh"));
				nv.setSoDienThoai(rs.getString("soDienThoai"));
				nv.setDiaChi(rs.getString("diaChi"));
				nv.setcCCD(rs.getString("cCCD"));
				nv.setNgaySinh(rs.getDate("ngaySinh").toLocalDate());
				nv.setNgayBatDauLamViec(rs.getDate("ngayBatDauLamViec").toLocalDate());
				nv.setCaLamViec(rs.getInt("caLamViec"));
				nv.setLuongCoBan(rs.getDouble("luongCoBan"));
				nv.setThangBacLuong(rs.getInt("thangBacLuong"));
				nv.setHeSoLuong(rs.getDouble("heSoLuong"));
				BoPhan bp = new BoPhan(rs.getString("maBP"));
				nv.setBoPhan(bp);
				nv.setPhuCap(rs.getDouble("phuCap"));
				
				dsNV.add(nv);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNV;
	}
	public ArrayList<NhanVien> getListNVtheoNamBP(int nam, String maBP){
		ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT * FROM dbo.NhanVien where Year(ngayBatDauLamViec) = ? and maBP=?";
		PreparedStatement stmt = null;
        int n = 0;
		try {
			stmt= con.prepareStatement(sql);
			stmt.setInt(1, nam);
			stmt.setString(2, maBP);
			ResultSet rs  = stmt.executeQuery();
			while(rs.next()) {
				String maNV =rs.getString("maNV");
				NhanVien nv = new NhanVien(maNV);
				nv.setAnhDaiDien(rs.getBytes("anhDaiDien"));
				nv.setTen(rs.getString("ten"));
				nv.setHo(rs.getString("ho"));
				nv.setGioiTinh(rs.getBoolean("gioiTinh"));
				nv.setSoDienThoai(rs.getString("soDienThoai"));
				nv.setDiaChi(rs.getString("diaChi"));
				nv.setcCCD(rs.getString("cCCD"));
				nv.setNgaySinh(rs.getDate("ngaySinh").toLocalDate());
				nv.setNgayBatDauLamViec(rs.getDate("ngayBatDauLamViec").toLocalDate());
				nv.setCaLamViec(rs.getInt("caLamViec"));
				nv.setLuongCoBan(rs.getDouble("luongCoBan"));
				nv.setThangBacLuong(rs.getInt("thangBacLuong"));
				nv.setHeSoLuong(rs.getDouble("heSoLuong"));
				BoPhan bp = new BoPhan(rs.getString("maBP"));
				nv.setBoPhan(bp);
				nv.setPhuCap(rs.getDouble("phuCap"));
				
				dsNV.add(nv);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNV;
	}
	
	public boolean deleteNV(String maNV) {
		int n=0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "delete NhanVien where maNV = ?";
		PreparedStatement stmt = null;
		try {
			stmt= con.prepareStatement(sql);
			stmt.setString(1,maNV);
			
			n= stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return n>0;
		
	}
	
	public boolean updateNhanVien(NhanVien nv) {
		int n=0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "update NhanVien set anhDaiDien = ? ,ten = ? , ho = ? , gioiTinh = ? ,soDienThoai = ? , diaChi = ?, cCCD= ?, ngaySinh = ?, ngayBatDauLamViec = ? , caLamViec = ?, luongCoBan = ?, thangBacLuong= ?, heSoLuong = ?, maBP=?, phuCap = ? where maNV= ?";
		PreparedStatement stmt = null;
		try {
			stmt= con.prepareStatement(sql);
			stmt.setBytes(1, nv.getAnhDaiDien());
			stmt.setString(2, nv.getTen());
			stmt.setString(3, nv.getHo());
			stmt.setBoolean(4, nv.isGioiTinh());
			stmt.setString(5, nv.getSoDienThoai());
			stmt.setString(6, nv.getDiaChi());
			stmt.setString(7, nv.getcCCD());
			stmt.setDate(8, Date.valueOf(nv.getNgaySinh()));
			stmt.setDate(9, Date.valueOf(nv.getNgayBatDauLamViec()));
			stmt.setInt(10, nv.getCaLamViec());
			stmt.setDouble(11, nv.getLuongCoBan());
			stmt.setInt(12, nv.getThangBacLuong());
			stmt.setDouble(13, nv.getHeSoLuong());
			stmt.setString(14, nv.getBoPhan().getMaBoPhan());
			stmt.setDouble(15, nv.getPhuCap());
			stmt.setString(16, nv.getMaNV());
			n= stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return n>0;
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
	
	public ArrayList<NhanVien> getListNVtheoBPCa(int ca, String maBP){
		ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT * FROM dbo.NhanVien where caLamViec = ? and maBP=?";
		PreparedStatement stmt = null;
        int n = 0;
		try {
			stmt= con.prepareStatement(sql);
			stmt.setInt(1, ca);
			stmt.setString(2, maBP);
			ResultSet rs  = stmt.executeQuery();
			while(rs.next()) {
				String maNV =rs.getString("maNV");
				NhanVien nv = new NhanVien(maNV);
				nv.setAnhDaiDien(rs.getBytes("anhDaiDien"));
				nv.setTen(rs.getString("ten"));
				nv.setHo(rs.getString("ho"));
				nv.setGioiTinh(rs.getBoolean("gioiTinh"));
				nv.setSoDienThoai(rs.getString("soDienThoai"));
				nv.setDiaChi(rs.getString("diaChi"));
				nv.setcCCD(rs.getString("cCCD"));
				nv.setNgaySinh(rs.getDate("ngaySinh").toLocalDate());
				nv.setNgayBatDauLamViec(rs.getDate("ngayBatDauLamViec").toLocalDate());
				nv.setCaLamViec(rs.getInt("caLamViec"));
				nv.setLuongCoBan(rs.getDouble("luongCoBan"));
				nv.setThangBacLuong(rs.getInt("thangBacLuong"));
				nv.setHeSoLuong(rs.getDouble("heSoLuong"));
				BoPhan bp = new BoPhan(rs.getString("maBP"));
				nv.setBoPhan(bp);
				nv.setPhuCap(rs.getDouble("phuCap"));
				
				dsNV.add(nv);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNV;
	}

}

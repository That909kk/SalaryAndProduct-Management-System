package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.HopDong;
import entity.SanPham;

public class SanPham_DAO {
	private ArrayList<SanPham> listSP;
	
	public SanPham_DAO() {
		listSP = new ArrayList<SanPham>();
	}
	
	public int getSize() {
		return listSP.size();
	}
	/**
	 * cre: Huỳnh Kim Thành
	 * Phương thức trả về danh sách tất cả sản phẩm
	 * @return ArrayList
	 */
	public ArrayList<SanPham> getDSSanPham() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "select * from SanPham";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				String maSP = rs.getString(1);
				String tenSP = rs.getString(2);
				int soLuong = rs.getInt(3);
				int soLuongCongDoan = rs.getInt(4);
				boolean trangThai = rs.getBoolean(5);
				HopDong hd = new HopDong(rs.getString(6));
				
				SanPham sp = new SanPham(maSP, tenSP, soLuong, soLuongCongDoan, trangThai, hd);
				listSP.add(sp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listSP;
	}
	/**
	 * Lấy một sản phẩm dựa trên mã sản phẩm
	 * @param maSP
	 * @return SanPham
	 */
	public SanPham getMotSanPham(String maSP) {
		SanPham sp = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "select * from SanPham where maSP = ?";
		
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maSP);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maSanPham = rs.getString(1);
				String tenSanPham = rs.getString(2);
				int soLuong = rs.getInt(3);
				int soLuongCongDoan = rs.getInt(4);
				boolean trangThai = rs.getBoolean(5);
				HopDong hd = new HopDong(rs.getString(6));
				
				sp = new SanPham(maSP, tenSanPham, soLuong, soLuongCongDoan, trangThai, hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sp;
	}
	/**
	 * cre: Huỳnh Kim Thành
	 * Phương thức dùng để thêm 1 sản phẩm được chọn
	 * @param sp
	 * @return true nếu thêm thành công
	 */
	public boolean insertSanPham(SanPham sp) {
		int n = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "insert into SanPham values (?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, sp.getMaSP());
			stmt.setString(2, sp.getTenSP());
			stmt.setInt(3, sp.getSoLuong());
			stmt.setInt(4, sp.getSoLuongCongDoan());
			stmt.setBoolean(5, sp.isTrangThai());
			stmt.setString(6, sp.getHopDong().getMaHopDong());
			
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
	/**
	 * cre: Huỳnh Kim Thành
	 * Phương thức dùng để cập nhật 1 sản phẩm được chọn
	 * @param sp
	 * @return true nếu cập nhật thành công
	 */
	public boolean updateSanPham(SanPham sp) {
		int n = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "update SanPham set tenSP = ?, soLuong = ?, soLuongCongDoan = ?, trangThai = ? where maSP = ?";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, sp.getTenSP());
			stmt.setInt(2, sp.getSoLuong());
			stmt.setInt(3, sp.getSoLuongCongDoan());
			stmt.setBoolean(4, sp.isTrangThai());
			stmt.setString(5, sp.getMaSP());
			
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
	/**
	 * cre: Huỳnh Kim Thành
	 * Phương thức xoá 1 sản phẩm trong database
	 * @param maHD
	 * @return true nếu xoá thành công
	 */
	public boolean deleteSanPham(String maSP) {
		int n = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "delete SanPham where maSP = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maSP);
			
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
	/**
	 * cre: Huỳnh Kim Thành
	 * Phương thức trả về danh sách tất cả sản phẩm theo mã hợp đồng
	 * @param maHD
	 * @return ArrayList
	 */
	public ArrayList<SanPham> getDSSanPhamTheoHopDong(String maHD) {
		ArrayList<SanPham> listTheoHopDong = new ArrayList<SanPham>();
		
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "select * from SanPham where maHopDong = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maHD);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				String maSP = rs.getString(1);
				String tenSP = rs.getString(2);
				int soLuong = rs.getInt(3);
				int soLuongCongDoan = rs.getInt(4);
				boolean trangThai = rs.getBoolean(5);
				HopDong hd = new HopDong(rs.getString(6));
				
				SanPham sp = new SanPham(maSP, tenSP, soLuong, soLuongCongDoan, trangThai, hd);
				listTheoHopDong.add(sp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTheoHopDong;
	}
	/**
	 * Lấy ra số lượng sản phẩm theo mã hợp đồng
	 * @param maHD
	 * @return int
	 */
	public int getSizeCuaDSTheoHopDong(String maHD) {
		return this.getDSSanPhamTheoHopDong(maHD).size();
	}
	/**
	 * cre: Huỳnh Kim Thành
	 * Phương thức trả về danh sách tất cả sản phẩm theo trạng thái sản phẩm (Đã hoàn thành / Chưa hoàn thành)
	 * @param tinhTrang
	 * @return ArrayList
	 */
	public ArrayList<SanPham> getDSSanPhamTheoTrangThai(boolean tinhTrang) {
		ArrayList<SanPham> listTheoTrangThai = new ArrayList<SanPham>();
		
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "select * from SanPham where trangThai = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setBoolean(1, tinhTrang);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				String maSP = rs.getString(1);
				String tenSP = rs.getString(2);
				int soLuong = rs.getInt(3);
				int soLuongCongDoan = rs.getInt(4);
				boolean trangThai = rs.getBoolean(5);
				HopDong hd = new HopDong(rs.getString(6));
				
				SanPham sp = new SanPham(maSP, tenSP, soLuong, soLuongCongDoan, trangThai, hd);
				listTheoTrangThai.add(sp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTheoTrangThai;
	}
	/**
	 * cre: Huỳnh Kim Thành
	 * Phương thức trả về danh sách tất cả sản phẩm theo cả 2 điều kiện mã hợp đồng và trạng thái sản phẩm
	 * @param maHD
	 * @return ArrayList
	 */
	public ArrayList<SanPham> getDSSanPhamTheoHDvaTT(String maHD, boolean tinhTrang) {
		ArrayList<SanPham> listTheoTrangThai = new ArrayList<SanPham>();
		
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "select * from SanPham where trangThai = ? and maHopDong = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setBoolean(1, tinhTrang);
			stmt.setString(2, maHD);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				String maSP = rs.getString(1);
				String tenSP = rs.getString(2);
				int soLuong = rs.getInt(3);
				int soLuongCongDoan = rs.getInt(4);
				boolean trangThai = rs.getBoolean(5);
				HopDong hd = new HopDong(rs.getString(6));
				
				SanPham sp = new SanPham(maSP, tenSP, soLuong, soLuongCongDoan, trangThai, hd);
				listTheoTrangThai.add(sp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTheoTrangThai;
	}
}

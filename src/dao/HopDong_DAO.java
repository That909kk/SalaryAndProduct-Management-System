package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

import connectDB.ConnectDB;
import entity.HopDong;

public class HopDong_DAO {
	private ArrayList<HopDong> listHD;
	
	public HopDong_DAO() {
		listHD = new ArrayList<HopDong>();
	}
	/**
	 * Hàm lấy số lượng phân tử trong mảng
	 * @return
	 */
	public int getSize() {
		return listHD.size();
	}
	/**
	 * hàm lấy danh sách hợp đồng
	 * @return ArrayList<HopDong>
	 */
	public ArrayList<HopDong> getDSHopDong() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "select * from HopDong order by ngayKi DESC";
		
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maHD = rs.getString(1);
				String tenDoiTac = rs.getString(2);
				Date ngayKy = rs.getDate(3);
				Date ngayThanhLi = rs.getDate(4);
				boolean trangThai = rs.getBoolean(5);
				
				HopDong hd = new HopDong(maHD, tenDoiTac, ngayKy.toLocalDate(), ngayThanhLi.toLocalDate(), trangThai);
				listHD.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listHD;
	}
	
	public boolean insertHopDong(HopDong hd) {
		int n = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "insert into HopDong values (?, ?, ?, ?, ?)";
		
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, hd.getMaHopDong());
			stmt.setString(2, hd.getTenDoiTac());
			stmt.setDate(3, Date.valueOf(hd.getNgayKy()));
			stmt.setDate(4, Date.valueOf(hd.getNgayThanhLyHopDong()));
			stmt.setBoolean(5, hd.isTrangThai());
			
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
	
	public HopDong getMotHopDong(String ma) {
		HopDong hd = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "select * from HopDong where maHopDong = ?";
		
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maHD = rs.getString(1);
				String tenDoiTac = rs.getString(2);
				Date ngayKy = rs.getDate(3);
				Date ngayThanhLi = rs.getDate(4);
				boolean trangThai = rs.getBoolean(5);
				
				hd = new HopDong(maHD, tenDoiTac, ngayKy.toLocalDate(), ngayThanhLi.toLocalDate(), trangThai);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hd;
	}
	
	public boolean updateHopDong(HopDong hd) {
		int n = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "update HopDong set tenDoiTac = ?, ngayKi = ?, ngayThanhLyHopDong = ?, trangThai = ? where maHopDong = ?";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, hd.getTenDoiTac());
			stmt.setDate(2, Date.valueOf(hd.getNgayKy()));
			stmt.setDate(3, Date.valueOf(hd.getNgayThanhLyHopDong()));
			stmt.setBoolean(4, hd.isTrangThai());
			stmt.setString(5, hd.getMaHopDong());
			
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
	
	public boolean deleteHopDong(String maHD) {
		int n = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "delete HopDong where maHopDong = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maHD);
			
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
	
	public ArrayList<HopDong> getListHDTheoTrangThai(boolean tinhTrang) {
		ArrayList<HopDong> listTheoTrangThai = new ArrayList<HopDong>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "select * from HopDong where trangThai = ? order by ngayKi DESC";
		
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setBoolean(1, tinhTrang);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maHD = rs.getString(1);
				String tenDoiTac = rs.getString(2);
				Date ngayKy = rs.getDate(3);
				Date ngayThanhLi = rs.getDate(4);
				boolean trangThai = rs.getBoolean(5);
				
				HopDong hd = new HopDong(maHD, tenDoiTac, ngayKy.toLocalDate(), ngayThanhLi.toLocalDate(), trangThai);
				listTheoTrangThai.add(hd);
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
		return listTheoTrangThai;
	}
	
	public ArrayList<HopDong> getDSHopDongTheoNam(int year) {
		ArrayList<HopDong> listTheoNam = new ArrayList<HopDong>();

		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "select * from HopDong where YEAR(ngayki) = ? order by ngayKi DESC";
		
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, year);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maHD = rs.getString(1);
				String tenDoiTac = rs.getString(2);
				Date ngayKy = rs.getDate(3);
				Date ngayThanhLi = rs.getDate(4);
				boolean trangThai = rs.getBoolean(5);
				
				HopDong hd = new HopDong(maHD, tenDoiTac, ngayKy.toLocalDate(), ngayThanhLi.toLocalDate(), trangThai);
				listTheoNam.add(hd);
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
		return listTheoNam;
	}
	
	public ArrayList<Integer> getDSNamKiHopDong() {
		HashSet<Integer> setNam = new HashSet<Integer>();
		ArrayList<Integer> dsNam = new ArrayList<Integer>();
		
		for (HopDong hopDong : listHD) {
			dsNam.add(hopDong.getNgayKy().getYear());
		}
		
		for (Integer namKi : dsNam) {
			setNam.add(namKi);
		}
		
		ArrayList<Integer> listNamKi = new ArrayList<Integer>(setNam);
		return listNamKi;
	}
	
	public ArrayList<HopDong> getDSHopDongTheoNamvaTT(int year, boolean tinhTrang) {
		ArrayList<HopDong> listTheoNamVaTT = new ArrayList<HopDong>();

		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "select * from HopDong where YEAR(ngayki) = ? and trangThai = ? order by ngayKi DESC";
		
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, year);
			stmt.setBoolean(2, tinhTrang);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maHD = rs.getString(1);
				String tenDoiTac = rs.getString(2);
				Date ngayKy = rs.getDate(3);
				Date ngayThanhLi = rs.getDate(4);
				boolean trangThai = rs.getBoolean(5);
				
				HopDong hd = new HopDong(maHD, tenDoiTac, ngayKy.toLocalDate(), ngayThanhLi.toLocalDate(), trangThai);
				listTheoNamVaTT.add(hd);
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
		return listTheoNamVaTT;
	}
}

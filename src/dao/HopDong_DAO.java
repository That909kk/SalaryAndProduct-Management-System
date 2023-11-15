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
	 * cre: Huỳnh Kim Thành
	 * Hàm lấy số lượng phân tử trong mảng
	 * @return int
	 */
	public int getSize() {
		return listHD.size();
	}
	/**
	 * cre: Huỳnh Kim Thành
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
	/**
	 * cre: Huỳnh Kim Thành
	 * Phương thức thêm một hợp đồng vào database
	 * @param hd
	 * @return true nếu thêm vào thành công
	 */
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
	/**
	 * cre: Huỳnh Kim Thành
	 * Phương thức lấy một hợp đồng từ mã hợp đồng
	 * @param ma
	 * @return HopDong
	 */
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
	/**
	 * cre: Huỳnh Kim Thành
	 * Phương thức cập nhật 1 hợp đồng trong database
	 * @param hd
	 * @return true nếu cập nhật thành công
	 */
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
	/**
	 * cre: Huỳnh Kim Thành
	 * Phương thức xoá 1 hợp đồng trong database
	 * @param maHD
	 * @return true nếu xoá thành công
	 */
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
	/**
	 * cre: Huỳnh Kim Thành
	 * Phương thức trả về danh sách hợp đồng theo trạng thái được nhập.
	 * @param tinhTrang
	 * @return ArrayList<HopDong>
	 */
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
	/**
	 * cre: Huỳnh Kim Thành
	 * Phương thức trả về danh sách hợp đồng theo năm được nhập.
	 * @param year
	 * @return ArrayList<HopDong>
	 */
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
	/**
	 * cre: Huỳnh Kim Thành
	 * Phương thức trả về danh sách các năm kí hợp đồng
	 * @param tinhTrang
	 * @return ArrayList<Integer>
	 */
	public ArrayList<Integer> getDSNamKiHopDong() {
		HashSet<Integer> setNam = new HashSet<Integer>();
		
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "select Year(ngayKi) from HopDong";
		
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int nam = rs.getInt(1);
				
				setNam.add(nam);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ArrayList<Integer> listNamKi = new ArrayList<Integer>(setNam);
		return listNamKi;
	}
	/**
	 * cre: Huỳnh Kim Thành
	 * Phương thức trả về danh sách hợp đồng theo trạng thái và năm được nhập.
	 * @param year, tinhTrang
	 * @return ArrayList<HopDong>
	 */
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

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
import entity.CongNhan;
import entity.Xuong;

public class CongNhan_DAO {
	private static CongNhan_DAO instance = new CongNhan_DAO();
	private ArrayList<CongNhan> listCN;
	
	public static CongNhan_DAO getInstance() {
		return instance;
	}
	
	public CongNhan_DAO() {
		listCN = new ArrayList<CongNhan>();
	}
	public ArrayList<CongNhan> getListCN(){
		ArrayList<CongNhan> dsCN = new ArrayList<CongNhan>();
		ConnectDB.getInstance();
		Statement stmt = null;
		try {
			Connection con  = ConnectDB.getConnection();
			String sql = "SELECT * FROM dbo.CongNhan";
			stmt = con.createStatement();
			ResultSet rs  = stmt.executeQuery(sql);
			while(rs.next()) {
				CongNhan cn = new CongNhan();
				cn.setMaCN(rs.getString(1));
				cn.setAnhDaiDien(rs.getBytes(2));
				cn.setHo(rs.getString(3));
				cn.setTen(rs.getString(4));
				cn.setGioiTinh(rs.getBoolean(5));
				java.sql.Date ngaySinh = rs.getDate(6);
				LocalDate NgaySinh = ngaySinh.toLocalDate();
				cn.setcCCD(rs.getString(7));
				cn.setDiaChi(rs.getString(8));
				cn.setSoDienThoai(rs.getString(9));
				cn.setChuyenMon(rs.getNString(10));
				cn.setCaLamViec(rs.getInt(11));
				cn.setPhuCap(rs.getDouble(12));
				cn.setLuongCoBan(rs.getDouble(13));
				java.sql.Date ngayBatDauLamViec = rs.getDate(14);
				LocalDate NgayBatDauLamViec = ngayBatDauLamViec.toLocalDate();	
				Xuong x = new Xuong(rs.getString(15));
				cn.setXuong(x);
				dsCN.add(cn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCN;
	}

	public boolean insertCN(CongNhan cn) {
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            String sql = "insert into dbo.CongNhan (maNV, anhDaiDien, ho, ten, gioiTinh, ngaySinh, cCCD, diaChi, soDienThoai, chuyenMon, caLamViec, phuCap, luongCoBan, ngayBatDauLamViec, maXuong) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, cn.getMaCN());
            stmt.setBytes(2, cn.getAnhDaiDien());
            stmt.setString(3, cn.getHo());
            stmt.setString(4, cn.getTen());
            stmt.setBoolean(5, cn.isGioiTinh());
            stmt.setDate(6, Date.valueOf(cn.getNgaySinh()));
            stmt.setString(7, cn.getcCCD());
            stmt.setString(8, cn.getDiaChi());
            stmt.setString(9, cn.getSoDienThoai());
            stmt.setString(10, cn.getChuyenMon());
            stmt.setInt(11, cn.getCaLamViec());
            stmt.setDouble(12, cn.getPhuCap());
            stmt.setDouble(13, cn.getLuongCoBan());
            stmt.setDate(14, Date.valueOf(cn.getNgayBatDauLamViec()));
            stmt.setString(15, cn.getXuong().getMaXuong());                   
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
	
	public boolean deletCN(String maCN) {
		int n=0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "delete CongNhan where maCN = ?";
		PreparedStatement stmt = null;
		try {
			stmt= con.prepareStatement(sql);
			stmt.setString(1,maCN);
			
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
	
	public boolean updateCN(CongNhan cn) {
		int n=0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "update CongNhan set anhDaiDien = ? ,ho = ? , ten = ? , gioiTinh = ? ,ngaySinh = ? , cCCD = ?, diaChi= ?, soDienThoai = ?, chuyenMon = ? , caLamViec = ?, phuCap = ?, luongCoBan= ?, ngayBatDauLamViec = ?, maXuong = ? where maNV= ?";
		PreparedStatement stmt = null;
		try {
			stmt= con.prepareStatement(sql);
	        stmt.setBytes(1, cn.getAnhDaiDien());
	        stmt.setString(2, cn.getHo());
	        stmt.setString(3, cn.getTen());
	        stmt.setBoolean(4, cn.isGioiTinh());
	        stmt.setDate(5, Date.valueOf(cn.getNgaySinh()));
	        stmt.setString(6, cn.getcCCD());
	        stmt.setString(7, cn.getDiaChi());
	        stmt.setString(8, cn.getSoDienThoai());
	        stmt.setString(9, cn.getChuyenMon());
	        stmt.setInt(10, cn.getCaLamViec());
	        stmt.setDouble(11, cn.getPhuCap());
	        stmt.setDouble(12, cn.getLuongCoBan());
	        stmt.setDate(13, Date.valueOf(cn.getNgayBatDauLamViec()));
	        stmt.setString(14, cn.getXuong().getMaXuong()); 
	        stmt.setString(15, cn.getMaCN());
	        n = stmt.executeUpdate();
			
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
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		CongNhan cn = null;
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

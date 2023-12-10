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
import entity.BangChamCongCN;
import entity.CongNhan;

public class BangChamCongCN_DAO {
	private static BangChamCongCN_DAO instance = new BangChamCongCN_DAO();

	public static BangChamCongCN_DAO getInstance() {
		return instance;
	}
	
	public ArrayList<BangChamCongCN> getBangCC(){
		ArrayList<BangChamCongCN> bangCC = new ArrayList<BangChamCongCN>();
		ConnectDB.getInstance();
		Statement state = null;
		try {
			Connection con = ConnectDB.getConnection();
			String Sql = "SELECT * FROM dbo.BangChamCongCongNhan";
			state = con.createStatement();
			ResultSet rs = state.executeQuery(Sql);
			while(rs.next()) {
				BangChamCongCN ccCN = new BangChamCongCN();
				ccCN.setMaCCCN(rs.getString(1));
				ccCN.setNgayCham(rs.getDate(2).toLocalDate());
				ccCN.setVangMat(rs.getBoolean(3));
				ccCN.setCoPhep(rs.getBoolean(4));
				ccCN.setSoGioTangCa(rs.getInt(5));
				ccCN.setSanLuong(rs.getInt(6));
				ccCN.setCaLam(rs.getInt(7));
				ccCN.setGhiChu(rs.getString(8));
				CongNhan cn = new CongNhan(rs.getString(9));
				ccCN.setCn(cn);				
				bangCC.add(ccCN);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bangCC;	
	}
	
	public ArrayList<String> listTatCaXuong(ArrayList<BangChamCongCN> listBangChamCongCongNhan){
		ArrayList<String> listXuong = new ArrayList<String>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT DISTINCT bp.maXuong from Xuong bp join CongNhan nv on bp.maXuong=cn.maXuong join BangChamCongCongNhan cc on nv.maCN=cccn.maCN";
		PreparedStatement stmt = null;
		int n =0;
		try {
			stmt= con.prepareStatement(sql);
			ResultSet rs  = stmt.executeQuery();
			
			while(rs.next()) {
				String maXuong = rs.getString(1);
				listXuong.add(maXuong);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listXuong;
		
	}
	public int getSoNgayDiLamCua1CN(String maCN, int thang, int nam) {
		int soNgayDiLam = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT COUNT(*) FROM dbo.BangChamCongCongNhan where maCN = ? and MONTH(ngayCham) = ? and YEAR(ngayCham) = ? and coPhep = 1";
		PreparedStatement stmt = null;
		int n =0;
		try {
			stmt= con.prepareStatement(sql);
			stmt.setString(1, maCN);
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
	public double getTongSoGioTangCaCua1CN(String maCN, int thang, int nam) {
		double tongSoGioTangCa = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT SUM(soGioTangCa) FROM dbo.BangChamCongNhanVien where maCN = ? and MONTH(ngayCham) = ? and YEAR(ngayCham) = ?";
		PreparedStatement stmt = null;
		int n =0;
		try {
			stmt= con.prepareStatement(sql);
			stmt.setString(1, maCN);
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
	public double getSoBangChamCongCua1CN(String maCN, int thang, int nam) {
		int soBangChamCong = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT COUNT(*) FROM dbo.BangChamCongNhanVien where maCN = ? and MONTH(ngayCham) = ? and YEAR(ngayCham) = ?";
		PreparedStatement stmt = null;
		int n =0;
		try {
			stmt= con.prepareStatement(sql);
			stmt.setString(1, maCN);
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
	public ArrayList<BangChamCongCN> dsBangCCtheomaCNthangnam(String maNV,int thang, int nam){
		ArrayList<BangChamCongCN> listBangChamCongCongNhan = new ArrayList<BangChamCongCN>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT * FROM dbo.BangChamCongNhanVien where MONTH(ngayCham) = ? and YEAR(ngayCham) = ? and maNV = ?";
		PreparedStatement stmt = null;
		int n =0;
		try {
			stmt= con.prepareStatement(sql);
			stmt.setInt(1, thang);
			stmt.setInt(2, nam);
			stmt.setString(3, maNV);
			ResultSet rs  = stmt.executeQuery();
			
			while(rs.next()) {
				BangChamCongCN ccCN = new BangChamCongCN(rs.getString("maChamCong"));
				ccCN.setNgayCham(rs.getDate("ngayCham").toLocalDate());
				ccCN.setSoGioTangCa(rs.getInt("soGioTangCa"));
				ccCN.setCaLam(rs.getInt("caLam"));
				ccCN.setGhiChu(rs.getString("ghiChu"));
				ccCN.setCoPhep(rs.getBoolean("coPhep"));
				ccCN.setVangMat(rs.getBoolean("vangMat"));
				CongNhan cn = new CongNhan(rs.getString("maCN"));
				ccCN.setCn(cn);
				
				listBangChamCongCongNhan.add(ccCN);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listBangChamCongCongNhan;
		
		
	}
	
	public ArrayList<BangChamCongCN> getBangCCTheoNgay(LocalDate ngayChamCong){
		ArrayList<BangChamCongCN> bangCC = new ArrayList<BangChamCongCN>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT * FROM dbo.BangChamCongNhanVien where ngayCham = ?";
		PreparedStatement stmt = null;
		int n =0;
		try {
			stmt= con.prepareStatement(sql);
			stmt.setString(1, String.valueOf(ngayChamCong));
			ResultSet rs  = stmt.executeQuery();
			
			while(rs.next()) {
				BangChamCongCN ccNV = new BangChamCongCN(rs.getString("maChamCong"));
				ccNV.setNgayCham(rs.getDate("ngayCham").toLocalDate());
				ccNV.setSoGioTangCa(rs.getInt("soGioTangCa"));
				ccNV.setCaLam(rs.getInt("caLam"));
				ccNV.setGhiChu(rs.getString("ghiChu"));
				ccNV.setCoPhep(rs.getBoolean("coPhep"));
				ccNV.setVangMat(rs.getBoolean("vangMat"));
				CongNhan cn = new CongNhan(rs.getString("maNV"));
				ccNV.setCn(cn);
				
				bangCC.add(ccNV);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bangCC;
		
		
	}
	
	public boolean insertBangChamCongCN(BangChamCongCN bangChamCongCN) {
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            String sql = "insert into dbo.BangChamCongCongNhan (maCC, ngayCham, maCN, vangMat, coPhep, soGioTangCa, sanLuong, ghiChu) values(?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, bangChamCongCN.getMaCCCN());
            stmt.setDate(2, Date.valueOf(bangChamCongCN.getNgayCham()));
            stmt.setString(3, bangChamCongCN.getCn().getMaCN());
            stmt.setBoolean(4, bangChamCongCN.isVangMat());
            stmt.setBoolean(5, bangChamCongCN.isCoPhep());
            stmt.setInt(6, bangChamCongCN.getSoGioTangCa());
            stmt.setInt(7, bangChamCongCN.getSanLuong());
            stmt.setString(8, bangChamCongCN.getGhiChu());                            
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
	
	public boolean updateBangChamCongCN(BangChamCongCN bangCC) {
		int n=0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "update BangChamCongCongNhan set soGioTangCa = ? , coPhep=? , vangMat = ?, coPhep=? ,ghiChu=?  where maCC= ?";
		PreparedStatement stmt = null;
		try {
			stmt= con.prepareStatement(sql);
			stmt.setInt(1, bangCC.getSoGioTangCa());
			stmt.setBoolean(2, bangCC.isCoPhep());
			stmt.setBoolean(3, bangCC.isVangMat());
			stmt.setBoolean(4, bangCC.isCoPhep());
			stmt.setString(5, bangCC.getGhiChu());
			stmt.setString(6, bangCC.getMaCCCN());
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
}



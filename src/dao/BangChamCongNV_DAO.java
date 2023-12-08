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
			String Sql = "SELECT * FROM dbo.BangChamCongNhanVien";
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
	
	public ArrayList<BangChamCongNV> getBangCCTheoNgay(LocalDate ngayChamCong){
		ArrayList<BangChamCongNV> bangCC = new ArrayList<BangChamCongNV>();
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
				
				bangCC.add(ccNV);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bangCC;
		
		
	}
	
	public boolean insertBangChamCongNV(BangChamCongNV bangChamCongNV) {
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            String sql = "insert into dbo.BangChamCongNhanVien (maChamCong, maNV, ngayCham, soGioTangCa, caLam, coMat, vangMat, coPhep, ghiChu) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, bangChamCongNV.getMaCCNV());
            stmt.setString(2, bangChamCongNV.getNv().getMaNV());
            stmt.setDate(3, Date.valueOf(bangChamCongNV.getNgayCham()));
            stmt.setInt(4, bangChamCongNV.getSoGioTangCa());
            stmt.setInt(5, bangChamCongNV.getCaLam());
            stmt.setBoolean(6, bangChamCongNV.isCoMat());
            stmt.setBoolean(7, bangChamCongNV.isVangMat());
            stmt.setBoolean(8, bangChamCongNV.isCoPhep());
            stmt.setString(9,bangChamCongNV.getGhiChu());
         
            
            
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
	
	public boolean updateBangChamCongNV(BangChamCongNV bangCC) {
		int n=0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "update BangChamCongNhanVien set soGioTangCa = ? , coMat=? , vangMat = ?, coPhep=? ,ghiChu=?  where maChamCong= ?";
		PreparedStatement stmt = null;
		try {
			stmt= con.prepareStatement(sql);
			stmt.setInt(1, bangCC.getSoGioTangCa());
			stmt.setBoolean(2, bangCC.isCoMat());
			stmt.setBoolean(3, bangCC.isVangMat());
			stmt.setBoolean(4, bangCC.isCoPhep());
			stmt.setString(5, bangCC.getGhiChu());
			stmt.setString(6, bangCC.getMaCCNV());
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
	
	
	public ArrayList<BangChamCongNV> getBangCCTheoNgayBPCa(LocalDate ngayChamCong,String maBP, int ca){
		ArrayList<BangChamCongNV> bangCC = new ArrayList<BangChamCongNV>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT * FROM dbo.BangChamCongNhanVien JOIN NhanVien ON BangChamCongNhanVien.maNV = NhanVien.maNV\r\n"
				+ "WHERE BangChamCongNhanVien.ngayCham = ?"
				+ "      AND NhanVien.maBP = ?"
				+ "      AND NhanVien.caLamViec = ?";
		PreparedStatement stmt = null;
		int n =0;
		try {
			stmt= con.prepareStatement(sql);
			stmt.setString(1, String.valueOf(ngayChamCong));
			stmt.setString(2, maBP);
			stmt.setInt(3, ca);
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
				
				bangCC.add(ccNV);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bangCC;
		
		
	}
	
	

}

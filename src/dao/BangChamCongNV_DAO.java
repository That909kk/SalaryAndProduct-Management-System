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
	public ArrayList<BangChamCongNV> dsBangCCtheomaNVthangnam(String maNV,int thang, int nam){
		ArrayList<BangChamCongNV> listBangChamCongNhanVien = new ArrayList<BangChamCongNV>();
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
	
	public boolean updateGhiChiBangChamCongNV(BangChamCongNV bangCC) {
		int n=0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "update BangChamCongNhanVien set ghiChu=?  where maChamCong= ?";
		PreparedStatement stmt = null;
		try {
			stmt= con.prepareStatement(sql);
			stmt.setString(1, bangCC.getGhiChu());
			stmt.setString(2, bangCC.getMaCCNV());
			n= stmt.executeUpdate();
			
		} catch (SQLException e) {	
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return n>0;
	}
	
	public BangChamCongNV layBangCCCuoiCungThangCua1NV(String maNV, int thang, int nam) {
		BangChamCongNV bangCC = new BangChamCongNV();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT TOP 1 * FROM dbo.BangChamCongNhanVien where maNV = ? and MONTH(ngayCham) = ? and YEAR(ngayCham) = ? order by ngayCham desc";
		PreparedStatement stmt = null;
		int n =0;		
		try {
			stmt= con.prepareStatement(sql);
			stmt.setString(1, maNV);
			stmt.setInt(2, thang);
			stmt.setInt(3, nam);
			ResultSet rs  = stmt.executeQuery();
			
			while(rs.next()) {
				 bangCC = new BangChamCongNV(rs.getString("maChamCong"));
				bangCC.setNgayCham(rs.getDate("ngayCham").toLocalDate());
				bangCC.setSoGioTangCa(rs.getInt("soGioTangCa"));
				bangCC.setCaLam(rs.getInt("caLam"));
				bangCC.setGhiChu(rs.getString("ghiChu"));
				bangCC.setCoPhep(rs.getBoolean("coPhep"));
				bangCC.setCoMat(rs.getBoolean("coMat"));
				bangCC.setVangMat(rs.getBoolean("vangMat"));
				NhanVien nv = new NhanVien(rs.getString("maNV"));
				bangCC.setNv(nv);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bangCC;
	}
}

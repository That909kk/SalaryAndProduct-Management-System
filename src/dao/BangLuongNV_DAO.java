package dao;

import org.junit.runners.model.Statement;

import com.microsoft.sqlserver.jdbc.SQLServerError;

import connectDB.ConnectDB;
import entity.BangChamCongNV;
import entity.BangLuongNV;
import entity.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class BangLuongNV_DAO {
	private ArrayList<BangLuongNV> listBangLuongNV;
	public BangLuongNV_DAO(){
		listBangLuongNV = new ArrayList<BangLuongNV>();
	}
	public int getSize(){
		return listBangLuongNV.size();
	}
	public ArrayList<BangLuongNV> getDSBangLuongNV(){
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from BangLuongNhanVien";
		PreparedStatement stmt = null;
		try{
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maLuongNV = rs.getString(1);
				NhanVien nv = new NhanVien(rs.getString(2));
				int thang = rs.getInt(3);
				int nam = rs.getInt(4);
				int soNgayDiLam = rs.getInt(5);
				int soNgayNghiKhongPhep = rs.getInt(6);
				double tienPhat = rs.getDouble(7);
				BangLuongNV blnv = new BangLuongNV(maLuongNV, nv, thang, nam, soNgayDiLam, soNgayNghiKhongPhep, tienPhat);
				listBangLuongNV.add(blnv);

			}

		}   catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return listBangLuongNV;
	}
	public BangLuongNV getMotBangLuongNV (String maLuong){
		BangLuongNV blnv = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from BangLuongNhanVien where maLuongNV = ?";
		PreparedStatement stmt = null;
		try{
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maLuong);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maLuongNV = rs.getString(1);
				NhanVien nv = new NhanVien(rs.getString(2));
				int thang = rs.getInt(3);
				int nam = rs.getInt(4);
				int soNgayDiLam = rs.getInt(5);
				int soNgayNghiKhongPhep = rs.getInt(6);
				double tienPhat = rs.getDouble(7);
				blnv = new BangLuongNV(maLuongNV, nv, thang, nam, soNgayDiLam, soNgayNghiKhongPhep, tienPhat);
			}

		}   catch (SQLException e) {
			e.printStackTrace();}
		catch (Exception e) {
			e.printStackTrace();}
		return blnv;
	}
	public boolean insertBangLuongNV(BangLuongNV blnv){
		int n =0;
		connectDB.ConnectDB.getInstance();
		Connection con = connectDB.ConnectDB.getConnection();
		String sql = "insert into BangLuongNhanVien values(?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		try{
			stmt = con.prepareStatement(sql);
			stmt.setString(1, blnv.getMaLuongNV());
			stmt.setString(2, blnv.getNv().getMaNV());
			stmt.setInt(3, blnv.getThang());
			stmt.setInt(4, blnv.getNam());
			stmt.setInt(5, blnv.getSoNgayDiLam());
			stmt.setInt(6, blnv.getSoNgayNghiKhongPhep());
			stmt.setDouble(7, blnv.getTienPhat());
			n = stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();} catch (Exception e) {
				e.printStackTrace();} finally { try{
					stmt.close();

				}catch (SQLException e) {
					e.printStackTrace();}

				}
		return n>0;
	}
	public boolean updateBangLuongNV(BangLuongNV blnv){
		int n =0;
		connectDB.ConnectDB.getInstance();
		Connection con = connectDB.ConnectDB.getConnection();
		String sql = "update BangLuongNhanVien set maNV = ?, thang = ?, nam = ?, soNgayDiLam = ?, soNgayNghiKhongPhep = ?, tienPhat = ? where maLuongNV = ?";
		PreparedStatement stmt = null;
		try{
			stmt = con.prepareStatement(sql);
			stmt.setString(1, blnv.getNv().getMaNV());
			stmt.setInt(2, blnv.getThang());
			stmt.setInt(3, blnv.getNam());
			stmt.setInt(4, blnv.getSoNgayDiLam());
			stmt.setInt(5, blnv.getSoNgayNghiKhongPhep());
			stmt.setDouble(6, blnv.getTienPhat());
			stmt.setString(7, blnv.getMaLuongNV());
			n = stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();} catch (Exception e) {
				e.printStackTrace();} finally { try{
					stmt.close();

				}catch (SQLException e) {
					e.printStackTrace();}

				}
		return n>0;
	}
	public boolean deleteBangLuongNV(String maLuong){
		int n =0;
		connectDB.ConnectDB.getInstance();
		Connection con = connectDB.ConnectDB.getConnection();
		String sql = "delete from BangLuongNhanVien where maLuongNV = ?";
		PreparedStatement stmt = null;
		try{
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maLuong);
			n = stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();} catch (Exception e) {
				e.printStackTrace();} finally { try{
					stmt.close();

				}catch (SQLException e) {
					e.printStackTrace();}

				}
		return n>0;
	}
	//        public ArrayList <BangLuongNV> getDSBangLuongtheomaNV(String maNV){
	//            ArrayList <BangLuongNV> listtheomaNV= new ArrayList<>();
	//            ConnectDB.getInstance();
	//            Connection con = ConnectDB.getConnection();
	//            String sql = "select * from BangLuongNhanVien where maNV = ?";
	//            PreparedStatement stmt = null;
	//            try{
	//                stmt = con.prepareStatement(sql);
	//                stmt.setString(1, maNV);
	//                ResultSet rs = stmt.executeQuery();
	//                while (rs.next()) {
	//                    String maLuongNV = rs.getString(1);
	//                    NhanVien nv = new NhanVien(rs.getString(2));
	//                    int thang = rs.getInt(3);
	//                    int nam = rs.getInt(4);
	//                    int soNgayDiLam = rs.getInt(5);
	//                    int soNgayNghiKhongPhep = rs.getInt(6);
	//                    double tienPhat = rs.getDouble(7);
	//                    BangLuongNV blnv = new BangLuongNV(maLuongNV, nv, thang, nam, soNgayDiLam, soNgayNghiKhongPhep, tienPhat);
	//                    listtheomaNV.add(blnv);
	//                    
	//                }
	//    
	//            }   catch (SQLException e) {
	//                e.printStackTrace();
	//            }catch (Exception e) {
	//                e.printStackTrace();
	//            }
	//        return listtheomaNV;

	//        }
	public ArrayList< BangLuongNV> getDSBangLuongtheoDK(int thang1, int nam1, String mabp){
		ArrayList <BangLuongNV> listtheomaNV= new ArrayList<BangLuongNV>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql ="select * from BangLuongNhanVien join NhanVien on BangLuongNhanVien.maNV=NhanVien.maNV where thang =? and nam = ? and maBP=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, thang1);
			stmt.setInt(2, nam1);
			stmt.setString(3, mabp);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maLuongNV = rs.getString(1);
				NhanVien nv = new NhanVien(rs.getString(2));
				int thang = rs.getInt(3);
				int nam = rs.getInt(4);
				int soNgayDiLam = rs.getInt(5);
				int soNgayNghiKhongPhep = rs.getInt(6);
				double tienPhat = rs.getDouble(7);
				BangLuongNV blnv = new BangLuongNV(maLuongNV, nv, thang, nam, soNgayDiLam, soNgayNghiKhongPhep, tienPhat);
				listtheomaNV.add(blnv);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return listtheomaNV;

	}
	public int getSizeCuaDSTheoNhanVien(int thang, int nam, String mabp){
		return this.getDSBangLuongtheoDK(thang,nam,mabp).size();
	}
	public ArrayList <BangLuongNV> getDSBangLuongNVtheoBoPhan (String maBP){
		ArrayList <BangLuongNV> listtheoBoPhan= new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from BangLuongNhanVien where maNV in (select maNV from NhanVien where maBP = ?)";
		PreparedStatement stmt = null;
		try{
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maBP);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maLuongNV = rs.getString(1);
				NhanVien nv = new NhanVien(rs.getString(2));
				int thang = rs.getInt(3);
				int nam = rs.getInt(4);
				int soNgayDiLam = rs.getInt(5);
				int soNgayNghiKhongPhep = rs.getInt(6);
				double tienPhat = rs.getDouble(7);
				BangLuongNV blnv = new BangLuongNV(maLuongNV, nv, thang, nam, soNgayDiLam, soNgayNghiKhongPhep, tienPhat);
				listtheoBoPhan.add(blnv);

			}

		}   catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return listtheoBoPhan;
	}
	public ArrayList <BangLuongNV> getDSbangLuongtheoNVvaCC(String maNV, String maCC){
		ArrayList <BangLuongNV> listtheoNVvaCC= new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from BangLuongNhanVien where maNV = ? and maLuongNV in (select maLuongNV from BangChamCongNV where maChamCong = ?)";
		PreparedStatement stmt = null;
		try{
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maNV);
			stmt.setString(2, maCC);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maLuongNV = rs.getString(1);
				NhanVien nv = new NhanVien(rs.getString(2));
				int thang = rs.getInt(3);
				int nam = rs.getInt(4);
				int soNgayDiLam = rs.getInt(5);
				int soNgayNghiKhongPhep = rs.getInt(6);
				double tienPhat = rs.getDouble(7);
				BangLuongNV blnv = new BangLuongNV(maLuongNV, nv, thang, nam, soNgayDiLam, soNgayNghiKhongPhep, tienPhat);
				listtheoNVvaCC.add(blnv);

			}

		}   catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return listtheoNVvaCC;
	}
}
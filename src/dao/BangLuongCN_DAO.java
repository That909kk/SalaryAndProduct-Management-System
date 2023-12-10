package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.microsoft.sqlserver.jdbc.SQLServerError;
import org.junit.runners.model.Statement;
import connectDB.ConnectDB;
import entity.BangLuongCN;
import entity.BangLuongNV;
import entity.CongNhan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BangLuongCN_DAO {
    private ArrayList<BangLuongCN> listBangLuongCN;
    public BangLuongCN_DAO(){
        listBangLuongCN = new ArrayList<BangLuongCN>();
    }
    public int getSize(){
        return listBangLuongCN.size();
    }
    public BangLuongCN getMotBangLuongCNTheoThangNam(String maCN, int thang, int nam) {
         BangLuongCN bangLuongCN = null;
        ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
        String sql = "SELECT * FROM dbo.BangLuongCongNhan WHERE MaCN = ? AND Thang = ? AND Nam = ?";
       
        PreparedStatement  stmt = null;
       try{
        stmt = con.prepareStatement(sql);
        stmt.setString(1, maCN);
        stmt.setInt(2, thang);
        stmt.setInt(3, nam);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            String maLuongCN = rs.getString(1);
            int thang1 = rs.getInt(3);
            int nam1 = rs.getInt(4);
            int tongSanLuong = rs.getInt(5);
            int soNgayNghiKhongPhep = rs.getInt(6);
            double tienPhat = rs.getDouble(7);
            double bhxh = rs.getDouble(8);
            double luongTong = rs.getDouble(9);
            CongNhan congNhan = new CongNhan_DAO().getCongNhanTheoMaCN(rs.getString(2));
            bangLuongCN = new BangLuongCN(maLuongCN,congNhan, thang1, nam1, tongSanLuong, soNgayNghiKhongPhep, tienPhat, bhxh, luongTong);
            

        }
         }catch(SQLException e){
              e.printStackTrace();

       }
         return bangLuongCN;
        
    }
    public boolean insertBangLuongCN(BangLuongCN bangLuongCN){
        int n = 0;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO dbo.BangLuongCongNhan VALUES(?,?,?,?,?,?,?,?,?)";
        try{
            stmt = con.prepareStatement(sql);
            stmt.setString(1, bangLuongCN.getMaLuongCN());
            stmt.setString(2, bangLuongCN.getCn().getMaCN());
            stmt.setInt(3, bangLuongCN.getThang());
            stmt.setInt(4, bangLuongCN.getNam());
            stmt.setInt(5, bangLuongCN.getTongSanLuong());
            stmt.setInt(6, bangLuongCN.getSoNgayNghiKhongPhep());
            stmt.setDouble(7, bangLuongCN.getTienPhat());
            stmt.setDouble(8, bangLuongCN.getBhxh());
            stmt.setDouble(9, bangLuongCN.getLuongTong());
            n = stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return n>0;
    }
    public boolean updateBangLuongCN(BangLuongCN bangLuongCN){
        int n = 0;
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        String sql = "UPDATE dbo.BangLuongCongNhan SET sanLuongTong = ?, SoNgayNghiKhongPhep = ?, TienPhat = ?, BHXH = ?, LuongTong = ? WHERE MaLuongCN = ?";
        try{
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, bangLuongCN.getTongSanLuong());
            stmt.setInt(2, bangLuongCN.getSoNgayNghiKhongPhep());
            stmt.setDouble(3, bangLuongCN.getTienPhat());
            stmt.setDouble(4, bangLuongCN.getBhxh());
            stmt.setDouble(5, bangLuongCN.getLuongTong());
            stmt.setString(6, bangLuongCN.getMaLuongCN());
            n = stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return n>0;
    }
}

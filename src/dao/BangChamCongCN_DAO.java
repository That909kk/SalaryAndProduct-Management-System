package dao;

import java.sql.Connection;
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
				java.sql.Date ngayCham = rs.getDate(2);
				LocalDate NgayCham = ngayCham.toLocalDate();
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
}

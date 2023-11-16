package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			String Sql = "SELECT * FROM dbo.ChamCongNhanVien";
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

}

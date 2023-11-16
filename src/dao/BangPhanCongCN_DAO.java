package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.BangPhanCongCN;
import entity.CongDoan;
import entity.CongNhan;

public class BangPhanCongCN_DAO {
	private ArrayList<BangPhanCongCN> listPCCD;
	
	public BangPhanCongCN_DAO() {
		listPCCD = new ArrayList<BangPhanCongCN>();
	}
	
	public ArrayList<BangPhanCongCN> getDSPhanCongCongDoanTheoMaCD(String ma) {
		ArrayList<BangPhanCongCN> listPCCDTheoMaCD = new ArrayList<BangPhanCongCN>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "Select * from BangPhanCongCN where maCD = ?";
		
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ma);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maPCCN = rs.getString(1);
				boolean trangThai = rs.getBoolean(2);
				LocalDate ngayPhanCong = rs.getDate(3).toLocalDate();
				int soLuongSP = rs.getInt(4);
				CongNhan cn = new CongNhan(rs.getString(5));
				CongDoan cd = new CongDoan(rs.getString(6));
				
				BangPhanCongCN bangPCCN = new BangPhanCongCN(maPCCN, trangThai, ngayPhanCong, soLuongSP, cn, cd);
				listPCCDTheoMaCD.add(bangPCCN);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listPCCDTheoMaCD;
	}
}

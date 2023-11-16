package dao;

import java.sql.Connection;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.CongNhan;

public class CongNhan_DAO {
	private ArrayList<CongNhan> listCN;
	
	public CongNhan_DAO() {
		listCN = new ArrayList<CongNhan>();
	}

}

package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	private static ConnectDB instance = new ConnectDB();
	public static Connection con = null;
	
	public static ConnectDB getInstance() {
		return instance;
	}
	
	public void connect() throws SQLException {
		String user = "sa";
		String pass = "123456789";
		String url = "jdbc:sqlserver://localhost:1433;databaseName=QLLSP;ecrypt=true;trustServerCertificate=true";
		con = DriverManager.getConnection(url, user, pass);
	}
	
	public void disconnect() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
	}
	
	public static Connection getConnection() {
		return con;
	}

	public static void closeConnection(Connection con2) {
		// TODO Auto-generated method stub
		
	}
}

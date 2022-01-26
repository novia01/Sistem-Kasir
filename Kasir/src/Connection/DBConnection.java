package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	Connection conn;
	
	public Connection open() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kasir","root","");
			System.out.println("Sukses Tersambung");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		return conn;
	}
	
	public Connection close() {
		try {
			if (conn != null) {
				conn.close();
				System.out.println("Berhasil terputus dari database");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

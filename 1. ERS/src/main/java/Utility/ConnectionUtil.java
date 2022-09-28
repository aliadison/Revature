package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil{
	
	private static String db_url = System.getenv("db_url");
	private static String db_user = System.getenv("db_user");
	private static String db_password = System.getenv("db_password");
	
	private static Connection conn = null ;
	
	public static Connection makeConnection() {
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(db_url, db_user, db_password);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}

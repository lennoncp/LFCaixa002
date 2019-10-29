package app.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
	
	private static String user = "nb90r1rn0st5md2x";
	private static String password = "qnarfazy163hei3o";
	//private static String url = "jdbc:mysql://192.168.1.108:3306/caixa?useSSL=false&useTimezone=true&serverTimezone=UTC";
	private static String url = "jdbc:mysql://g8r9w9tmspbwmsyo.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/rn6zz3jq7iip2b9i?useSSL=false&useTimezone=true&serverTimezone=UTC";

	private static Connection con;
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static void closeConnection() {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

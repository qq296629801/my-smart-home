package cn.ymsys.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Dbcon {

	public static String url = "jdbc:mysql://192.168.0.111:3306/smart?user=root&password=root&useUnicode=true&characterEncoding=UTF8";
	public static Connection conn;
	public static Statement stmt;

	public static Statement getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			stmt = conn.createStatement();
			return stmt;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void close() {
		if (conn != null || stmt != null) {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

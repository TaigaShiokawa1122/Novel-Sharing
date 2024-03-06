package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		final String URL = "jdbc:mysql://localhost/novel_sharing_app_db?useUnicode=true&characterEncoding=utf8";
		final String USER = "root";
		final String PASS = "root";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(URL,USER,PASS);
		return con;
		
	}

}

package database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DatabaseUtilities {

	public static Connection getDatabaseConnection() throws ClassNotFoundException, SQLException {
		String user = System.getenv("DB_USER");
		String password = System.getenv("DB_PW");
		String url = System.getenv("DB_URL");
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(url, user, password);
		
		return connection;
	}
	
	public static void closeConnection(Connection c) {
		try {
			c.close();
		} catch (Exception ex) {
			// Left intentionally blank
		}
	}
	
}

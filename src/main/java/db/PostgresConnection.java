package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection {
	private static final String url = "jdbc:postgresql://localhost:5432/pontosServlet";
	private static final String user = "postgres";
	private static final String password = "1234";
	
	public static Connection createConnection() {
		
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver found");	
		} catch(ClassNotFoundException e) {
			System.out.println("Driver not found. " + e.getMessage() );
		}
		
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to database");
			return connection;
		} catch(SQLException e) {
			System.out.println("Connection failed." + e.getMessage());
			return null;
		}
	}
}

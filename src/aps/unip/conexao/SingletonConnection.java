package aps.unip.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
	
	private static String URL = "jdbc:mysql://db4free.net:3306/apschat";
	private static String USER = "apsunip";
	private static String PASSWORD = "aps12345";
	private static Connection connection = null;
	
	static {
		connect();
	}
	
	private static void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			if (connection == null) {
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
				connection.setAutoCommit(false);
				System.out.println("conectado");
			}
		} catch (ClassNotFoundException | SQLException e){
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
}

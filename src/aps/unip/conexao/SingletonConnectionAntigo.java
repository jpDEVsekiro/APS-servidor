package aps.unip.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SingletonConnectionAntigo {
	
	private static String URL = "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10407324";
	private static String USER = "sql10407324";
	private static String PASSWORD = "fjJtBfPqQt";
	private static Connection connection = null;
	
	static {
		connect();
	}
	
	public SingletonConnectionAntigo() {
		
	}
	
	private static void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			if (connection == null) {
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
				connection.setAutoCommit(false);
				System.out.println("[BANCO DE DADOS CONECTADO]");
			}
		} catch (ClassNotFoundException | SQLException e){
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
	public static void close(PreparedStatement statement) {
		try {
			if( statement != null) {
				statement.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement statement, ResultSet resultSet) {
		try {
			if(resultSet != null) {
				
				resultSet.close();
			}
			if(statement != null) {				
				statement.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}

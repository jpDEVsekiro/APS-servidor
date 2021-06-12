package aps.unip.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SingletonConnection {
	
	private static String URL = "jdbc:sqlite:BD/BancoDeDados.db";
	private static Connection connection = null;
	
	static {
		connect();
	}
	
	public SingletonConnection() {
		
	}
	
	private static void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			if (connection == null) {
				connection = DriverManager.getConnection(URL);
				connection.setAutoCommit(false);
				System.out.println("[BANCO DE DADOS CONECTADO]");
			}
		} catch (SQLException | ClassNotFoundException e){
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

package aps.unip.conexao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.protocol.Resultset;

public class teste {
	public static void main(String[] args) {
		try {
			String SQL = "show databases;";
			PreparedStatement preparedStatement = SingletonConnection.getConnection().prepareStatement(SQL);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				System.out.println(resultSet.getString("Database"));
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}

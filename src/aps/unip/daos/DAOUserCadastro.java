package aps.unip.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import aps.unip.conexao.SingletonConnection;

public class DAOUserCadastro {
	private Connection connection = SingletonConnection.getConnection();
	
	private boolean checarExistencia(Map<String, Object> parametros) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			String SQL = String.format(
					"select * from usuario where email_usuario = \"%s\";", 
					parametros.get("email"));
			statement = connection.prepareStatement(SQL);
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				return false;
			}
			else {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			SingletonConnection.close(statement, resultSet);
		}
	}
	
	public boolean cadastrarUsuario(Map<String, Object> parametros) {
		if(checarExistencia(parametros)) {
			PreparedStatement statement = null;
			try {
				String SQL = "INSERT INTO `apschat`.`usuario`(`nome_usuario`,`senha_usuario`,`email_usuario`,`foto`)VALUES(?,?,?,?);";
				statement = connection.prepareStatement(SQL);
				statement.setString(1, (String) parametros.get("nome"));
				statement.setString(2, (String) parametros.get("senha"));
				statement.setString(3, (String) parametros.get("email"));
				statement.setBytes(4, (byte[]) parametros.get("foto"));
				statement.execute();
				connection.commit();
				return true;
			}catch (Exception e) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			 e.printStackTrace();
			} finally {
				SingletonConnection.close(statement);
			}
		}
		return false;
	}
}
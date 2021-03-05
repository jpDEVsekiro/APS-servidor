package aps.unip.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import aps.unip.conexao.SingletonConnection;

public class DAOUserCadastro {
	private Connection connection = SingletonConnection.getConnection();
	
	public boolean cadastrarUsuario(Map<String, Object> parametros) {
		try {
			String SQL = String.format(
					"INSERT INTO `apschat`.`usuario`(`nome_usuario`,`apelido_usuario`,`senha_usuario`,`login_usuario`)VALUES('%s','%s','%s','%s');",
					parametros.get("nome"),
					parametros.get("apelido"),
					parametros.get("senha"),
					parametros.get("login"));
			PreparedStatement statement = connection.prepareStatement(SQL);
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
		}
		return false;
	}
}
/*

 */

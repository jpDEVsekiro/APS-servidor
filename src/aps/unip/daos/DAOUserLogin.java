package aps.unip.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import aps.unip.conexao.SingletonConnection;
import aps.unip.enums.Status;
import aps.unip.usuarios.Usuario;

public class DAOUserLogin {
	Connection connection = SingletonConnection.getConnection();
	
	public Map<String, Object> validarUsuario(String email, String senha) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			String SQL = String.format(
					"SELECT * FROM usuario WHERE usuario_email='%s'AND usuario_senha='%s';",
					email,
					senha);
			statement = connection.prepareStatement(SQL);
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				Map<String, Object> retorno = new HashMap<String, Object>();
				retorno.put("nome", resultSet.getString("usuario_nome"));
				retorno.put("id", resultSet.getInt("usuario_id"));
				retorno.put("foto", resultSet.getBytes("usuario_foto"));
				return retorno;
				
			}
			else {
				return null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			SingletonConnection.close(statement, resultSet);
		}
	}
}








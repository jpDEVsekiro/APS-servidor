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
	
	public Map<String, Object> validarUsuario(String usuario, String senha) {
		PreparedStatement statement;
		ResultSet resultSet;
		Map<String, Object> retorno = new HashMap<String, Object>();
		try {
			String SQL = String.format(
					"SELECT * FROM usuario WHERE login_usuario='%s' AND senha_usuario='%s';",
					usuario,
					senha);
			statement = connection.prepareStatement(SQL);
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				retorno.put("nome", resultSet.getString("nome_usuario"));
				retorno.put("apelido",resultSet.getString("apelido_usuario"));
				return retorno;
			}
			else {
				return null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

//requisicao LOGIN
//status vazio
//login
//senha

//login reply
//status ok
//Nome string
//apelido String










package aps.unip.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import aps.unip.conexao.SingletonConnection;

public class DAOBuscarUsuarios {
	private Connection connection = SingletonConnection.getConnection();
	
	public Object[][] busacarUsuarios(String nome){
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		ArrayList<Object> resultados = new ArrayList<Object>();
		try {
			String SQL = "SELECT * from `apschat`.`usuario` WHERE nome_usuario like '%"+nome+"%';";
		
			statement = connection.prepareStatement(SQL);
			resultSet = statement.executeQuery();

			
			while (resultSet.next()) {
				resultados.add(resultSet.getString("id_usuario"));
				resultados.add(resultSet.getString("nome_usuario"));
				resultados.add(resultSet.getBytes("foto"));
			}
			int listSize = resultados.size();

			Object[][] retorno = new Object[listSize/3][3];
			

			if (listSize >= 2) {
				for (int i = 0 ; i < ((listSize)/3) ; i++) {
					retorno[i][0] = resultados.get(0);
					retorno[i][1] = resultados.get(1);
					retorno[i][2] = resultados.get(2);
					resultados.remove(0);
					resultados.remove(0);
					resultados.remove(0);
				}
				return retorno;
			}
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

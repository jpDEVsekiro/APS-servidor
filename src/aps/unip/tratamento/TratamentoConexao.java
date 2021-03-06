package aps.unip.tratamento;

import java.net.Socket;
import java.util.Map;

import aps.unip.daos.DAOUserLogin;
import aps.unip.enums.Requisicao;
import aps.unip.enums.Status;
import aps.unip.protocolo.Mensagem;
import aps.unip.usuarios.Usuario;
import aps.unip.usuarios.Usuarios;

public class TratamentoConexao {
	
	public void tratarConexao(Socket socket) {
		Usuario usuario = new Usuario(socket);
		
		if(usuario.getMensagemInput().getRequisicao() == Requisicao.CADASTRO) {
			
		}else if (usuario.getMensagemInput().getRequisicao() == Requisicao.LOGIN) {
			DAOUserLogin login = new DAOUserLogin();
			Map<String, Object> dados = login.validarUsuario((String) usuario.getMensagemInput().getParametro("usuario"),
			                                                 (String) usuario.getMensagemInput().getParametro("senha"));
			if(dados != null) {
				usuario.setNome((String) dados.get("nome"));
				usuario.setApelido((String) dados.get("apelido"));
				Usuarios.addUsuario(usuario);
				
				Mensagem mensagemRespostaLogin = new Mensagem();
				mensagemRespostaLogin.setRequisicao(Requisicao.LOGIN_REPLY);
				mensagemRespostaLogin.setStatus(Status.STATUS_OK);
				mensagemRespostaLogin.setParametros("nome", dados.get("nome"));
				mensagemRespostaLogin.setParametros("apelido", dados.get("apelido"));
				mensagemRespostaLogin.setParametros("mensagem", "usuario logado");
				usuario.dispararMensagem(mensagemRespostaLogin);
				
				usuario.iniciarRequisicaoListener();
			}else {
				Mensagem mensagemRespostaLogin = new Mensagem();
				mensagemRespostaLogin.setRequisicao(Requisicao.LOGIN_REPLY);
				mensagemRespostaLogin.setStatus(Status.USUARIO_NAO_CADASTRADO);
				mensagemRespostaLogin.setParametros("mensagem", "Usuario nao cadastrado");
				usuario.dispararMensagem(mensagemRespostaLogin);
			}
			
			//consultar no banco de dados
			/// retorno de map com os dados do usuario, se estiver cadastrado
			/// retorno de null se nao estiver
		}
		
		
	}
	
}

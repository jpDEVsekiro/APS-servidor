package aps.unip.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import aps.unip.daos.DAOUserCadastro;
import aps.unip.enums.Requisicao;
import aps.unip.enums.Status;
import aps.unip.protocolo.Mensagem;


public class ServerUtils {

	/*
	 * Esse metodo filtra a solicitacao do cliente quando é feita a primeira
	 * conexao.
	 * 
	 * @parametro:socket -> soquete recebido na conexao com o cliente.
	 * 
	 * @return void.
	 */
	public void tratarConexao(Socket socket) {
		new Thread() {
			public void run() {
				try {

					ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
					ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

					Mensagem mensagemInput = (Mensagem) input.readObject();
					/*
					 * Bloco de cadastro
					 */
					if (mensagemInput.getRequisicao() == Requisicao.CADASTRO) {
						/*
						 * Se algum parametro de login for null, retornar erro de parametro.
						 */
						if (mensagemInput.getParametro("nome") == null 
								|| mensagemInput.getParametro("apelido") == null
								|| mensagemInput.getParametro("senha") == null
								|| mensagemInput.getParametro("login") == null) {

							Mensagem mensagemOutput = new Mensagem();
							mensagemOutput.setRequisicao(Requisicao.CADASTRO_REPLY);
							mensagemOutput.setStatus(Status.STATUS_ERRO_PARAMETRO);
							mensagemOutput.setParametros("mensagem", "Falta de parametros");

							output.writeObject(mensagemOutput);
							output.flush();
						} else {
							DAOUserCadastro cadastro = new DAOUserCadastro();
							if (cadastro.cadastrarUsuario(mensagemInput.getMap())) {

								Mensagem mensagemOutput = new Mensagem();
								mensagemOutput.setRequisicao(Requisicao.CADASTRO_REPLY);
								mensagemOutput.setStatus(Status.STATUS_OK);
								mensagemOutput.setParametros("mensagem", "Usuario cadastrado");

								output.writeObject(mensagemOutput);
								output.flush();
							} else {
								Mensagem mensagemOutput = new Mensagem();
								mensagemOutput.setRequisicao(Requisicao.CADASTRO_REPLY);
								mensagemOutput.setStatus(Status.STATUS_ERRO_CADASTRO);
								mensagemOutput.setParametros("mensagem", "erro ao tentar cadastrar");

								output.writeObject(mensagemOutput);
								output.flush();
							}
						}
					} else if (mensagemInput.getRequisicao() == Requisicao.LOGIN) {

						/*
						 * executa as regras de negocio. retorna estatos de sucesso pelo output do
						 * usuario e adiciona na lista de usuarios online, caso estiver cadastrado.
						 * retorna estatos de erro pelo output do usuario, caso nao esteja cadastrado.
						 */
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
		
	}

}

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
				ObjectOutputStream output;
				ObjectInputStream input;
				try {

					output = new ObjectOutputStream(socket.getOutputStream());
					input = new ObjectInputStream(socket.getInputStream());

					Mensagem mensagemInput = (Mensagem) input.readObject();
					Mensagem mensagemOutput = new Mensagem();
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

							mensagemOutput.setRequisicao(Requisicao.CADASTRO_REPLY);
							mensagemOutput.setStatus(Status.STATUS_ERRO_PARAMETRO);
							mensagemOutput.setParametros("mensagem", "Falta de parametros");
						} else {
							DAOUserCadastro cadastro = new DAOUserCadastro();
							if (cadastro.cadastrarUsuario(mensagemInput.getMap())) {

								mensagemOutput.setRequisicao(Requisicao.CADASTRO_REPLY);
								mensagemOutput.setStatus(Status.STATUS_OK);
								mensagemOutput.setParametros("mensagem", "Usuario cadastrado");
							} else {

								mensagemOutput.setRequisicao(Requisicao.CADASTRO_REPLY);
								mensagemOutput.setStatus(Status.STATUS_ERRO_CADASTRO);
								mensagemOutput.setParametros("mensagem", "erro ao tentar cadastrar");
							}
						}
					} else if (mensagemInput.getRequisicao() == Requisicao.LOGIN) {


					}
					
					output.writeObject(mensagemOutput);
					output.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
		
	}

}

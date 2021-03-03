package aps.unip.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import aps.unip.enums.Requisicao;
import aps.unip.enums.Status;
import aps.unip.protocolo.Mensagem;

public class Server {
	private ServerSocket serverSocket = null;

	private void criarServerSocket(int port) throws IOException {
		serverSocket = new ServerSocket(port);
	}

	private Socket esperaConexao() throws IOException {

		return serverSocket.accept();
	}

	private static void fecharSocket(Socket socket) throws IOException {
		socket.close();
	}
	////////////////////////////// Testes //////////////////////////////////
	/*
	 * Classe DAO simulacao de cadastro no BD
	 * @parametro; mensagemInput.
	 * @return: true quando cadastrado,False quando erro.
	 */
	public static boolean DAOcadastrarUsuario(Mensagem mensagem) {
		
		String nome, segundoNome, senha, login;
		nome = (String) mensagem.getParametro("nome");
		segundoNome = (String) mensagem.getParametro("segundoNome");
		senha = (String) mensagem.getParametro("senha");
		login = (String) mensagem.getParametro("login");
		System.out.println("SERVIDOR: Usuario "+nome+" "+segundoNome + " senha: "+senha+" login:"+login+ ". Cadastrado no BD");
		
		return true;
	}
	
	/*
	 * Classe util tratamento da requisicao de cadastro
	 * @parametro; mensagemInput.
	 * @return: Retorna uma mensagem com Status de erro ou status OK, dependendo da resposta do BD.
	 */
	public static Mensagem UTILcadastroUsuario(Mensagem mensagemInput){
		Mensagem mensagemOutout = new Mensagem();
		if(DAOcadastrarUsuario(mensagemInput)){
			mensagemOutout.setStatus(Status.STATUS_OK);
			mensagemOutout.setParametros("mensagem", "Usuario Cadastrado");
		}
		else {
			mensagemOutout.setStatus(Status.STATUS_ERRO_CADASTRO);
			mensagemOutout.setParametros("mensagem", "Nao foi possivel Cadastrar");
		}
		return mensagemOutout;
	}
	
	///////////////////////////////////////////////////////////////////////
	
	
	
	private static void tratarConexao(Socket socket) throws IOException {
		ObjectOutputStream output = null;
		ObjectInputStream input = null;

		try {
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());

			Mensagem mensagemInput = (Mensagem) input.readObject();
			if(mensagemInput.getRequisicao() == Requisicao.CADASTRO) {
				output.writeObject(UTILcadastroUsuario(mensagemInput));
				output.flush();
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (output != null) {
				output.close();
			}
			if (input != null) {
				input.close();
			}
			fecharSocket(socket);
		}
	}

	public static void main(String[] args) {
		try {
				Server server = new Server();
				server.criarServerSocket(5555);
			for (;;) {
				System.out.println("Esperando conexao");
				Socket socket = server.esperaConexao();
				System.out.println("Cliente Conectado");
				tratarConexao(socket);
				System.out.println("Fim");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

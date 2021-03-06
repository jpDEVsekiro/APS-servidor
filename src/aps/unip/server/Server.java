package aps.unip.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import aps.unip.enums.Requisicao;
import aps.unip.enums.Status;
import aps.unip.protocolo.Mensagem;
import aps.unip.tratamento.TratamentoConexao;
import aps.unip.tratamento.TratamentoRequisicao;
import aps.unip.usuarios.Usuario;
import aps.unip.usuarios.Usuarios;

public class Server {
	private ServerSocket serverSocket = null;

	private void criarServerSocket(int port) throws IOException {
		serverSocket = new ServerSocket(port);
	}

	private Socket esperaConexao() throws IOException {
		return serverSocket.accept();
	}
	
	public static void main(String[] args) {
		try {
			Server server = new Server();
			server.criarServerSocket(5555);
			for (;;) {
				System.out.println("Esperando conexao");
				Socket socket = server.esperaConexao();
				System.out.println("Cliente Conectado");
				TratamentoConexao conexao = new TratamentoConexao();
				conexao.tratarConexao(socket);
				System.out.println("Fim");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}



package aps.unip.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import aps.unip.conexao.SingletonConnection;
import aps.unip.tratamento.TratamentoConexao;


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
			server.criarServerSocket(80);
			@SuppressWarnings("unused")
			SingletonConnection bdConnection = new SingletonConnection();
			for (;;) {
				System.out.println("[SERVIDOR AGUARDANDO NOVA CONEXÃO]");
				Socket socket = server.esperaConexao();
				System.out.println("[CLIENTE CONECTADO]");
				new Thread() {
					public void run() {
						TratamentoConexao conexao = new TratamentoConexao();
						conexao.tratarConexao(socket);
					}
				}.start();
				System.out.println("//-----------------------//");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}



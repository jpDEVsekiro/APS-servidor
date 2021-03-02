package aps.unip.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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

	private static void tratarConexao(Socket socket) throws IOException {
		ObjectOutputStream output = null;
		ObjectInputStream input = null;

		try {
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());

			String msg = input.readUTF();
			System.out.println("Mensagem recebida..." + msg);
			output.writeUTF("ola mundo");
			output.flush();

		} catch (IOException e) {
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

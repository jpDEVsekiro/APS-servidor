package aps.unip.usuarios;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import aps.unip.protocolo.Mensagem;
import aps.unip.tratamento.TratamentoRequisicao;

public class Usuario {
	private String nome;
	private String apelido;
	private Socket socket;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	Mensagem mensagemInput;
	
	public Usuario(Socket socket) {
		try {	
			this.socket = socket;
			this.output = new ObjectOutputStream(socket.getOutputStream());
			this.input = new ObjectInputStream(socket.getInputStream());
			mensagemInput = (Mensagem) input.readObject();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Thread requisicaoListener = new Thread() {
		public void run() {
			for (;;) {
				try {
					mensagemInput = (Mensagem) input.readObject();
					TratamentoRequisicao tratamento = new TratamentoRequisicao();
					Mensagem mensagemOutput = tratamento.tratarRequisicao(mensagemInput);
					dispararMensagem(mensagemOutput);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	};
	
	public void dispararMensagem(Mensagem mensagemOutput) {
		new Thread() {
			public void run() {
				try {
					output.writeObject(mensagemOutput);
					output.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	};
	
	public void iniciarRequisicaoListener() {
		requisicaoListener.start();
	}
	
	public void fecharUsuario() throws IOException {
		if(socket != null) {
			socket.close();
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) throws IOException {
		this.socket = socket;
	}

	public Mensagem getMensagemInput() {
		return mensagemInput;
	}

	public void setMensagemInput(Mensagem mensagemInput) {
		this.mensagemInput = mensagemInput;
	}

	public String toString() {
		return "Usuario [nome=" + nome + ", apelido=" + apelido + ", socket=" + socket +
				 "]";
	}
}

package aps.unip.tratamento;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import aps.unip.daos.DAOUserLogin;
import aps.unip.enums.Requisicao;
import aps.unip.enums.Status;
import aps.unip.protocolo.Mensagem;
import aps.unip.usuarios.Usuario;
import aps.unip.usuarios.Usuarios;

public class TratamentoRequisicao{

	
	private Mensagem cadastrarUsuario() {
		System.out.println("Cadastrado");
		return null;
	}
	
	private Mensagem logarUsuario(Mensagem mensagem) throws ClassNotFoundException, IOException {
		
		Mensagem mensagemOutput = new Mensagem();	
		DAOUserLogin login = new DAOUserLogin();
		
		
		//Usuario usuario = login.validarUsuario(mensagemInput.getMap());
//		if(usuario != null) {
//
//			usuario.setSocket(socket);
//			Usuarios.setUsuario(usuario);
//
//
//			mensagemOutput.setRequisicao(Requisicao.LOGIN_REPLY);
//			mensagemOutput.setStatus(Status.STATUS_OK);
//			mensagemOutput.setParametros("mensagem", "Usuario logado");
//			mensagemOutput.setParametros("nome", usuario.getNome());
//			mensagemOutput.setParametros("apelido", usuario.getApelido());
//		}
//		else {
//			mensagemOutput.setParametros("requisicao", Requisicao.LOGIN_REPLY);
//			mensagemOutput.setParametros("status", Status.STATUS_ERRO_LOGIN);
//			mensagemOutput.setParametros("mensagem", "Usuario nao registrado");
//		}
//		output.writeObject(mensagemOutput);
//		output.flush();
//		teste.mandarm();
		return null;
	}
	public Mensagem tratarRequisicao(Mensagem mensagemInput) {
		try {
			if (mensagemInput.getRequisicao() == Requisicao.CADASTRO) {
				return cadastrarUsuario();

			} else if (mensagemInput.getRequisicao() == Requisicao.LOGIN) {
				return logarUsuario(mensagemInput);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; // VAI RETORNAR MENSAGEM DE ERRO
	}
	
}

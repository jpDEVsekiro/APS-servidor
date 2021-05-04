package aps.unip.conexao;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import aps.unip.daos.DAOBuscarUsuarios;
import aps.unip.daos.DAOMensagens;
import aps.unip.daos.DAOUserCadastro;
import aps.unip.daos.DAOUserLogin;
import aps.unip.protocolo.Mensagem;

public class teste {
	
	public static byte[] imageToBytes(File file,String tipo) {
		try {
			byte[] retorno = Files.readAllBytes(file.toPath());
			return retorno;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		//================= teste Buscar mensagens arquivadas
//		DAOMensagens daoMensagens = new DAOMensagens();
//		Object[][] retorno = daoMensagens.buscarMensagensArquivadas(3);
//		System.out.println();
		
		//================= teste Arquivar mensagem
		
//		DAOMensagens daoMensagens = new DAOMensagens();
//		Mensagem mensagem = new Mensagem();
//		mensagem.setParametros("remetente_id", 1);
//		mensagem.setParametros("destinatario_id", 2);
//		mensagem.setParametros("mensagem", "teste de arquivo de mensagem");
//		daoMensagens.arquivarMensage(mensagem);
		
		
		
//		//================= teste Busca
//		DAOBuscarUsuarios buscarUsuarios = new DAOBuscarUsuarios();
//		Object[][] retorno = buscarUsuarios.busacarUsuarios("ad", 1);
//		System.out.println();
		
//		//================= teste login
//		DAOUserLogin daoUserLogin = new DAOUserLogin();
//		Map<String, Object> retorno = daoUserLogin.validarUsuario("admin", "admin");
//		System.out.println("Nome =====> "+retorno.get("nome"));
//		System.out.println("ID =====> "+retorno.get("id"));
//		byte[] foto = (byte[]) retorno.get("foto");
//		System.out.println("foto =====> "+ Arrays.toString(foto));
		
		
		
		//================= teste cadastro
//		DAOUserCadastro cadastro = new DAOUserCadastro();
//		Map<String, Object> parametros = new HashMap<String, Object>();
//		parametros.put("nome","teste3");
//		parametros.put("senha","teste3");
//		parametros.put("email","teste3");
//		
//		try {
//			
//			File fileReader = new File("C:/Users/Talles/Desktop/APS-workspace/Cliente/imgs/SemFoto.png");
//			
//			byte[] imagem = imageToBytes(fileReader, "png");
//			parametros.put("foto", imagem);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		cadastro.cadastrarUsuario(parametros);
//		
//		parametros.put("foto","admin");
	}
}

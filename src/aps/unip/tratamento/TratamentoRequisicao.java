package aps.unip.tratamento;


import aps.unip.daos.DAOBuscarUsuarios;
import aps.unip.enums.Requisicao;
import aps.unip.enums.Status;
import aps.unip.protocolo.Mensagem;
import aps.unip.usuarios.Usuario;
import aps.unip.usuarios.Usuarios;


public class TratamentoRequisicao{
	private final String ERRO_USUARIO_OFFLINE = "Cannot invoke \"aps.unip.usuarios.Usuario.dispararMensagem(aps.unip.protocolo.Mensagem)\" because \"usuarioDestinatario\" is null";
			
	private Mensagem enviarMensagem(Mensagem mensagemInput) {
		Mensagem mensagemDestinatario;
		Mensagem mensagemRemetenteReply;
		try {
			mensagemDestinatario = mensagemInput;
			mensagemDestinatario.setRequisicao(Requisicao.NOVA_MENSAGEM);
			mensagemDestinatario.setStatus(Status.NOVA_MENSAGEM);
			Usuario usuarioDestinatario = Usuarios.getUsuario((Integer) mensagemInput.getParametro("destinatario_id"));
			usuarioDestinatario.dispararMensagem(mensagemDestinatario);

			mensagemRemetenteReply = new Mensagem();
			mensagemRemetenteReply.setRequisicao(Requisicao.ENVIAR_MENSAGEM_REPLY);
			mensagemRemetenteReply.setStatus(Status.MENSAGEM_ENVIADA);
			mensagemRemetenteReply.setParametros("mensagem", "Mensagem envidad com sucesso!");
			return mensagemRemetenteReply;
		} catch (Exception e) {
			if(e.getMessage().equals(ERRO_USUARIO_OFFLINE)) {
				System.out.println("NAO FEITO [MENSAGEM ARQUIVADA]");
				mensagemRemetenteReply = new Mensagem();
				mensagemRemetenteReply.setRequisicao(Requisicao.ENVIAR_MENSAGEM_REPLY);
				mensagemRemetenteReply.setStatus(Status.MENSAGEM_ARQUIVADA);
				mensagemRemetenteReply.setParametros("mensagem", "usuario offline, mensagem arquivada");
				return mensagemRemetenteReply;
			}else {
				e.printStackTrace();
				mensagemRemetenteReply = new Mensagem();
				mensagemRemetenteReply.setRequisicao(Requisicao.ENVIAR_MENSAGEM_REPLY);
				mensagemRemetenteReply.setStatus(Status.MENSAGEM_NAO_ENVIADA);
				mensagemRemetenteReply.setParametros("mensagem", "Erro ao enviar mensagem");
				return mensagemRemetenteReply;
			}

		}
	}
	
	private Mensagem buscarUsuario(Mensagem mensagemInput) {
		DAOBuscarUsuarios buscarUsuarios = new DAOBuscarUsuarios();
		Mensagem mensagemReply = new Mensagem();
		try {
			Object[][] retorno = buscarUsuarios.busacarUsuarios( (String) mensagemInput.getParametro("nome"));
			if (retorno != null) {
				mensagemReply.setRequisicao(Requisicao.BUSCAR_USUARIO_REPLY);
				mensagemReply.setStatus(Status.USUARIOS_ENCONTRADOS);
				mensagemReply.setParametros("usuarios", retorno);
				return mensagemReply;
			}
			else {
				mensagemReply.setRequisicao(Requisicao.BUSCAR_USUARIO_REPLY);
				mensagemReply.setStatus(Status.NENHUM_USUARIO_ENCONTRADO);
				mensagemReply.setParametros("mensagem", "Nenhum usuario retornado do servidor.");
				return mensagemReply;
			}
		} catch (Exception e) {
			e.printStackTrace();
			mensagemReply.setRequisicao(Requisicao.BUSCAR_USUARIO_REPLY);
			mensagemReply.setStatus(Status.STATUS_ERRO_SERVIDOR);
			mensagemReply.setParametros("mensagem", e);
			return mensagemReply;
		}

	}
	
	public Mensagem tratarRequisicao(Mensagem mensagemInput) {
		Requisicao requisicao = mensagemInput.getRequisicao();
		
		switch (requisicao) {
		case ENVIAR_MENSAGEM:
			return (enviarMensagem(mensagemInput));
			
		case BUSCAR_USUARIO:
			return (buscarUsuario(mensagemInput));
		default:
			break;
		}
		return null;
	}

}

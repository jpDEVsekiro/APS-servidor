package aps.unip.protocolo;

import java.io.Serializable;
import java.util.HashMap;

import aps.unip.enums.Requisicao;
import aps.unip.enums.Status;

public class Mensagem implements Serializable {
	private Requisicao requisicao;
	private Status status;
	private HashMap<String, Object> parametros = new HashMap<String, Object>();
	
	
	public Mensagem() {
		
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public void setParametros(String chave, Object valor){
		parametros.put(chave, valor);
	}
	
	public Object getParametro(String chave) {
		return chave;
		
	}

	public Requisicao getRequisicao() {
		return requisicao;
	}

	public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}
}

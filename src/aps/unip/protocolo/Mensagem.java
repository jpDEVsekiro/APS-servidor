package aps.unip.protocolo;

import aps.unip.enums.Status;

public class Mensagem {
	private static Status status = Status.STATUS200;
	private String primeiroNome,
	               segundoNome;
	private int idade;
	
	public static void main(String[] args) {
		System.out.println(status.getValor());
	}
	
}

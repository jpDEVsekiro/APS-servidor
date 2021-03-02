package aps.unip.enums;

public enum Status {
	STATUS200("sucesso");

	private String valor;
	
	Status(String valor) {
		this.valor = valor;
	}
	
	public String getValor() {
		return this.valor;
	}
}

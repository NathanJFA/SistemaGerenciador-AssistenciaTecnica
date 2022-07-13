package model.enumerateds;

public enum Equipamento {
	NOTEBOOK("Notebook"),
	COMPUTADOR("Computador"),
	IMPRESSORA("Impressora");
	
	private String nome;
	
	private Equipamento(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
}

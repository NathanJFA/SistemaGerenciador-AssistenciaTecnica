package model.enumerateds;

public enum TipoServico {
	FORMATACAO("Formatação"),
	LIMPEZA("Limpeza"),
	MANUTENCAO_PREVENTIVA("Manutenção Preventiva"),
	MANUTENCAO_CORRETIVA("Manutenção Corretiva");
	
	private String nome;
	
	private TipoServico(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
}

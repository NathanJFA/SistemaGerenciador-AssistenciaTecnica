package model.services;

import java.util.Collection;

import model.dao.ManutencaoDAO;
import model.entity.Manutencao;
import model.entity.Peca;

public class ManutencaoService {
	
	private static Collection<Manutencao> manutencoes;

	private ManutencaoDAO instance = ManutencaoDAO.getInstance();
	
	private PecaService pecaService = new PecaService();
	
	public ManutencaoService() {
		super();
	}
	public void cadastrar(Manutencao manutencao) {
			instance.persist(manutencao);				
	}
	public void atualizar(Manutencao manutencao) {
		instance.merge(manutencao);
	}
	public void remover(Manutencao manutencao) {
		instance.remove(manutencao);
	}
	public void removerPorId(int id) {
		instance.removeById(id);
	}
	public Collection<Manutencao> recuperarManutencoes(){
		manutencoes = instance.findAll();
		return this.manutencoes;
	}
	public Manutencao procurarPorId(int id) {
		return instance.getById(id);
	}
	public Collection<Manutencao> procurarPorTipoEquipamento(String tipoEquipamento) {
		return instance.findByTipoEquipamento(tipoEquipamento);
	}
	public Collection<Manutencao> procurarPorTipoServico(String tipoServico) {
		return instance.findByTipoServico(tipoServico);
	}
	public Collection<Manutencao> procurarManutencoesPagas(Boolean pagou){
		return instance.findByPagas(pagou);
	}
}

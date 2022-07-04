package model.services;

import java.util.Collection;

import model.dao.PecaDAO;
import model.entity.Peca;

public class PecaService {

	private static Collection<Peca> pecas;
	
	public PecaDAO instance = PecaDAO.getInstance();
	
	public PecaService() {
		super();
	}
	public void cadastrar(Peca peca) {
		instance.persist(peca);
	}
	public void atualizar(Peca peca) {
		instance.merge(peca);
	}
	public void remover(Peca peca) {
		instance.remove(peca);
	}
	public void removerPorId(int id) {
		instance.removeById(id);
	}
	public Collection<Peca> recuperarClientes(){
		pecas = instance.findAll();
		return this.pecas;
	}
	public Peca procurarPorId(int id) {
		return instance.getById(id);
	}
	public Peca procurarPorDescricao(String descricao) {
		return instance.findByPecaByDescricao(descricao);
	}
}

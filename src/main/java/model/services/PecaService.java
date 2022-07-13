package model.services;

import java.util.Collection;

import model.dao.PecaDAO;
import model.entity.Manutencao;
import model.entity.Peca;

public class PecaService {

	private static Collection<Peca> pecas;

	public PecaDAO instance = PecaDAO.getInstance();

	public PecaService() {
		super();
	}

	public boolean check(Peca peca) {

		try {
			if (peca.getDescricao().length() < 1 || peca.getValorPreDefinido() == 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean cadastrar(Peca peca) {
		if (check(peca)) {
			instance.persist(peca);
			return true;
		}
		return false;
	}

	public boolean atualizar(Peca peca) {
		if (check(peca)) {
			instance.merge(peca);
			return true;
		}
		return false;
	}

	public void remover(Peca peca) {
		instance.remove(peca);
	}

	public void removerPorId(int id) {
		instance.removeById(id);
	}

	public Collection<Peca> recuperarPecas() {
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

package model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import model.entity.Peca;

public interface PecaDaoInter {

	public static PecaDAO getInstance() {
		return null;
	}

	public EntityManager getEntityManager();

	public Peca getById(final int id);

	@SuppressWarnings("unchecked")
	public List<Peca> findAll();

	public void persist(Peca peca);

	public void merge(Peca peca);

	public void remove(Peca peca);

	public void removeById(final int id);

	public Peca findByPecaByDescricao(String descricao);

	public Peca findPecaByValor(double valor);
}

package model.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import model.entity.Manutencao;
import model.entity.Peca;

public interface ManutencaoDaoInter {

	public static ManutencaoDAO getInstance() {
		return null;
	}

	public EntityManager getEntityManager();

	public Manutencao getById(final int id);

	@SuppressWarnings("unchecked")
	public List<Manutencao> findAll();

	public void persist(Manutencao manutencao);

	public void merge(Manutencao manutencao);

	public void remove(Manutencao manutencao);

	public void removeById(final int id);

	public Collection<Manutencao> findByTipoEquipamento(String tipoEquipamento);

	public Collection<Manutencao> findByTipoServico(String tipoServico);
	
	public Collection<Manutencao> findByPagas(boolean pagou);
	
	public Collection<Manutencao> findByValor(Double valor);
	
	
}

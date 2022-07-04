package model.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Cliente;
import model.entity.Manutencao;
import model.entity.Peca;

public class ManutencaoDAO implements ManutencaoDaoInter {

	private static ManutencaoDAO instance;
	protected EntityManager entityManager;

	public static ManutencaoDAO getInstance() {
		if (instance == null) {
			instance = new ManutencaoDAO();
		}

		return instance;
	}

	public ManutencaoDAO() {
		entityManager = getEntityManager();
	}

	public EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("SistemaGerenciador");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	public Manutencao getById(final int id) {
		return entityManager.find(Manutencao.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Manutencao> findAll() {
		return entityManager.createQuery("FROM " + Manutencao.class.getName()).getResultList();
	}

	public Collection<Manutencao> findByTipoServico(String tipoServico) {
		try {
			Query query = entityManager.createQuery("SELECT * FROM Manutencao m WHERE m.tipoServico = :parametro");
			query.setParameter("parametro", "%" + tipoServico.toLowerCase() + "%");
			return query.getResultList();

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	public Collection<Manutencao> findByTipoEquipamento(String tipoEquipamento) {
		try {
			Query query = entityManager.createQuery("SELECT * FROM Manutencao m WHERE m.tipoEquipamento = :parametro");
			query.setParameter("parametro", "%" + tipoEquipamento.toUpperCase() + "%");
			return query.getResultList();

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	public void persist(Manutencao manutencao) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(manutencao);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(Manutencao manutencao) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(manutencao);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(Manutencao manutencao) {
		try {
			entityManager.getTransaction().begin();
			manutencao = entityManager.find(Manutencao.class, manutencao.getIdManutencao());
			entityManager.remove(manutencao);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removeById(final int id) {
		try {
			Manutencao manutencao = getById(id);
			remove(manutencao);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public Collection<Manutencao> findByPagas(boolean pagou) {
		try {
			if (pagou == true) {
				Query query = entityManager.createQuery("SELECT * FROM Manutencao m WHERE m.pagou = :parametro");
				query.setParameter("parametro", "%" + true + "%");
				return query.getResultList();
			} else {
				Query query = entityManager.createQuery("SELECT * FROM Manutencao m WHERE m.pagou = :parametro");
				query.setParameter("parametro", "%" + false + "%");
				return query.getResultList();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Collection<Manutencao> findByValor(Double valor) {
		try {
			Query query = entityManager.createQuery("SELECT * FROM Manutencao m WHERE m.pagou = :parametro");
			query.setParameter("parametro", "%" + true + "%");
			return query.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}

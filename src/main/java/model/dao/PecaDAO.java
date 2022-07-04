package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Cliente;
import model.entity.Peca;

public class PecaDAO implements PecaDaoInter {

	private static PecaDAO instance;
	protected EntityManager entityManager;

	public static PecaDAO getInstance() {
		if (instance == null) {
			instance = new PecaDAO();
		}

		return instance;
	}

	private PecaDAO() {
		entityManager = getEntityManager();
	}

	@Override
	public EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("SistemaGerenciador");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	@Override
	public Peca getById(final int id) {
		return entityManager.find(Peca.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Peca> findAll() {
		return entityManager.createQuery("FROM " + Peca.class.getName()).getResultList();
	}

	@Override
	public void persist(Peca peca) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(peca);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	@Override
	public void merge(Peca peca) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(peca);
			entityManager.getTransaction().commit();

		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}

	}

	@Override
	public void remove(Peca peca) {
		try {
			entityManager.getTransaction().begin();
			peca = entityManager.find(Peca.class, peca.getIdPeca());
			entityManager.remove(peca);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	@Override
	public void removeById(final int id) {
		try {
			Peca peca = getById(id);
			remove(peca);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public Peca findByPecaByDescricao(String descricao) {
		try {
			Query query = entityManager.createQuery("SELECT * FROM Peca p WHERE p.descricao = :parametro");
			query.setParameter("parametro", "%" + descricao + "%");
			return (Peca) query.getSingleResult();

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	@Override
	public Peca findPecaByValor(double valor) {
		try {
			Query query = entityManager.createQuery("SELECT * FROM Peca p WHERE p.valorPreDefinido = :parametro");
			query.setParameter("parametro", "%" + valor + "%");
			Peca peca = (Peca) query.getSingleResult();
			if (peca == null) {
				throw new Exception("Não encontrado");
			} else {
				return peca;
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

}

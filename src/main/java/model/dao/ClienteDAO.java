package model.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Cliente;
import model.entity.Endereco;
import model.entity.Manutencao;

public class ClienteDAO implements ClienteDaoInter {

	private static ClienteDAO instance;
	protected EntityManager entityManager;

	public static ClienteDAO getInstance() {
		if (instance == null) {
			instance = new ClienteDAO();
		}

		return instance;
	}

	public ClienteDAO() {
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
	public Cliente getById(final int id) {
		return entityManager.find(Cliente.class, id);
	}

	
	public List<Cliente> findAll() {
		return entityManager.createQuery("FROM " + Cliente.class.getName()).getResultList();
	}

	@Override
	public Cliente findByNomeCliente(String nomeCliente) {
		try {
			Query query = entityManager.createQuery("SELECT * FROM Cliente c WHERE c.nome = :parametro");
			query.setParameter("parametro", "%" + nomeCliente.toLowerCase() + "%");
			return (Cliente) query.getSingleResult();
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}


	@Override
	public Cliente getByCPF(String cpf) {
		try {
			Query query = entityManager.createQuery("SELECT * FROM Cliente c WHERE c.cpf = :parametro");
			query.setParameter("parametro", "%" + cpf + "%");
			return (Cliente) query.getSingleResult();

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	@Override
	public void persist(Cliente cliente) {
		try {
			cliente.setNome(cliente.getNome().toLowerCase());
			entityManager.getTransaction().begin();
			entityManager.persist(cliente);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	@Override
	public void merge(Cliente cliente) {
		try {
			//cliente.setNome(cliente.getNome().toLowerCase());
			entityManager.getTransaction().begin();
			entityManager.merge(cliente);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	@Override
	public void remove(Cliente cliente) {
		try {
			entityManager.getTransaction().begin();
			cliente = entityManager.find(Cliente.class, cliente.getIdCliente());
			entityManager.remove(cliente);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	@Override
	public void removeById(final int id) {
		try {
			Cliente cliente = getById(id);
			remove(cliente);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public Collection<Manutencao> findManutencoesByCpfCliente(String cpf) {
		try {
			Cliente cliente = this.getByCPF(cpf);
			return cliente.getManutencoesCliente();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}

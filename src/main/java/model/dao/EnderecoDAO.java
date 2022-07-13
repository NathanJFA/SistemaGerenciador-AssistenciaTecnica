package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.entity.Endereco;

public class EnderecoDAO implements EnderecoDaoInter {

	private static EnderecoDAO instance;
	protected EntityManager entityManager;
	
	
	public EnderecoDAO() {
		entityManager = getEntityManager();
	}
	
	
	public static EnderecoDAO getInstance() {
		if (instance == null) {
			instance = new EnderecoDAO();
		}

		return instance;
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
	public Endereco getById(int id) {
		try {
			return entityManager.find(Endereco.class, id);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Endereco> findAll() {
		return entityManager.createQuery("FROM " + Endereco.class.getName()).getResultList();
	}

	@Override
	public void persist(Endereco endereco) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(endereco);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
		
	}

	@Override
	public void merge(Endereco endereco) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(endereco);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	@Override
	public void remove(Endereco endereco) {
		try {
			entityManager.getTransaction().begin();
			endereco = entityManager.find(Endereco.class, endereco.getCliente().getIdCliente());
			entityManager.remove(endereco);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	@Override
	public void removeById(int id) {
		try {
			entityManager.getTransaction().begin();
			Endereco endereco = entityManager.find(Endereco.class, id);
			if(endereco.getCliente().getIdCliente() != 0 && endereco != null) {
				entityManager.remove(endereco);
			}
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	@Override
	public void updateEnderecoByCpf(Endereco endereco, String cpf) {
		//TODO
		
	}

	@Override
	public void updateEnderecoById(Endereco endereco, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Endereco getByCPF(String cpf) {
		// TODO Auto-generated method stub
		return null;
	}

}

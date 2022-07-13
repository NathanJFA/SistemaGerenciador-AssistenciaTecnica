package model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import model.entity.Endereco;

public interface EnderecoDaoInter {
	
	
	public static EnderecoDAO getInstance() {
		return null;
	}
	
	public EntityManager getEntityManager();


	public Endereco getById(final int id);

	@SuppressWarnings("unchecked")
	public List<Endereco> findAll();
	

	public void persist(Endereco endereco);


	public void merge(Endereco endereco);


	public void remove(Endereco endereco);


	public void removeById(final int id);

	public void updateEnderecoByCpf(Endereco endereco, String cpf);
	
	public void updateEnderecoById(Endereco endereco, int id);
	
	public Endereco getByCPF(String cpf);
	
}

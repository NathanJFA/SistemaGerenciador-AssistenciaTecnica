package model.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.entity.Cliente;
import model.entity.Endereco;
import model.entity.Manutencao;

public interface ClienteDaoInter {
	
	public static ClienteDAO getInstance() {
		return null;
	}
	
	public EntityManager getEntityManager();


	public Cliente getById(final int id);

	@SuppressWarnings("unchecked")
	public List<Cliente> findAll();
	
	public Cliente findByNomeCliente(String nomeCliente);

	public void persist(Cliente cliente);


	public void merge(Cliente cliente);


	public void remove(Cliente cliente);


	public void removeById(final int id);

	public void updateEnderecoByCpf(Endereco endereco, String cpf);
	
	public void updateEnderecoById(Endereco endereco, int id);
	
	public Cliente getByCPF(String cpf);
	
	public Collection<Manutencao> findManutencoesByCpfCliente(String cpf);
	
	public Endereco findEnderecoByCpfCLiente(String cpf);
}

package model.services;

import java.util.Collection;

import model.dao.ClienteDAO;
import model.dao.ManutencaoDAO;
import model.entity.Cliente;
import model.entity.Endereco;
import model.entity.Manutencao;
import model.entity.Peca;

public class ClienteService {

	private static Collection<Cliente> clientes;

	private ClienteDAO instance = ClienteDAO.getInstance();

	private ManutencaoService manutencaoService = new ManutencaoService();

	public ClienteService() {
		super();
	}

	public void cadastrar(Cliente cliente) {
		instance.persist(cliente);
	}

	public void atualizar(Cliente cliente) {
		instance.merge(cliente);
	}

	public void remover(Cliente cliente) {
		instance.remove(cliente);
	}

	public void removerPorId(int id) {
		instance.removeById(id);
	}

	public Collection<Cliente> recuperarClientes() {
		clientes = instance.findAll();
		return this.clientes;
	}

	public Cliente procurarPorId(int id) {
		return instance.getById(id);
	}

	public Cliente procurarPorNome(String nome) {
		return instance.findByNomeCliente(nome);
	}

	public void alterarEnderecoPorId(Endereco endereco, int id) {
		instance.updateEnderecoById(endereco, id);
	}

	public void alterarEnderecoPorCpf(Endereco endereco, String cpf) {
		instance.updateEnderecoByCpf(endereco, cpf);
	}

	public Collection<Manutencao> recuperarManutencoesDoClientePorCPF(String cpf) {
		return instance.findManutencoesByCpfCliente(cpf);
	}

	public Endereco recuperarEnderecoDoClientePorCPF(String cpf) {
		return instance.findEnderecoByCpfCLiente(cpf);
	}
}

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
	
	private EnderecoService enderecoService = new EnderecoService();

	private ClienteDAO instance = ClienteDAO.getInstance();

	private ManutencaoService manutencaoService = new ManutencaoService();

	public ClienteService() {
		super();
	}

	public boolean check(Cliente cliente) {
		try {
			System.out.println(cliente.toString());
			if(cliente.getNome().length() < 1 || cliente.getCpf().isEmpty() || cliente.getEmail().isEmpty() || cliente.getSexo().getNome().isEmpty()) {
				return false;
			}else {
				return true;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
		
	}
	public boolean cadastrar(Cliente cliente) {
		if(check(cliente)) {
			instance.persist(cliente);
			return true;
		}
		return false;
	}

	public boolean atualizar(Cliente cliente) {
		if(check(cliente)) {
			instance.merge(cliente);
			return true;
		}
		return false;
	}

	public void remover(Cliente cliente) {
		if(!(enderecoService.procurarPorId(cliente.getIdCliente()) == null)) {
			enderecoService.removerPorId(cliente.getIdCliente());
		}
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


	public Collection<Manutencao> recuperarManutencoesDoClientePorCPF(String cpf) {
		return instance.findManutencoesByCpfCliente(cpf);
	}
}

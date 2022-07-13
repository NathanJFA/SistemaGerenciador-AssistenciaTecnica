package model.services;

import java.util.ArrayList;
import java.util.Collection;

import model.dao.EnderecoDAO;
import model.entity.Cliente;
import model.entity.Endereco;

public class EnderecoService {

	private EnderecoDAO instance = EnderecoDAO.getInstance();

	private Collection<Endereco> enderecos = new ArrayList<>();
	
	public static Endereco enderecoAEditar;

	public EnderecoService() {
		super();
	}

	public boolean checkRua(Endereco endereco) {

		try {
			if (endereco.getRua().isEmpty() || endereco.getRua().isEmpty() || endereco.getRua() == null) {
				return false;
			} else {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	public boolean checkBairro(Endereco endereco) {

		try {
			if (endereco.getBairro().isEmpty() || endereco.getBairro().isEmpty() || endereco.getBairro() == null) {
				return false;
			} else {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	public static Endereco instanciarEnderecoAEditar(Endereco endereco) {
		enderecoAEditar = new Endereco();
		enderecoAEditar.setBairro(endereco.getBairro());
		enderecoAEditar.setCidade(endereco.getCidade());
		enderecoAEditar.setCliente(endereco.getCliente());
		enderecoAEditar.setComplemento(endereco.getComplemento());
		enderecoAEditar.setNumero(endereco.getNumero());
		enderecoAEditar.setRua(endereco.getRua());
		System.out.println(enderecoAEditar.toString());
		return enderecoAEditar;
	}
	
	public boolean checkNumero(Endereco endereco) {

		try {
			if (endereco.getNumero().isEmpty() || endereco.getNumero().isEmpty() || endereco.getNumero() == null) {
				return false;
			} else {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	public boolean checkComplemento(Endereco endereco) {

		try {
			if (endereco.getRua().isEmpty() || endereco.getBairro().isEmpty() || endereco.getComplemento().isEmpty()
					|| endereco.getCidade().isEmpty()) {
				return false;
			} else {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	public boolean check(Endereco endereco) {
		if(!checkRua(endereco) || !checkNumero(endereco) || !checkBairro(endereco) || !checkComplemento(endereco)) {
			return false;
		}else {
			return true;
		}
	}

	public boolean cadastrar(Endereco endereco) {
		if (check(endereco)) {
			instance.persist(endereco);
			return true;
		}
		return false;
	}

	public boolean atualizar(Endereco endereco) {
		if (check(endereco) && (endereco.getCliente().getIdCliente() != 0)) {
			instance.merge(endereco);
			return true;
		}
		return false;
	}

	public void remover(Endereco endereco) {
		instance.remove(endereco);
	}

	public void removerPorId(int id) {
		instance.removeById(id);
	}

	public Collection<Endereco> recuperarEnderecos() {
		enderecos = instance.findAll();
		return this.enderecos;
	}

	public Endereco procurarPorId(int id) {
		return instance.getById(id);
	}

	public Endereco getEnderecoAEditar() {
		return enderecoAEditar;
	}

	public void setEnderecoAEditar(Endereco enderecoAEditar) {
		enderecoAEditar = enderecoAEditar;
	}

}

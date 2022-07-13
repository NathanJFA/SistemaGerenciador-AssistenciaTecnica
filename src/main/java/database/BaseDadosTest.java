package database;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;

import model.entity.Cliente;
import model.entity.Endereco;
import model.entity.Manutencao;
import model.entity.Peca;
import model.entity.Sexo;
import model.enumerateds.Equipamento;
import model.enumerateds.TipoServico;

public class BaseDadosTest {
	
	ArrayList<Cliente> clientes = new ArrayList<>();
	Collection<Manutencao> manutencoes = new ArrayList<>();
	Collection<Peca> pecas = new ArrayList<>();
	EntityManager em;
	
	public BaseDadosTest(EntityManager em) {
		this.em = em;
	}

	public void createClientesEEnderecos() {
		
		for(int i = 0; i < 10; i++) {
			Cliente cliente = new Cliente();
			cliente.setNome("NathanTeste" + i);
			cliente.setCpf("086.885.449-3" + i);
			cliente.setEmail("nathan@teste.com" + i);
			cliente.setSexo(Sexo.M);
			cliente.setTelefone("87834728347" + i);
			Endereco endereco = new Endereco();
			endereco.setRua("rua violeta" + i);
			endereco.setBairro("planalto 2" + i);
			endereco.setComplemento("proximo a quadra" + i);
			endereco.setCidade("mataraca");
			endereco.setNumero("4");
			
			clientes.add(cliente);
			
			try {
				em.persist(cliente);
				endereco.setCliente(cliente);
				em.persist(endereco);
			}catch(Exception e) {
				e. printStackTrace();
				System.out.println("Deu ruim na criação de cliente");
			}
			
		}
		
		

	}
	public void createManutencoes() {

		for(int i=0; i<10; i++) {
			Manutencao manutencao = new Manutencao();
			manutencao.setDescricao("manutencao de numero: "+i);
			manutencao.setEntregue(true);
			manutencao.setPagou(false);
			manutencao.setTipoEquipamento(Equipamento.NOTEBOOK);
			manutencao.setTipoServico(TipoServico.MANUTENCAO_CORRETIVA);
			
			
			Collection<Peca> pecas = new ArrayList<>();
			for(int j=40; j<45; j++) {
				Peca peca = new Peca();
				peca.setDescricao("descricao "+j);
				peca.setValorPreDefinido(20);
				pecas.add(peca);
				em.persist(peca);
			}
			manutencao.setPecasUtilizadas(pecas);
			
			for(int j=0; j < clientes.size(); j++) {
				manutencao.setCliente(this.clientes.get(i));
				this.clientes.get(i).addManutencao(manutencao);
				manutencoes.add(manutencao);
			}

			
		}
		
		for(Manutencao manutencao: manutencoes) {
			try {				
				em.persist(manutencao);
				
			}catch(Exception e) {
				e. printStackTrace();
				System.out.println("Deu ruim na criação da manutencao");
			}
		}
	}
	public void createPecas() {
		for(int i=0; i<10; i++) {
			Peca peca = new Peca();
			peca.setDescricao("descricao "+i);
			peca.setValorPreDefinido(20);
			pecas.add(peca);
			
			try {
				
				em.persist(peca);
				
			}catch(Exception e) {
				e. printStackTrace();
				System.out.println("Deu ruim na criação da peça");
			}
		}
	}
	
	public void exibeArrays() {

		for(Peca e: pecas) {
			System.out.println("---------------------------------------------------Peça");
			System.out.println(e.toString());
		}
		for(Manutencao e: manutencoes) {
			System.out.println("--------------------------------------------------Manutencao");
			System.out.println(e.toString());
		}
		for(Cliente e: clientes) {
			System.out.println("---------------------------------------------------Cliente");
			System.out.println(e.toString());
		}
	}
}

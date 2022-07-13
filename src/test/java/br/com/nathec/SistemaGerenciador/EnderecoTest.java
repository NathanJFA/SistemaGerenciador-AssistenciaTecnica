package br.com.nathec.SistemaGerenciador;

import static org.junit.Assert.*;

import org.junit.Test;

import model.entity.Cliente;
import model.entity.Endereco;
import model.entity.Sexo;
import model.services.ClienteService;
import model.services.EnderecoService;
import model.services.ManutencaoService;
import model.services.PecaService;

public class EnderecoTest {
	
	ClienteService clienteService = new ClienteService();
	ManutencaoService manutencaoService = new ManutencaoService();
	PecaService pecaService = new PecaService();
	EnderecoService enderecoService = new EnderecoService();

	@Test
	public void cadastroTest() {
		Cliente cliente = new Cliente();
		cliente.setNome("Teste de insercao");
		cliente.setEmail("nathan@teste.com.br");
		cliente.setSexo(Sexo.M);
		cliente.setTelefone("8711209822");
		
		clienteService.cadastrar(cliente);
		
		Endereco endereco = new Endereco();
		endereco.setBairro("planalto 4");
		endereco.setCidade("mamanguape");
		endereco.setComplemento("proximo a quadra");
		endereco.setNumero("10");
		
		endereco.setCliente(cliente);
		enderecoService.cadastrar(endereco);
		
		assertEquals(cliente.getIdCliente(), enderecoService.procurarPorId(cliente.getIdCliente()).getCliente().getIdCliente());
	}
	@Test
	public void removerEnderecoTest() {
		Cliente cliente = new Cliente();
		cliente.setNome("Teste de insercao");
		cliente.setEmail("nathan@teste.com.br");
		cliente.setSexo(Sexo.M);
		cliente.setTelefone("8711209822");
		
		clienteService.cadastrar(cliente);
		
		Endereco endereco = new Endereco();
		endereco.setBairro("planalto 4");
		endereco.setCidade("mamanguape");
		endereco.setComplemento("proximo a quadra");
		endereco.setNumero("10");
		
		endereco.setCliente(cliente);
		enderecoService.cadastrar(endereco);
		
		enderecoService.remover(endereco);
		
		assertNull(enderecoService.procurarPorId(endereco.getCliente().getIdCliente()));
	}
	
	@Test
	public void removerClienteEEnderecoTest() {
		Cliente cliente = new Cliente();
		cliente.setNome("Teste de insercao");
		cliente.setEmail("nathan@teste.com.br");
		cliente.setSexo(Sexo.M);
		cliente.setTelefone("8711209822");
		
		clienteService.cadastrar(cliente);
		
		Endereco endereco = new Endereco();
		endereco.setBairro("planalto 4");
		endereco.setCidade("mamanguape");
		endereco.setComplemento("proximo a quadra");
		endereco.setNumero("10");
		
		endereco.setCliente(cliente);
		enderecoService.cadastrar(endereco);
		
		enderecoService.remover(endereco);
		clienteService.remover(cliente);
		
		assertNull(enderecoService.procurarPorId(endereco.getCliente().getIdCliente()));
	}

}

package br.com.nathec.SistemaGerenciador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import model.entity.Cliente;
import model.entity.Endereco;
import model.entity.Equipamento;
import model.entity.Manutencao;
import model.entity.Peca;
import model.entity.Sexo;
import model.entity.TipoServico;
import model.services.ClienteService;
import model.services.ManutencaoService;
import model.services.PecaService;

public class ClienteTest {
	
	ClienteService instanciaService = new ClienteService();
	ManutencaoService instanciaServiceManutencao = new ManutencaoService();
	PecaService instanciaPecaService = new PecaService();
	
	@Test
	public void testaCadastroCliente() {
		Cliente cliente = new Cliente();
		cliente.setNome("Teste de insercao");
		cliente.setEmail("nathan@teste.com.br");
		cliente.setSexo(Sexo.M);
		cliente.setTelefone("8711209822");
		instanciaService.cadastrar(cliente);
		assertEquals(cliente.getIdCliente(), instanciaService.procurarPorId(cliente.getIdCliente()).getIdCliente());
		assertNotNull(instanciaService.procurarPorId(cliente.getIdCliente()));
	}
	@Test
	public void testaCadastroClienteFull() {
		
		Cliente cliente = new Cliente();
		cliente.setNome("Teste de insercao full");
		cliente.setEmail("nathan@teste.com.br");
		cliente.setSexo(Sexo.M);
		cliente.setTelefone("8711209822");
		
		Endereco endereco = new Endereco();
		endereco.setBairro("planalto 2");
		endereco.setCidade("Mataraca");
		endereco.setComplemento("proximo a quadra");
		endereco.setNumero("5");
		cliente.setEndereco(endereco);
		
		instanciaService.cadastrar(cliente);
		
		//Cliente cliente = instanciaService.procurarPorId(1);
		
		Manutencao manutencao = new Manutencao();
		manutencao.setDescricao("Limpeza bruta");
		manutencao.setEntregue(false);
		manutencao.setPagou(false);
		manutencao.setTipoEquipamento(Equipamento.NOTEBOOK);
		manutencao.setTipoServico(TipoServico.LIMPEZA);
		manutencao.setValor(40.00);
		
		Collection<Peca> pecas = new ArrayList<>();
		Peca peca = new Peca();
		peca.setDescricao("Limpa contato");
		peca.setValorPreDefinido(15.00);
		instanciaPecaService.cadastrar(peca);
		Peca peca1 = new Peca();
		peca1.setDescricao("Pincel");
		peca1.setValorPreDefinido(20.00);
		instanciaPecaService.cadastrar(peca1);
		Peca peca2 = new Peca();
		peca2.setDescricao("Pincel teste");
		peca2.setValorPreDefinido(25.00);
		instanciaPecaService.cadastrar(peca2);
		pecas.add(peca);
		pecas.add(peca1);
		pecas.add(peca2);
		
		manutencao.setPecasUtilizadas(pecas);
		
		manutencao.setCliente(cliente);
		
		instanciaServiceManutencao.cadastrar(manutencao);
		
		Collection<Manutencao> manutencoes = new ArrayList<>();
		manutencoes.add(manutencao);
		cliente.setManutencoesCliente((ArrayList<Manutencao>) manutencoes);
		
		assertEquals(cliente.getIdCliente(), instanciaService.procurarPorId(cliente.getIdCliente()).getIdCliente());
		assertNotNull(instanciaService.procurarPorId(cliente.getIdCliente()));
	}

	@Test
	public void testaRemocao() {
		Cliente cliente = new Cliente();
		cliente.setNome("Teste de insercao e remocao");
		cliente.setEmail("sistema@teste.com.br");
		cliente.setSexo(Sexo.F);
		cliente.setTelefone("8734343323");
		instanciaService.cadastrar(cliente);

		assertNotNull(instanciaService.procurarPorId(cliente.getIdCliente()));
		
		instanciaService.remover(cliente);

		assertNull(instanciaService.procurarPorId(cliente.getIdCliente()));

	}

	@Test
	public void testaAtualizacao() {
		Cliente cliente = new Cliente();
		cliente.setNome("Teste de Atualizacao");
		cliente.setEmail("sistema@teste.com.br");
		cliente.setSexo(Sexo.F);
		cliente.setTelefone("8734343323");

		instanciaService.cadastrar(cliente);

		cliente.setEmail("emailatualizado@teste.com.br");
		
		instanciaService.atualizar(cliente);
		
		assertEquals(instanciaService.procurarPorId(cliente.getIdCliente()).getEmail(), cliente.getEmail());

	}
	
	@Test
	public void testaRecuperarLista() {
		Cliente cliente = new Cliente();
		cliente.setNome("Teste de RecuperacaoLista");
		cliente.setEmail("sistema@teste.com.br");
		cliente.setSexo(Sexo.M);
		cliente.setTelefone("8734343323");

		instanciaService.cadastrar(cliente);

		Collection<Cliente> clientes = instanciaService.recuperarClientes();
		assertTrue(clientes.size() > 0);
		
	}
	
}

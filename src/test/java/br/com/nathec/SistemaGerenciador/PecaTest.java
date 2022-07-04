package br.com.nathec.SistemaGerenciador;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import model.entity.Peca;
import model.services.PecaService;

public class PecaTest {
	
	PecaService service = new PecaService();
	
	@Test 
	public void testaRetornoID() {
		Peca peca1 = service.procurarPorId(2);
		System.out.println(peca1.toString());
		
		Peca peca2 = new Peca();
		peca2.setDescricao("Memoria");
		peca2.setValorPreDefinido(145.00);
		
		service.cadastrar(peca2);
		
		if(peca2.getIdPeca() == 0) {
			fail("não atualizou o id");
		}
	}

	@Test
	public void testaCadastro() {
		Peca peca = new Peca();
		peca.setDescricao("Memoria ram ddr4 4gb");
		peca.setValorPreDefinido(144.00);
		
		service.cadastrar(peca);
	
		assertEquals(peca.getIdPeca(), service.procurarPorId(peca.getIdPeca()).getIdPeca());
	}
	
	@Test
	public void testaRemocao() {
		Peca peca = new Peca();
		peca.setDescricao("Memoria ram ddr3 4gb");
		peca.setValorPreDefinido(135.00);
		service.cadastrar(peca);
		
		assertEquals(peca.getIdPeca(), service.procurarPorId(peca.getIdPeca()).getIdPeca());
		
		service.remover(peca);
		
		assertNull(service.procurarPorId(peca.getIdPeca()));
		
	}
	@Test
	public void testaAtualizacao() {
		Peca peca = new Peca();
		peca.setDescricao("Fonte HyperX");
		peca.setValorPreDefinido(343.00);
		
		service.cadastrar(peca);
		
		peca.setDescricao("Fonte HyperX 500w");
		
		service.atualizar(peca);
		
		assertNotEquals(service.procurarPorId(peca.getIdPeca()).getDescricao(), "Fonte HyperX");
	}
	@Test
	public void testaRecuperarLista() {
		Peca peca1 = new Peca();
		peca1.setDescricao("Fonte Gigabyte");
		peca1.setValorPreDefinido(343.00);
		
		service.cadastrar(peca1);		
		
		Collection<Peca> pecas = service.recuperarClientes();
		assertTrue(pecas.size() > 0);
	}
	
	

}

package br.com.nathec.SistemaGerenciador;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import model.entity.Cliente;
import model.entity.Equipamento;
import model.entity.Manutencao;
import model.entity.Peca;
import model.services.ClienteService;
import model.services.ManutencaoService;

public class ManutencaoTest {

	ManutencaoService instanciaService = new ManutencaoService();
	ClienteService instanciaServiceCliente = new ClienteService();
	Cliente cliente = instanciaServiceCliente.procurarPorId(1);

	@Test
	public void testaCadastro() {
		
		
		Manutencao manutencao = new Manutencao();
		
		manutencao.setCliente(cliente);
		manutencao.setDescricao("Limpeza de cabeçoteeeeeeeeee");
		manutencao.setEntregue(true);
		manutencao.setPagou(true);
		manutencao.setTipoEquipamento(Equipamento.IMPRESSORA);
		manutencao.setValor(37.00);
		
		instanciaService.cadastrar(manutencao);

		assertEquals(manutencao.getIdManutencao(), instanciaService.procurarPorId(manutencao.getIdManutencao()).getIdManutencao());
	}

	@Test
	public void testaRemocao() {
		Manutencao manutencao = new Manutencao();
		manutencao.setDescricao("Limpeza de cabeçote");
		manutencao.setEntregue(true);
		manutencao.setPagou(true);
		manutencao.setTipoEquipamento(Equipamento.IMPRESSORA);
		manutencao.setValor(35.00);

		manutencao.setCliente(cliente);

		instanciaService.cadastrar(manutencao);
		
		assertNotNull(instanciaService.procurarPorId(manutencao.getIdManutencao()));

		instanciaService.remover(manutencao);
		
		assertNull(instanciaService.procurarPorId(manutencao.getIdManutencao()));
		
	}

	@Test
	public void testaAtualizacao() {
		Manutencao manutencao = new Manutencao();
		manutencao.setDescricao("Formatacao e troca de memoria ram");
		manutencao.setEntregue(true);
		manutencao.setPagou(true);
		manutencao.setTipoEquipamento(Equipamento.COMPUTADOR);
		manutencao.setValor(120.00);

		manutencao.setCliente(cliente);

		instanciaService.cadastrar(manutencao);

		manutencao.setDescricao("Formatacao e troca de memoria ram, e fonte teste3");
		
		instanciaService.atualizar(manutencao);
		
		assertEquals(manutencao.getIdManutencao(), instanciaService.procurarPorId(manutencao.getIdManutencao()).getIdManutencao());

		assertNotEquals(instanciaService.procurarPorId(manutencao.getIdManutencao()).getDescricao(), "Formatacao e troca de memoria ram");
	}

	@Test
	public void testaRecuperarLista() {
		Manutencao manutencao = new Manutencao();
		manutencao.setDescricao("Formatacao com limpeza");
		manutencao.setEntregue(true);
		manutencao.setPagou(true);
		manutencao.setTipoEquipamento(Equipamento.NOTEBOOK);
		manutencao.setValor(45.00);

		manutencao.setCliente(cliente);

		instanciaService.cadastrar(manutencao);

		Collection<Manutencao> manutencoes = instanciaService.recuperarManutencoes();
		assertTrue(manutencoes.size() > 0);
	}
}

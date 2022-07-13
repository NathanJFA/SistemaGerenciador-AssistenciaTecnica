package model.services;

import java.util.Collection;

import model.dao.ManutencaoDAO;
import model.entity.Cliente;
import model.entity.Endereco;
import model.entity.Manutencao;
import model.entity.Peca;

public class ManutencaoService {

	private static Collection<Manutencao> manutencoes;

	private ManutencaoDAO instance = ManutencaoDAO.getInstance();

	private PecaService pecaService = new PecaService();
	
	public static Manutencao campoManutencao;

	public ManutencaoService() {
		super();
	}

	public boolean check(Manutencao manutencao) {

		try {
			if (manutencao.getDescricao().length() < 1 || manutencao.getCliente() == null
					|| manutencao.getData() == null || manutencao.getValor() == 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean cadastrar(Manutencao manutencao) {
		if (check(manutencao)) {
			instance.persist(manutencao);
			return true;
		}
		return false;
	}

	public boolean atualizar(Manutencao manutencao) {
		if (check(manutencao)) {
			instance.merge(manutencao);
			return true;
		}
		return false;
	}

	public void remover(Manutencao manutencao) {
		instance.remove(manutencao);
	}

	public void removerPorId(int id) {
		instance.removeById(id);
	}

	public Collection<Manutencao> recuperarManutencoes() {
		manutencoes = instance.findAll();
		return this.manutencoes;
	}

	public Manutencao procurarPorId(int id) {
		return instance.getById(id);
	}

	public Collection<Manutencao> procurarPorTipoEquipamento(String tipoEquipamento) {
		return instance.findByTipoEquipamento(tipoEquipamento);
	}
	public Manutencao instanciarManutencaoACadastrar(Manutencao manutencao) {
		campoManutencao = new Manutencao();
		campoManutencao.setDescricao(manutencao.getDescricao());
		campoManutencao.setValor(manutencao.getValor());
		campoManutencao.setCliente(manutencao.getCliente());
		campoManutencao.setPagou(manutencao.getPagou());
		campoManutencao.setTipoServico(manutencao.getTipoServico());
		campoManutencao.setTipoEquipamento(manutencao.getTipoEquipamento());
		campoManutencao.setData(manutencao.getData());
		campoManutencao.setPecasUtilizadas(manutencao.getPecasUtilizadas());
		System.out.println("INSTANCIANDO CADASTRO: " +manutencao.toString());
		System.out.println("INSTANCIANDO - INFO CLIENTE: " +manutencao.getCliente().toString());
		return campoManutencao;
	}
	public Manutencao instanciarManutencaoAEditar(Manutencao manutencao) {
		campoManutencao = manutencao;
		System.out.println("LOCAL DO PRINT: metodo instanciarCampoAEditar() em ManutencaoService");
		System.out.println("objeto da classe ManutencaoService: " + campoManutencao.toString());
		System.out.println("objeto cliente da classe ManutencaoBean: " + campoManutencao.getCliente().toString());
		System.out.println("------------------------------------------------------------------------------------------------------------");
		return campoManutencao;
	}
	public Manutencao instanciarCampoManutencao(Cliente clienteSelecionado) {
		campoManutencao = new Manutencao();
		campoManutencao.setDescricao("");
		campoManutencao.setValor(0);
		campoManutencao.setCliente(clienteSelecionado);
		campoManutencao.setPagou(false);
		//campoManutencao.setTipoServico();
		//campoManutencao.setTipoEquipamento();
		//campoManutencao.setData();
		//campoManutencao.setPecasUtilizadas();
		System.out.println("LOCAL DO PRINT: metodo instanciarCampoManutencao() em ManutencaoService");
		System.out.println("objeto da classe ManutencaoService: " + campoManutencao.toString());
		System.out.println("objeto cliente da classe ManutencaoBean: " + campoManutencao.getCliente().toString());
		System.out.println("------------------------------------------------------------------------------------------------------------");
		return campoManutencao;
	}

	public Collection<Manutencao> recuperarManutencoesPorIdCliente(int id) {
		return instance.findByIdCliente(id);
	}

	public Collection<Manutencao> procurarPorTipoServico(String tipoServico) {
		return instance.findByTipoServico(tipoServico);
	}

	public Collection<Manutencao> procurarManutencoesPagas(Boolean pagou) {
		return instance.findByPagas(pagou);
	}

	public Collection<Manutencao> getManutencoes() {
		return manutencoes;
	}

	public void setManutencoes(Collection<Manutencao> manutencoes) {
		ManutencaoService.manutencoes = manutencoes;
	}

	public Manutencao getManutencao() {
		return campoManutencao;
	}

	public void setManutencao(Manutencao campoManutencao) {
		ManutencaoService.campoManutencao = campoManutencao;
	}
}

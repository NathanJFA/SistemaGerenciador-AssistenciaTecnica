package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.entity.Cliente;
import model.entity.Endereco;
import model.entity.Manutencao;
import model.entity.Peca;
import model.enumerateds.Equipamento;
import model.enumerateds.TipoServico;
import model.services.ClienteService;
import model.services.EnderecoService;
import model.services.ManutencaoService;
import model.services.PecaService;

@ManagedBean
@SessionScoped
public class ManutencaoBean implements Serializable {

	public static Collection<Peca> pecas;
	public static Collection<Manutencao> manutencoesDoCliente;
	public static Collection<Manutencao> manutencoes;

	public static ClienteService clienteService = new ClienteService();
	public static ManutencaoService manutencaoService = new ManutencaoService();
	public static PecaService pecaService = new PecaService();
	public static EnderecoService enderecoService = new EnderecoService();

	public static Manutencao campoManutencao;
	public static Peca peca = new Peca();
	public boolean mostraListaPecas = true;

	public ManutencaoBean() {

	}

	public void inicializaComponentes() {
		setMostraListaPecas(false);
		campoManutencao = manutencaoService.getManutencao();
		manutencoes = manutencaoService.recuperarManutencoes();
		manutencoesDoCliente = getManutencoesDoCliente();
		pecas = pecaService.recuperarPecas();
		System.out.println("LOCAL DO PRINT: metodo instanciarComponentes() em ManutencaoBean");
		System.out.println("objeto da classe ManutencaoBean: " + campoManutencao.toString());
		System.out.println("objeto cliente da classe ManutencaoBean: " + campoManutencao.getCliente().toString());
		System.out.println("objeto clienteSelecionado: " + ClienteBean.clienteSelecionado.toString());
		System.out.println("------------------------------------------------------------------------------------------------------------");
		
	}

	public void inicializaListagem() {
		manutencoes = manutencaoService.recuperarManutencoes();
	}
	public void inicializaListagemPecas() {
		pecas = pecaService.recuperarPecas();
		System.out.println("TESTANDOOOOOOOOOOOOOOOOOOOOOOOOO");
		System.out.println(pecas);
	}
	public void buscaObjetoEdicao() {
		System.out.println("LOCAL DO PRINT: metodo buscaObjetoEdicao() antes de invocar método (instancia) em ManutencaoBean");
		System.out.println("objeto da classe ManutencaoBean: " + campoManutencao.toString());
		System.out.println("objeto cliente da classe ManutencaoBean: " + campoManutencao.getCliente().toString());
		System.out.println("------------------------------------------------------------------------------------------------------------");
		campoManutencao = manutencaoService.instanciarManutencaoAEditar(campoManutencao);
		System.out.println("LOCAL DO PRINT: metodo buscaObjetoEdicao() depois de invocar metodo (instancia) em ManutencaoBean");
		System.out.println("objeto da classe ManutencaoBean: " + campoManutencao.toString());
		System.out.println("objeto cliente da classe ManutencaoBean: " + campoManutencao.getCliente().toString());
		System.out.println("------------------------------------------------------------------------------------------------------------");
	}
	public void removerPeca() {
		Collection<Peca> pecasDaManutencao = campoManutencao.getPecasUtilizadas();
		pecasDaManutencao.remove(peca);
		this.campoManutencao.setPecasUtilizadas(pecasDaManutencao);
		cancelarPeca();
	}
	public void adicionarPecaNaManutencao() {
		Collection<Peca> pecasDaManutencao = campoManutencao.getPecasUtilizadas();
		pecasDaManutencao.add(peca);
		this.campoManutencao.setPecasUtilizadas(pecasDaManutencao);
		cancelarPeca();
	}

	public Collection<Manutencao> getManutencoes() {
		return manutencoes;
	}

	public void setManutencoes(Collection<Manutencao> manutencoes) {
		ManutencaoBean.manutencoes = manutencoes;
	}
	public String mostrarListaPecas() {
		mostraListaPecas = true;
		return null;
	}

	

	public String[] getValoresEntregue() {
		String[] valores = new String[2];
		valores[0] = "Sim";
		valores[1] = "Não";
		return valores;
	}

	public String[] getValoresPagou() {
		String[] valores = new String[2];
		valores[0] = "Sim";
		valores[1] = "Não";
		return valores;
	}

	public Collection<Manutencao> getManutencoesDoCliente() {
		System.out.println("LOCAL DO PRINT: metodo getManutencoesDoCliente() em ManutencaoBean");
		System.out.println("cliente: " + ClienteBean.clienteSelecionado.toString());
		System.out.println("------------------------------------------------------------------------------------------------------------");
		Collection<Manutencao> manutencoes = manutencaoService.recuperarManutencoes();
		Collection<Manutencao> manutencoesCliente = new ArrayList<>();
		if (manutencoes.size() == 0 || manutencoes.isEmpty()) {
			return null;
		}
		for (Manutencao m : manutencoes) {
			if (m.getCliente().getIdCliente() == ClienteBean.clienteSelecionado.getIdCliente()) {
				manutencoesCliente.add(m);
			}
		}
		if (manutencoesCliente.size() == 0 || manutencoesCliente.isEmpty()) {
			return null;
		}
		return manutencoesCliente;
	}
	public void salvarPeca() {
		this.pecaService.atualizar(peca);
		pecas.add(peca);
		cancelarPeca();
	}
	public void excluirPeca() {
		this.pecaService.remover(peca);
		pecas.remove(peca);
		cancelarPeca();
	}
	public String cancelarPeca() {
		peca = new Peca();
		return "peca.java";
	}

	public static void setManutencoesDoCliente(Collection<Manutencao> manutencoesDoCliente) {
		ManutencaoBean.manutencoesDoCliente = manutencoesDoCliente;
	}

	public void atualizaPecas() {
		pecas = pecaService.recuperarPecas();
	}

	public Collection<Peca> getPecas() {
		atualizaPecas();
		return pecas;
	}

	public Equipamento[] getTiposEquipamento() {
		return model.enumerateds.Equipamento.values();
	}

	public TipoServico[] getTiposServico() {
		return model.enumerateds.TipoServico.values();
	}

	public String cancelar() {
		campoManutencao = new Manutencao();
		return "clientes.jsf";
	}

	public void excluirManutencao() {
		manutencaoService.remover(campoManutencao);
		if (manutencaoService.procurarPorId(campoManutencao.getIdManutencao()) == null) {
			manutencoesDoCliente.remove(campoManutencao);
			manutencaoService.instanciarCampoManutencao(ClienteBean.clienteSelecionado);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Manutenção excluída com Sucesso!", "Detalhes"));
			
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Não foi possível remover!", "Detalhes"));
		}
		manutencaoService.instanciarCampoManutencao(ClienteBean.clienteSelecionado);

		System.out.println("LOCAL DO PRINT: metodo excluirManutencao() em ManutencaoBean");
		System.out.println("objeto da classe ManutencaoBean: " + campoManutencao.toString());
		//System.out.println("objeto cliente da classe ManutencaoBean: " + campoManutencao.getCliente().toString());
		System.out.println("------------------------------------------------------------------------------------------------------------");
		
	}

	public String editarManutencao() {
		if (campoManutencao == null) {
			campoManutencao = new Manutencao();
			return "home.jsf";
		} else {
			return "manutencoes.jsf";
		}
	}

	public String editarManutencaoAPartirDaLista() {
		System.out.println("editando manutencao a partir da lista" + campoManutencao.toString());
		campoManutencao = manutencaoService.instanciarManutencaoAEditar(campoManutencao);
		System.out.println("editando endereco a partir da lista2" + campoManutencao.toString());
		if (campoManutencao == null) {
			campoManutencao = new Manutencao();
			return "home.jsf";
		} else {
			return "manutencoes.jsf";
		}
	}

	public void salvarManutencao() {
		
		try {
			if (campoManutencao.getCliente().getIdCliente() != 0) {
				if (manutencaoService.procurarPorId(campoManutencao.getIdManutencao()) == null) {					
					if (manutencaoService.cadastrar(campoManutencao)) {					
						if (!manutencoes.contains(campoManutencao)) {
							manutencoes.add(campoManutencao);
						}
						if (!manutencoesDoCliente.contains(campoManutencao)) {
							manutencoes.add(campoManutencao);
						}
						
						FacesContext contexto = FacesContext.getCurrentInstance();
						contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro efetuada com sucesso! ", ""));
						manutencaoService.instanciarCampoManutencao(ClienteBean.clienteSelecionado);
						cancelar();
					} else {
						FacesContext contexto = FacesContext.getCurrentInstance();
						contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Não foi possível cadastrar! ", ""));
						cancelar();
					}
				} else {
					if (manutencaoService.atualizar(campoManutencao)) {
						
						if (!manutencoes.contains(campoManutencao)) {
							manutencoes.add(campoManutencao);
						}
						if (!manutencoesDoCliente.contains(campoManutencao)) {
							manutencoesDoCliente.add(campoManutencao);
						}
						FacesContext contexto = FacesContext.getCurrentInstance();
						contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Edição efetuada com sucesso! ", ""));
						cancelar();
					} else {
						FacesContext contexto = FacesContext.getCurrentInstance();
						contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Não foi possível cadastrar! ", ""));
						cancelar();
					}
				}
				
			} else {
				FacesContext contexto = FacesContext.getCurrentInstance();
				contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ID do cliente está nulo! ", ""));
				cancelar();
			}
			manutencaoService.instanciarCampoManutencao(ClienteBean.clienteSelecionado);
			cancelar();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Manutencao getCampoManutencao() {
		return campoManutencao;
	}

	public void setCampoManutencao(Manutencao campoManutencao) {
		ManutencaoBean.campoManutencao = campoManutencao;
	}

	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		ManutencaoBean.peca = peca;
	}

	public boolean isMostraListaPecas() {
		return mostraListaPecas;
	}

	public void setMostraListaPecas(boolean mostraListaPecas) {
		this.mostraListaPecas = mostraListaPecas;
	}
	public static void setPecas(Collection<Peca> pecas) {
		ManutencaoBean.pecas = pecas;
	}

}

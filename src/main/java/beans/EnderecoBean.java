package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import database.BaseDadosTest;
import model.entity.Cliente;
import model.entity.Endereco;
import model.entity.Manutencao;
import model.entity.Peca;
import model.entity.Sexo;
import model.enumerateds.Equipamento;
import model.enumerateds.TipoServico;
import model.services.ClienteService;
import model.services.EnderecoService;
import model.services.ManutencaoService;
import model.services.PecaService;

@ManagedBean
@SessionScoped
public class EnderecoBean implements Serializable {

	public static Collection<Cliente> clientes;
	public static Collection<Endereco> enderecos;
	public static Collection<Peca> pecas;
	public static Collection<Manutencao> manutencoesDoCliente;
	public static Collection<Manutencao> manutencoes;

	public static ClienteService clienteService = new ClienteService();
	public static ManutencaoService manutencaoService = new ManutencaoService();
	public static PecaService pecaService = new PecaService();
	public static EnderecoService enderecoService = new EnderecoService();

	public static Cliente clienteSelecionado;
	public static Endereco endereco;

	public EnderecoBean() {
		System.out.println("Entrou na pagina");
	}

	public void inicializaComponentes() {
		endereco = enderecoService.getEnderecoAEditar();
		enderecos = enderecoService.recuperarEnderecos();
	}

	public void inicializaListagem() {
		enderecos = enderecoService.recuperarEnderecos();
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public Endereco getEndereco() {
		return this.endereco;
	}

	public Collection<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Collection<Endereco> enderecos) {
		EnderecoBean.enderecos = enderecos;
	}

	public String cancelar() {
		clienteSelecionado = new Cliente();
		ClienteBean.clienteSelecionado = new Cliente();
		endereco = new Endereco();
		return "clientes.jsf";
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void excluirEndereco() {
		enderecoService.remover(endereco);
		if (enderecoService.procurarPorId(endereco.getCliente().getIdCliente()) == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Endereco excluído com Sucesso!", "Detalhes"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Não foi possível cancelar!", "Detalhes"));
		}
		cancelar();
	}

	public String editarEndereco() {
		System.out.println("editando endereco a partir da lista" + endereco.toString());
		if (endereco == null) {
			endereco = new Endereco();
			return "home.jsf";
		} else {
			return "endereco.jsf";
		}
	}
	public String editarEnderecoAPartirDaLista() {
		System.out.println("editando endereco a partir da lista" + endereco.toString());
		endereco = enderecoService.instanciarEnderecoAEditar(endereco);
		System.out.println("editando endereco a partir da lista2" + endereco.toString());
		if (endereco == null) {
			endereco = new Endereco();
			return "home.jsf";
		} else {
			return "endereco.jsf";
		}
	}

	public void salvarEndereco() {
		try {
			System.out.println("endereco em Salvar: " + endereco.toString());

			if (endereco.getCliente().getIdCliente() != 0) {
				System.out.println("1 if: " + endereco.toString());
				if (enderecoService.procurarPorId(endereco.getCliente().getIdCliente()) == null) {
					System.out.println("2 if: " + endereco.toString());
					if (enderecoService.cadastrar(endereco)) {
						System.out.println("3 if: " + endereco.toString());
						if (!enderecos.contains(endereco)) {
							enderecos.add(endereco);
						}
						FacesContext contexto = FacesContext.getCurrentInstance();
						contexto.addMessage(null,
								new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro efetuada com sucesso! ", ""));
					} else {
						System.out.println("4 if: " + endereco.toString());
						FacesContext contexto = FacesContext.getCurrentInstance();
						contexto.addMessage(null,
								new FacesMessage(FacesMessage.SEVERITY_INFO, "Não foi possível cadastrar! ", ""));
					}
				} else {
					System.out.println("5 if: " + endereco.toString());
					if (enderecoService.atualizar(endereco)) {
						System.out.println("6 if: " + endereco.toString());
						if (!enderecos.contains(endereco)) {
							enderecos.add(endereco);
						}
						FacesContext contexto = FacesContext.getCurrentInstance();
						contexto.addMessage(null,
								new FacesMessage(FacesMessage.SEVERITY_INFO, "Edição efetuada com sucesso! ", ""));
					} else {
						System.out.println("7 if: " + endereco.toString());
						FacesContext contexto = FacesContext.getCurrentInstance();
						contexto.addMessage(null,
								new FacesMessage(FacesMessage.SEVERITY_INFO, "Não foi possível cadastrar! ", ""));
					}
				}
			} else {
				System.out.println("8 if: " + endereco.toString());
				FacesContext contexto = FacesContext.getCurrentInstance();
				contexto.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "ID do cliente está nulo! ", ""));
			}

			System.out.println("antes do cancelar em salvar endereço" + endereco.toString());
			cancelar();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

package beans;

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
import model.entity.Equipamento;
import model.entity.Manutencao;
import model.entity.Sexo;
import model.entity.TipoServico;
import model.services.ClienteService;
import model.services.ManutencaoService;
import model.services.PecaService;


@ManagedBean
@SessionScoped
public class SistemaGerenciadorBean {

	//private static Collection<Manutencao> manutencoes;
	public Collection<Cliente> clientes;
	
	private ClienteService clienteService = new ClienteService();
	private ManutencaoService manutencaoService = new ManutencaoService();
	private PecaService pecaService = new PecaService();

	private Cliente clienteSelecionado;


	@PersistenceContext(unitName = "SistemaGerenciador")
	private EntityManager em;

	

	public SistemaGerenciadorBean() {
		
		clienteSelecionado = new Cliente();
		clientes = (ArrayList<Cliente>) clienteService.recuperarClientes();
		//System.out.println("clientes " +clientes);
	}
	
	public static void main(String [] args) {
		iniciaBanco();
	}

	public static void iniciaBanco() {
		EntityManagerFactory emf;
		EntityManager em;
		emf = Persistence.createEntityManagerFactory("SistemaGerenciador");
		em = emf.createEntityManager();
		BaseDadosTest bdt = new BaseDadosTest(em);
		em.getTransaction().begin();
		bdt.createClientesEEnderecos();
		bdt.createPecas();
		bdt.createManutencoes();
		em.getTransaction().commit();
		em.close();
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public Collection<Cliente> getLista() {
		return clientes;
	}

	public void setLista(Collection<Cliente> clientes) {
		this.clientes = (ArrayList<Cliente>) clientes;
	}

	public void salvar() {
		try {
			clienteService.atualizar(clienteSelecionado);
			if(!clientes.contains(clienteSelecionado)) {
				clientes.add(clienteSelecionado);
			}
			clienteSelecionado = new Cliente();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Edição efetuada com sucesso! ", ""));
		}catch(Exception ex) {
			ex.printStackTrace(); 
		}
		
	}
		

	public String cancelar() {
		clienteSelecionado = new Cliente();
		return "cadastroclientes.jsf";
	}

	public void excluir() {
		clienteService.remover(clienteSelecionado);
		if(clienteService.procurarPorId(clienteSelecionado.getIdCliente()) == null) {
			clientes.remove(clienteSelecionado);
			clienteSelecionado = new Cliente();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente Excluído com Sucesso!", "Detalhes"));
		}
		clienteSelecionado = new Cliente();
	}

	public Sexo[] getSexos() {
		return model.entity.Sexo.values();
	}
	public Equipamento[] getTiposEquipamento() {
		return model.entity.Equipamento.values();	
	}
	public TipoServico[] getTiposServico() {
		return model.entity.TipoServico.values();	
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}

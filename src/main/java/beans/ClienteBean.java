

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
import model.entity.Sexo;
import model.services.ClienteService;
import model.services.EnderecoService;
import model.services.ManutencaoService;
import model.services.PecaService;

@ManagedBean
@SessionScoped
public class ClienteBean implements Serializable {

	
	public static Collection<Cliente> listaClientes;
	public Collection<Endereco> enderecos;

	public static ClienteService clienteService = new ClienteService();
	public static ManutencaoService manutencaoService = new ManutencaoService();
	public static PecaService pecaService = new PecaService();
	public static EnderecoService enderecoService = new EnderecoService();

	public static Cliente clienteSelecionado = new Cliente();
	public static Manutencao manutencao = new Manutencao();

	public static Endereco enderecoAEditar;
	public static Manutencao campoManutencao;
	
	public ClienteBean() {
		System.out.println("Entrou na pagina listaClientes");
	}
	
	public void inicializaComponentes() {
		//clienteSelecionado = new Cliente();
		//manutencao = new Manutencao();
		listaClientes = clienteService.recuperarClientes();
		
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public Collection<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(Collection<Cliente> clientes) {
		this.listaClientes = (ArrayList<Cliente>) clientes;
	}

	public void salvarCliente() {
		try {
			if(clienteService.atualizar(clienteSelecionado)) {
				FacesContext contexto = FacesContext.getCurrentInstance();
				contexto.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Edição efetuada com sucesso! ", ""));
			}else {
				FacesContext contexto = FacesContext.getCurrentInstance();
				contexto.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Não foi possível! ", ""));
			}
			if (!listaClientes.contains(clienteSelecionado)) {
				listaClientes.add(clienteSelecionado);
			}
			clienteSelecionado = new Cliente();
			cancelar();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public String cancelar() {
		clienteSelecionado = new Cliente();
		manutencao = new Manutencao();
		return "clientes.jsf";
	}

	public void excluirCliente() {
		clienteService.remover(clienteSelecionado);
		if (clienteService.procurarPorId(clienteSelecionado.getIdCliente()) == null) {
			listaClientes.remove(clienteSelecionado);
			clienteSelecionado = new Cliente();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente Excluído com Sucesso!", "Detalhes"));
		}
		clienteSelecionado = new Cliente();
	}
	public Sexo[] getSexos() {
		return model.entity.Sexo.values();
	}
	
	public String editarEnderecoButton() {
		if (enderecoService.procurarPorId(clienteSelecionado.getIdCliente()) == null) {
			enderecoAEditar = new Endereco();
			enderecoAEditar.setCliente(clienteSelecionado);
			enderecoAEditar = enderecoService.instanciarEnderecoAEditar(enderecoAEditar);
			
			return "endereco.jsf";
		} else {
			enderecoAEditar = enderecoService.procurarPorId(clienteSelecionado.getIdCliente());
			enderecoAEditar.setCliente(clienteSelecionado);
			enderecoService.instanciarEnderecoAEditar(enderecoAEditar);
			return "endereco.jsf";
		}
		
	}
	public String cadastroManutencaoButton() {
		campoManutencao = manutencaoService.instanciarCampoManutencao(clienteSelecionado);
		System.out.println("LOCAL DO PRINT: metodo cadastroManutencaoButton() em clienteBean");
		System.out.println("retorno do método instanciarCampoManutencao: " + campoManutencao.toString());
		System.out.println("objeto cliente da classe ManutencaoBean: " + campoManutencao.getCliente().toString());
		System.out.println("------------------------------------------------------------------------------------------------------------");
		return "manutencoes.jsf";
		
	}

	public Endereco getEnderecoAEditar() {
		return enderecoAEditar;
	}

	public void setEnderecoAEditar(Endereco enderecoAEditar) {
		this.enderecoAEditar = enderecoAEditar;
	}


}

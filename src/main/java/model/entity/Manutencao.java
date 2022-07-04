package model.entity;

import java.io.Serializable;
import java.lang.String;
import java.util.Collection;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Manutencao implements Serializable {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idManutencao;
	@Enumerated(EnumType.STRING)
	private Equipamento tipoEquipamento;
	@Enumerated(EnumType.STRING)
	private TipoServico tipoServico;
	private double valor;
	private String descricao;
	private boolean entregue;
	private boolean pagou;	
	@ManyToOne(cascade = CascadeType.ALL)
	private Cliente cliente;
	@ManyToMany
	@JoinTable(name="pecasManutencoes",
			joinColumns=@JoinColumn(name="id_manutencao"),
			inverseJoinColumns=@JoinColumn(name="id_peca"))
	private Collection<Peca> pecasUtilizadas;
	private static final long serialVersionUID = 1L;

	public Manutencao() {
		super();
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public int getIdManutencao() {
		return idManutencao;
	}
	public int getManutencao() {
		return this.idManutencao;
	}

	public void setIdManutencao(int idMautencao) {
		this.idManutencao = idManutencao;
	}

	public Equipamento getTipoEquipamento() {
		return this.tipoEquipamento;
	}

	public void setTipoEquipamento(Equipamento tipoEquipamento) {
		this.tipoEquipamento = tipoEquipamento;
	}

	public TipoServico getTipoServico() {
		return this.tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean getEntregue() {
		return this.entregue;
	}

	public void setEntregue(boolean entregue) {
		this.entregue = entregue;
	}

	public boolean getPagou() {
		return this.pagou;
	}

	public void setPagou(boolean pagou) {
		this.pagou = pagou;
	}
	
	public Collection<Peca> getPecasUtilizadas() {
		return pecasUtilizadas;
	}

	public void setPecasUtilizadas(Collection<Peca> pecasUtilizadas) {
		this.pecasUtilizadas = pecasUtilizadas;
	}
	@Override
	public String toString() {
		return "Manutencao [idManutencao=" + idManutencao + ", tipoEquipamento=" + tipoEquipamento + ", tipoServico="
				+ tipoServico + ", valor=" + valor + ", descricao=" + descricao + ", entregue=" + entregue + ", pagou="
				+ pagou + ", pecasUtilizadas=" + pecasUtilizadas + "]";
	}
	public Cliente getCliente() {
		return cliente;
	}
}

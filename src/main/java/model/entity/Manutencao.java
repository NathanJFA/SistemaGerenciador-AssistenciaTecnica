package model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.enumerateds.Equipamento;
import model.enumerateds.TipoServico;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Manutencao implements Serializable {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idManutencao;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Equipamento tipoEquipamento;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoServico tipoServico;
	private double valor;
	private String descricao;
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date data;
	private boolean entregue;
	private boolean pagou;	
	@ManyToOne(cascade = { CascadeType.MERGE})
	private Cliente cliente;
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
	@JoinTable(name="pecasManutencoes",
			joinColumns=@JoinColumn(name="id_manutencao"),
			inverseJoinColumns=@JoinColumn(name="id_peca"))
	private Collection<Peca> pecasUtilizadas;
	private static final long serialVersionUID = 1L;

	public Manutencao() {
		this.descricao="";
		this.data=new Date();
		this.entregue=false;
		this.pagou=false;
		this.pecasUtilizadas= new ArrayList<>();
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
	public int quantPecas() {
		if(pecasUtilizadas == null) {
			pecasUtilizadas = new ArrayList<>();
			return pecasUtilizadas.size();
		}
		return pecasUtilizadas.size();
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
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
}

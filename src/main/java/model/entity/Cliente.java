package model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;

/**
 * Entity implementation class for Entity: Cliente
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCliente;
	private String nome;
	@Column(unique=true)
	private String cpf;
	private String telefone;
	private String email;
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;
	@OneToMany(mappedBy="cliente",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private Collection<Manutencao> manutencoesCliente;
	

	public Cliente() {
		manutencoesCliente = new ArrayList<>();
	}   
	   
	public int getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}   
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}   
	
	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}   
	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
	public Sexo getSexo() {
		return this.sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}   
	
	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Collection<Manutencao> getManutencoesCliente() {
		return manutencoesCliente;
	}
	public void setManutencoesCliente(ArrayList<Manutencao> manutencoesCliente) {
		this.manutencoesCliente = manutencoesCliente;
	}
	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nome=" + nome + ", cpf=" + cpf + ", telefone=" + telefone
				+ ", email=" + email + ", sexo=" + sexo + ", endereco=" + endereco + ", manutencoesCliente="
				+ manutencoesCliente + "]";
	}
	public void addManutencao(Manutencao manutencao) {
		this.manutencoesCliente.add(manutencao);
	}
	public void removeManutencao(Manutencao manutencao) {
		this.manutencoesCliente.remove(manutencao);
	}
	public int getQuantManutencoes() {
		return this.manutencoesCliente.size();
	}
   
}

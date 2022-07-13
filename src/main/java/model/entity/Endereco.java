package model.entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Endereco
 *
 */
@Entity
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cliente_id",nullable = false)
	private Cliente cliente;
	private String cidade;
	private String rua;
	private String bairro;
	private String complemento;
	private String numero;

	public Endereco() {
		this.bairro="";
		this.cidade="";
		this.complemento="";
		this.numero="";
		this.rua="";
	}   
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getRua() {
		return this.rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}   
	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}   
	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}   
	public String getNumero() {
		return this.numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Override
	public String toString() {
		return "Endereco [cliente=" + cliente + ", cidade=" + cidade + ", rua=" + rua + ", bairro=" + bairro
				+ ", complemento=" + complemento + ", numero=" + numero + "]";
	}
   
}

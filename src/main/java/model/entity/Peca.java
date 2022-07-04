package model.entity;

import java.io.Serializable;
import java.lang.String;
import java.util.Collection;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Peca
 *
 */
@Entity
public class Peca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPeca;
	private String descricao;
	private double valorPreDefinido;	

	public Peca() {
		super();
	}       
	public int getIdPeca() {
		return this.idPeca;
	}

	public void setIdPeca(int idPeca) {
		this.idPeca = idPeca;
	}   
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}   
	public double getValorPreDefinido() {
		return this.valorPreDefinido;
	}

	public void setValorPreDefinido(double valorPreDefinido) {
		this.valorPreDefinido = valorPreDefinido;
	}
	@Override
	public String toString() {
		return "Peca [idPeca=" + idPeca + ", descricao=" + descricao + ", valorPreDefinido=" + valorPreDefinido + "]";
	}   
}

package br.com.staroski.recarga.persistence.entities;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "Recarga")
public class Recarga {

	private long id;
	private int quantidade;
	private Date data = new Date();
	private Municao municao;

	@Column(name = "data", columnDefinition = "date")
	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getData() {
		return data;
	}

	@Id
	@GeneratedValue
	@Column(name = "id_recarga", columnDefinition = "numeric")
	public long getId() {
		return id;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_recarga_municao", nullable = false)
	public Municao getMunicao() {
		return municao;
	}

	@Column(name = "quantidade", columnDefinition = "integer")
	public int getQuantidade() {
		return quantidade;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setMunicao(Municao municao) {
		this.municao = municao;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}

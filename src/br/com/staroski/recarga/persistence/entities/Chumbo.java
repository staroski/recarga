package br.com.staroski.recarga.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "Chumbo")
public class Chumbo {

	private long id_chumbo;
	private String descricao;
	private int quantidade;

	@Column(name = "descricao", columnDefinition = "varchar(128)")
	public String getDescricao() {
		return descricao;
	}

	@Id
	@GeneratedValue
	@Column(name = "id_chumbo", columnDefinition = "numeric")
	public long getId() {
		return id_chumbo;
	}

	@Column(name = "quantidade", columnDefinition = "integer")
	public int getQuantidade() {
		return quantidade;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setId(long id) {
		id_chumbo = id;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}

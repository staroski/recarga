package br.com.staroski.recarga.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "Projetil")
public final class Projetil {

	private long id_projetil;
	private String descricao;
	private int quantidade;

	@Column(name = "descricao", columnDefinition = "varchar(128)")
	public String getDescricao() {
		return descricao;
	}

	@Id
	@GeneratedValue
	@Column(name = "id_projetil", columnDefinition = "numeric")
	public long getId() {
		return id_projetil;
	}

	@Column(name = "quantidade", columnDefinition = "integer")
	public int getQuantidade() {
		return quantidade;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setId(long id) {
		id_projetil = id;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}

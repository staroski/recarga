package br.com.staroski.recarga.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "Polvora")
public final class Polvora {

	private long id_polvora;
	private String descricao;
	private int quantidade;

	@Column(name = "descricao", columnDefinition = "varchar(128)")
	public String getDescricao() {
		return descricao;
	}

	@Id
	@GeneratedValue
	@Column(name = "id_polvora", columnDefinition = "numeric")
	public long getId() {
		return id_polvora;
	}

	@Column(name = "quantidade", columnDefinition = "integer")
	public int getQuantidade() {
		return quantidade;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setId(long id) {
		id_polvora = id;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}

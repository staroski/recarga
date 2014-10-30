package br.com.staroski.recarga.persistence;

import javax.persistence.*;

@Entity
@Table(name = "Espoleta")
public final class Espoleta {

	private long id_espoleta;
	private String descricao;
	private int quantidade;

	@Column(name = "descricao", columnDefinition = "varchar(128)")
	public String getDescricao() {
		return descricao;
	}

	@Id
	@GeneratedValue
	@Column(name = "id_espoleta", columnDefinition = "numeric")
	public long getId() {
		return id_espoleta;
	}

	@Column(name = "quantidade", columnDefinition = "integer")
	public int getQuantidade() {
		return quantidade;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setId(long id) {
		id_espoleta = id;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}

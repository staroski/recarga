package br.com.staroski.recarga.persistence;

import javax.persistence.*;

@Entity
@Table(name = "Municao")
public final class Municao {

	private long id_municao;
	private int quantidade;
	private Calibre calibre;

	@OneToOne(optional = false)
	@JoinColumn(name = "id_municao_calibre", nullable = false)
	public Calibre getCalibre() {
		return calibre;
	}

	@Id
	@GeneratedValue
	@Column(name = "id_municao", columnDefinition = "numeric")
	public long getId() {
		return id_municao;
	}

	@Column(name = "quantidade", columnDefinition = "integer")
	public int getQuantidade() {
		return quantidade;
	}

	public void setCalibre(Calibre calibre) {
		this.calibre = calibre;
	}

	public void setId(long id) {
		id_municao = id;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}

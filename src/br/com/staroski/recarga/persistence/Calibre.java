package br.com.staroski.recarga.persistence;

import javax.persistence.*;

@Entity
@Table(name = "Calibre")
public final class Calibre {

	private long id_calibre;
	private String descricao;
	private double polvora;
	private int chumbos;

	@Column(name = "chumbos", columnDefinition = "integer")
	public int getChumbos() {
		return chumbos;
	}

	@Column(name = "descricao", columnDefinition = "varchar(128)")
	public String getDescricao() {
		return descricao;
	}

	@Id
	@GeneratedValue
	@Column(name = "id_calibre", columnDefinition = "numeric")
	public long getId() {
		return id_calibre;
	}

	@Column(name = "polvora", columnDefinition = "numeric(6,3)")
	public double getPolvora() {
		return polvora;
	}

	public void setChumbos(int chumbos) {
		this.chumbos = chumbos;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setId(long id) {
		id_calibre = id;
	}

	public void setPolvora(double polvora) {
		this.polvora = polvora;
	}
}

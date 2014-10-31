package br.com.staroski.recarga.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "Municao")
public class Municao {

	private long id_municao;
	private Calibre calibre;
	private Estojo estojo;
	private Projetil projetil;
	private Chumbo chumbo;
	private int quantidadeChumbo;
	private Polvora polvora;
	private double quantidadePolvora;
	private Espoleta espoleta;
	private int quantidade;

	@ManyToOne(optional = false, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "id_municao_calibre", nullable = false)
	public Calibre getCalibre() {
		return calibre;
	}

	@ManyToOne(optional = true)
	@JoinColumn(name = "id_municao_chumbo", nullable = true)
	public Chumbo getChumbo() {
		return chumbo;
	}

	@ManyToOne(optional = false, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "id_municao_espoleta", nullable = false)
	public Espoleta getEspoleta() {
		return espoleta;
	}

	@ManyToOne(optional = false, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "id_municao_estojo", nullable = false)
	public Estojo getEstojo() {
		return estojo;
	}

	@Id
	@GeneratedValue
	@Column(name = "id_municao", columnDefinition = "numeric")
	public long getId() {
		return id_municao;
	}

	@ManyToOne(optional = false, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "id_municao_polvora", nullable = false)
	public Polvora getPolvora() {
		return polvora;
	}

	@ManyToOne(optional = false, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "id_municao_projetil", nullable = false)
	public Projetil getProjetil() {
		return projetil;
	}

	@Column(name = "quantidade", columnDefinition = "integer")
	public int getQuantidade() {
		return quantidade;
	}

	@Column(name = "quantidade_chumbo", columnDefinition = "integer", nullable = true)
	public int getQuantidadeChumbo() {
		return quantidadeChumbo;
	}

	@Column(name = "quantidade_polvora", columnDefinition = "numeric(6,3)")
	public double getQuantidadePolvora() {
		return quantidadePolvora;
	}

	public void setCalibre(Calibre calibre) {
		this.calibre = calibre;
	}

	public void setChumbo(Chumbo chumbo) {
		this.chumbo = chumbo;
	}

	public void setEspoleta(Espoleta espoleta) {
		this.espoleta = espoleta;
	}

	public void setEstojo(Estojo estojo) {
		this.estojo = estojo;
	}

	public void setId(long id) {
		id_municao = id;
	}

	public void setPolvora(Polvora polvora) {
		this.polvora = polvora;
	}

	public void setProjetil(Projetil projetil) {
		this.projetil = projetil;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public void setQuantidadeChumbo(int quantidadeChumbo) {
		this.quantidadeChumbo = quantidadeChumbo;
	}

	public void setQuantidadePolvora(double quantidadePolvora) {
		this.quantidadePolvora = quantidadePolvora;
	}
}

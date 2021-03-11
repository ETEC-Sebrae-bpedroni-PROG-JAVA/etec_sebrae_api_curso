package br.gov.sp.etecsebrae.dto;

import java.io.Serializable;

public class CursoDto implements Serializable {

	private static final long serialVersionUID = 2494721780728280004L;

	private String nome;

	private String sigla;

	public CursoDto() {
		super();
	}

	public CursoDto(String nome, String sigla) {
		super();
		this.nome = nome;
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}

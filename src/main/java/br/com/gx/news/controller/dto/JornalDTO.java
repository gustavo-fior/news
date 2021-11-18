package br.com.gx.news.controller.dto;

import br.com.gx.news.modelo.Jornal;

public class JornalDTO {

	private String nome;
	private String link;
	
	public JornalDTO(Jornal jornal) {
		this.nome = jornal.getNome();
		this.link = jornal.getLink();
	}

	public JornalDTO(String nome, String link) {
		this.nome = nome;
		this.link = link;
	}

	public String getNome() {
		return nome;
	}

	public String getLink() {
		return link;
	}

}

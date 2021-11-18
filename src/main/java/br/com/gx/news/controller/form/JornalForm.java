package br.com.gx.news.controller.form;

import javax.validation.constraints.NotBlank;

import br.com.gx.news.modelo.Jornal;

public class JornalForm {

	@NotBlank
	private String nome;

	@NotBlank
	private String link;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Jornal toJornal() {

		Jornal jornal = new Jornal(this.nome, this.link);
		return jornal;

	}

}

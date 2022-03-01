package br.com.gx.news.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.gx.news.modelo.Trend;

public class TrendForm {

	@NotBlank
	private String titulo;

	@NotBlank
	@Size(max = 200, message = "Máximo de 200 caractéres no link da imagem.")
	private String imagem;

	@NotBlank
	private String descricao;

	@NotBlank
	private String link;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Trend convert() {

		return new Trend(this.titulo, this.imagem, this.descricao, this.link);

	}

}
